#summary A description of the stacks in ua-ros-pkg

===ua_drivers===

This stack contains ROS drivers for various components of the Wubble Robot, specifically:
  * Drivers for the Dynamixel AX-12 servos ([http://www.ros.org/wiki/ax12_driver_core ax12_driver_core]). These servos are used by the robot's !SmartArm, tilt laser, and head pan/tilt.
    * A Python interface to Phidgets pressure sensors and RFID sensors  ([http://www.ros.org/wiki/phidgets_py_api phidgets_py_api], [http://www.ros.org/wiki/phidgets_ros phidgets_ros]). 
      * Two interfaces to the Videre STOC camera: The first ([http://www.ros.org/wiki/videre_stereo_cam videre_stereo_cam]) uses standard !FireWire libraries to get the left and right images from the camera. The second ([http://www.ros.org/wiki/videre_stoc videre_stoc]) relies on Videre's proprietary SVS software packages to pull the left image and disparity image from the STOC.

      ===ua_controllers===

      This package contains controllers for the Wubble Robot, which generally depend on ua_drivers for interfacing with the hardware. Thus, packages in this stack provide a higher level of abstraction for robot subsystems than ua_drivers. 
        * Controllers for the !SmartArm ([http://www.ros.org/wiki/smart_arm_controller smart_arm_controller]), tilting laser ([http://www.ros.org/wiki/wubble_laser_tilt_controller wubble_laser_tilt_controller]), and head pan/tilt ([http://www.ros.org/wiki/wubble_camera_pan_tilt_controller wubble_camera_pan_tilt_controller]). 
          * Actions interfaces for all of the above controllers ([http://www.ros.org/wiki/wubble_actions wubble_actions]).
            * Message types common to all controllers ([http://www.ros.org/wiki/ua_controller_msgs ua_controller_msgs]).
              * A control system for a simple Bioloid robot used in some Wizard-of-Oz experiments at UA ([http://www.ros.org/wiki/ccs ccs]).
                * An inverse kinematics (IK) solver for the !SmartArm, created with OpenRAVE ([http://www.ros.org/wiki/smart_arm_kinematics smart_arm_kinematics]).

                ===ua_robots===

                This stack contains URDF description files, 3D models, and configuration parameters for the Wubble Robot. For a component-by-component description of the robot, see WubbleRobot.

                ===ua_vision===

                Contains packages related to vision on UA robots, including saliency tracking based on the 

                  * A ROS wrapper for Nick's Machine Perception Toolkit ([http://www.ros.org/wiki/nmpt nmpt]), a vision library providing algorithms for computing salient parts of an image. This information is used for saliency-based tracking ([http://www.ros.org/wiki/saliency_tracking saliency_tracking]). 

                  ===ua_cognition===

                  This stack will contain the cognitive architecture for the Wubble Robot. Currently, it contains a ROS wrapper for the GBBopen blackboard system ([http://www.ros.org/wiki/gbbopen gbbopen]).

                  ===ua_apps===

                    * Wubble Robot Gmapping application ([http://www.ros.org/wiki/wubble_mapping wubble_mapping]).
                      * Wubble Robot teleoperation ([http://www.ros.org/wiki/wubble_teleop wubble_teleop]).
                        * Wubble Robot blocks stacking ([http://www.ros.org/wiki/wubble_blocks wubble_blocks]).

                        ===wubble_world===

                        This stack contains world files for the Gazebo simulator (in [http://www.ros.org/wiki/wubble_environments wubble_environments]), intended as an environment for continuing the [http://www.wubbleworld.com Wubble World] project.
