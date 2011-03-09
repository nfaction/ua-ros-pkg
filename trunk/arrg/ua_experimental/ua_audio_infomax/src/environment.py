#!/usr/bin/env python
import roslib; roslib.load_manifest('ua_audio_infomax')
import rospy

__author__ = 'Daniel Ford, dford@email.arizona.edu'

from random import random, choice
from scipy import zeros
from numpy import *
from math import *
from std_msgs.msg import String

from pybrain.utilities import Named, drawIndex
from pybrain.rl.environments.environment import Environment

# import ROS services
from ua_audio_infomax.srv import *

class InfoMaxEnv(Environment, Named):

	def __init__(self, objDict, actionDict, numCategories):

		self.numCategories = numCategories
		self.objDict = objDict
		self.actionDict = actionDict
		self.catNames = objDict.values()
		self.objectNames = objDict.keys()
		#self.actionNames = actionDict.keys()
		self.actionNames = ["pick up", "drop", "push", "squeeze", "move left", "move right", "reset"]

		# set up "request action" client here
		rospy.init_node('infomaxAgent')

	# get observation from environment
	def getSensors(self, action, location):

		# get category of the object in front of the robot
		catID = int(self.objDict[self.objectNames[location]])

		# send "sense" service request 
		rospy.wait_for_service('InfoMax')	
		try:
			# create service definition
			sense = rospy.ServiceProxy('InfoMax',InfoMax)
			# call service, which returns PDF corresponding to action we requested
			obs = sense(self.objectNames, self.actionNames, self.numCategories, catID, action)	

			# return only the PDF from the robot
			return obs	

		# if service fails, exit
		except rospy.ServiceException, e:
			print "Service call failed: %s"%e

	# perform action selected by the network
	def performAction(self, action):

		# send "move/manipulate" service request
		rospy.wait_for_service('InfoMax') 	
		try:
			move = rospy.ServiceProxy('InfoMax',InfoMax)	
			# call service and only grab new location
			response = move(self.objectNames, self.actionNames, self.numCategories, 0, action)	

			# return only the robot's location
			return response.location

		# service failure handler
		except rospy.ServiceException, e:
			print "Service call failed: %s"%e  
