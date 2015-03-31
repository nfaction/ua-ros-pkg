#summary A description of UA's Wubble Robot and related ROS packages

=Overview=

This repository provides a number of packages that support the Wubble Robot created by the Arizona Robotics Research Group. Because a number of the components of the Wubble Robot are also used by other research groups, we provide a description of the robot and of relevant software packages. The [http://www.ros.org/wiki/ua_robots ua_robots] stack contains URDF description files for the Wubble Robot and its components. This URDF is linked to detailed 3D models of the robot's components, so that all of the robot's sensors and motors can be accurately simulated in Gazebo (seen below) and visualized in rviz, with correct transform information for tf.   

=Wubble Robot=

<div style="float:left;"> 
<img alt="logo" src="http://ua-ros-pkg.googlecode.com/svn/trunk/arrg/wiki/images/wubble1-sim.png" /><img alt="background" src="http://ua-ros-pkg.googlecode.com/svn/trunk/arrg/wiki/images/wubble1-rl.png" /> 
</div>

Packages:
  * [http://www.ros.org/wiki/wubble_description wubble_description]
    * [http://www.ros.org/wiki/wubble_actions wubble_actions]
      * [http://www.ros.org/wiki/wubble_mapping wubble_mapping]
        * [http://www.ros.org/wiki/wubble_teleop wubble_teleop]
          * [http://www.ros.org/wiki/wubble_blocks wubble_blocks]

          =Components=

          ==Videre ERRATIC (Mobile Base)==

          <img src="http://www.videredesign.com/assets/images/robot_images/robot/era-mobi_sm.png" height="100"/> 

          [http://www.videredesign.com/index.php?id=45 Description]

          Packages:
            * [http://www.ros.org/wiki/erratic_description erratic_description]
              * [http://www.ros.org/wiki/ua_erratic_player ua_erratic_player]
                * [http://www.ros.org/wiki/wubble_plugins wubble_plugins]

                ==Videre STOC (Stereo Camera)==

                <img src="http://www.videredesign.com/assets/images/vision_images/products/stoc-sthdcsg-9cm_med.png" height="100"/> 

                [http://www.videredesign.com/index.php?id=74 Description]

                Packages:
                  * [http://www.ros.org/wiki/videre_stoc_description videre_stoc_description]
                    * [http://www.ros.org/wiki/videre_stereo_cam videre_stereo_cam]
                      * [http://www.ros.org/wiki/videre_stoc videre_stoc]
                        * [http://www.ros.org/wiki/stoc_publisher stoc_publisher]

                        ==Hokuyo URG (Laser Range Finder)==

                        <img src="http://www.acroname.com/robotics/parts/R325-URG-04LX-UG01.jpg" height="100"/>

                        [http://www.acroname.com/robotics/parts/R325-URG-04LX-UG01.html Description]

                        Packages:
                          * [http://www.ros.org/wiki/houkyo_urg_description houkyo_urg_description]

                          ==Crust Crawler Smart Arm==

                          <img src="http://www.crustcrawler.com/products/smartarm/images/bigGrip2FingerLarge.jpg" height="100"/> 

                          [http://www.crustcrawler.com/products/smartarm/index.php?prod=12#thumb Description]

                          Packages:
                            * [http://www.ros.org/wiki/smart_arm_description smart_arm_description]
                              * [http://www.ros.org/wiki/smart_arm_controller smart_arm_controller]
                                * [http://www.ros.org/wiki/smart_arm_kinematics smart_arm_kinematics]

                                ==Robotis AX-12+ Servos==

                                <img src="http://www.crustcrawler.com/motors/AX12/images/AX_12.jpg" height=100/>

                                [http://www.robotis.com/zbxe/dynamixel_en Description]

                                Packages:
                                  * [http://www.ros.org/wiki/ax12_driver_core ax12_driver_core]
                                    * [http://www.ros.org/wiki/ax12_controller_core ax12_controller_core]

                                    ==Prosilica GC1600CH GigE Vision camera==

                                    <img src="http://www.prosilica.com/images/cameras/GC_back_400x254.jpg" height=100/>

                                    [http://www.prosilica.com/products/gc1600h.html Description]

                                    Packages:
                                      * [http://www.ros.org/wiki/prosilica_gige_sdk prosilica_gige_sdk]
                                        * [http://www.ros.org/wiki/prosilica_camera prosilica_camera]
                                          * [http://www.ros.org/browse/details.php?name=ua_overhead_cam ua_overhead_cam]
                                            * [http://www.ros.org/browse/details.php?name=background_filters background_filters]
