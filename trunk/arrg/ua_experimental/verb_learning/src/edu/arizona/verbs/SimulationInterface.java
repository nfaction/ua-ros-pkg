package edu.arizona.verbs;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import org.apache.log4j.Logger;

import ros.NodeHandle;
import ros.Ros;
import ros.RosException;
import ros.ServiceServer;
import ros.pkg.time_series.msg.Episode;
import ros.pkg.time_series.srv.ConstrainVerb;
import ros.pkg.time_series.srv.Empty;
import ros.pkg.time_series.srv.FSMReset;
import ros.pkg.time_series.srv.FSMUpdate;
import ros.pkg.time_series.srv.FindSignature;
import ros.pkg.time_series.srv.TestSignature;
import ros.pkg.time_series.srv.Empty.Request;
import ros.pkg.time_series.srv.Empty.Response;
import edu.arizona.cs.learn.algorithm.alignment.factory.SequenceFactory;
import edu.arizona.cs.learn.algorithm.alignment.model.Instance;
import edu.arizona.cs.learn.algorithm.heatmap.HeatmapImage;
import edu.arizona.cs.learn.timeseries.model.Interval;
import edu.arizona.cs.learn.timeseries.model.Signature;
import edu.arizona.cs.learn.util.SequenceType;

public class SimulationInterface {
	private static Logger logger = Logger.getLogger(SimulationInterface.class);

	// These are the verbs that we have learned!
	private static HashMap<String, Verb> verbs = new HashMap<String, Verb>();
	
	// Saves a heatmap to file. For reference, see Heatmaps.java
	// TODO: This should probably be somewhere else
	public static void makeHeatmap(Verb verb, List<Interval> episode, int min) {
		String f = verb.getVerbFolder() + "heatmap.png"; // Will overwrite earlier heatmaps, that's OK
		HeatmapImage.makeHeatmap(f, verb.getSignature().signature(), min, episode, SequenceType.allen);
	}
	
	// Utility functions for converting ROS message types to the appropriate Java classes
	public static List<Interval> getIntervals(Episode episode) {
		logger.debug("Getting intervals from episode...");
		List<Interval> intervals = new Vector<Interval>();
		for (int i = 0; i < episode.intervals.length; i++) {
			ros.pkg.time_series.msg.Interval interval = episode.intervals[i];
			logger.debug(interval.proposition);
			intervals.add(Interval.make(interval.proposition.toString(), interval.start, interval.end));
		}
		logger.debug("Got em.");
		return intervals;
	}
	
	public static Instance getInstance(Episode e, String name, int id) {
		List<Interval> intervals = getIntervals(e);		
		logger.debug(intervals.size());
		Collections.sort(intervals, Interval.eff);
		for (Interval i : intervals) {
			i.episode = id;
			i.file = "test";
			logger.debug(i);
		}
		logger.debug("Finished with " + intervals.size() + " intervals, converting to instance...");
		Instance result = new Instance(name, id, SequenceFactory.allenSequence(intervals));
		
		// TODO: SequenceFactory is in the time_series JAR! Not the src, which is probably a problem
		
		return result;
	}
	
	public static List<Instance> getSequences(Episode[] episodes, String name) {
		List<Instance> results = new ArrayList<Instance>();

		for (int i = 0; i < episodes.length; i++) {
			logger.debug("Converting episode " + (i+1) + " of " + episodes.length + " (" + episodes[i].intervals.length + " intervals)");
//			System.out.println(episodes[i].intervals[0]);
			results.add(getInstance(episodes[i], name, i));
		}
		
		return results;
	}
	
	/**
	 * @param args
	 * @throws RosException
	 */
	public static void main(String[] args) throws RosException {
		final Ros ros = Ros.getInstance();
		ros.init("time_series_node");
		NodeHandle nh = ros.createNodeHandle();

		// FindSignature service which creates a signature from a set of episodes
		// and stores it in a file (currently does not prune or otherwise alter the signature)
		ServiceServer.Callback<FindSignature.Request, FindSignature.Response> scb = 
		new ServiceServer.Callback<FindSignature.Request, FindSignature.Response>() {
			public FindSignature.Response call(FindSignature.Request request) {
				FindSignature.Response res = new FindSignature.Response();
				logger.debug("BEGIN find_signature callback...");

				logger.debug("Received " + request.episodes.length + " episodes for <" + request.verb + ">");
				List<Instance> instances = getSequences(request.episodes, request.verb);
				if (verbs.containsKey(request.verb)) {
					logger.info("Overwriting old data for verb: " + request.verb);
				} 
				Verb verb = new Verb(request.verb);
				verb.generalizeInstances(instances);
				verbs.put(request.verb, verb);
				
				logger.debug("... END find_signature callback.");
				return res;
			}
		};
		ServiceServer<FindSignature.Request, FindSignature.Response, FindSignature> findService = 
			nh.advertiseService("time_series/find_signature", new FindSignature(), scb);
		logger.info("Advertised service: " + findService.getService());
		
		// TestSignature service which tests an episode against a signature, and returns the distance
		ServiceServer.Callback<TestSignature.Request, TestSignature.Response> testCallback =
		new ServiceServer.Callback<TestSignature.Request, TestSignature.Response>() {
			@Override
			public TestSignature.Response call(TestSignature.Request request) {
				TestSignature.Response response = new TestSignature.Response();

				Verb verb = verbs.get(request.verb);
				List<Interval> intervals = getIntervals(request.episode);
				
				logger.debug("Generating Heatmap...");
				makeHeatmap(verb, intervals, request.min);
				
				Instance inst = getInstance(request.episode, "test", 0);
				double alignmentScore = verb.testInstance(inst, request.min);
				logger.debug("Alignment Score: " + alignmentScore);
				
				return response;
			}
		};
		ServiceServer<TestSignature.Request, TestSignature.Response, TestSignature> testService = 
			nh.advertiseService("time_series/test_signature", new TestSignature(), testCallback);
		logger.info("Advertised service: " + testService.getService());
		
		// Service "load_verbs" loads the stored verbs from file
		ServiceServer.Callback<Empty.Request, Empty.Response> loadCallback =
		new ServiceServer.Callback<Empty.Request, Empty.Response>() {
			@Override
			public Response call(Request request) {
				Response resp = new Response();
				
				File verbDirectory = new File("verbs");

				FilenameFilter filter = new FilenameFilter() {
				    public boolean accept(File dir, String name) {
//				        return name.endsWith(".signature");
				    	return dir.isDirectory();
				    }
				};
				
				String[] verbDirs = verbDirectory.list(filter);
				if (verbDirs == null) {
					logger.error("PROBLEM LOADING VERBS");
				} else {
					logger.debug("LOADING " + verbDirs.length + " SIGNATURES...");
				    for (String dir : verbDirs) {
				    	String signatureFile = "verbs/" + dir + "/signature.xml";
				    	logger.debug("\t" + signatureFile);
				        Verb verb = new Verb(dir, Signature.fromXML(signatureFile));
				        verbs.put(verb.getLexicalForm(), verb);
				    }
				}
				
				logger.debug("VERBS LOADED");
				
				return resp;
			}
		};
		ServiceServer<Empty.Request, Empty.Response, Empty> loadService = 
			nh.advertiseService("time_series/load_verbs", new Empty(), loadCallback);
		logger.info("Advertised service: " + loadService.getService());
				
		// "update_state" checks the current set of true propositions against the FSM recognizer
		ServiceServer.Callback<FSMUpdate.Request, FSMUpdate.Response> updateCallback = 
		new ServiceServer.Callback<FSMUpdate.Request, FSMUpdate.Response>() {
			@Override
			public FSMUpdate.Response call(FSMUpdate.Request request) {
				FSMUpdate.Response resp = new FSMUpdate.Response();
				
				Verb verb = verbs.get(request.verb);
				Set<String> activeProps = new TreeSet<String>();
				activeProps.addAll(Arrays.asList(request.relations));
				resp.reward = verb.updateFSM(activeProps);
				return resp;
			}
		};
		ServiceServer<FSMUpdate.Request, FSMUpdate.Response, FSMUpdate> updateService = 
			nh.advertiseService("time_series/fsm_update", new FSMUpdate(), updateCallback);
		logger.info("Advertised service: " + updateService.getService());
		
		// "fsm_reset" resets the current FSM recognizer (may not be strictly necessary)
		ServiceServer.Callback<FSMReset.Request, FSMReset.Response> resetCallback = 
		new ServiceServer.Callback<FSMReset.Request, FSMReset.Response>() {
			@Override
			public FSMReset.Response call(FSMReset.Request request) {
				FSMReset.Response resp = new FSMReset.Response();
				
				Verb verb = verbs.get(request.verb);
				verb.resetRecognizer();
				return resp;
			}
		};
		ServiceServer<FSMReset.Request, FSMReset.Response, FSMReset> resetService = 
			nh.advertiseService("time_series/fsm_reset", new FSMReset(), resetCallback);
		logger.info("Advertised service: " + resetService.getService());
		
		// "fsm_reset" resets the current FSM recognizer (may not be strictly necessary)
		ServiceServer.Callback<ConstrainVerb.Request, ConstrainVerb.Response> constraintCallback = 
		new ServiceServer.Callback<ConstrainVerb.Request, ConstrainVerb.Response>() {
			@Override
			public ConstrainVerb.Response call(ConstrainVerb.Request request) {
				ConstrainVerb.Response resp = new ConstrainVerb.Response();
				
				Verb verb = verbs.get(request.verb);
				verb.addConstraint(Arrays.asList(request.banned_props));
				
				return resp;
			}
		};
		ServiceServer<ConstrainVerb.Request, ConstrainVerb.Response, ConstrainVerb> constraintService = 
			nh.advertiseService("time_series/constrain_verb", new ConstrainVerb(), constraintCallback);
		logger.info("Advertised service: " + constraintService.getService());
		
		logger.info("Initialization Complete.");
		
		ros.spin();
	}
}
