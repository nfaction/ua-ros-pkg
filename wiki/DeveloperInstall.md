#summary Instructions for Developers to Setup ROS with ua-ros-pkg

  # If the machine has never had ROS installed it, do this first:
  {{{
  sudo apt-get install build-essential python-yaml cmake subversion git-core mercurial python-setuptools
  }}}
    # Install the rosinstall program
    {{{
    sudo easy_install -U rosinstall
    }}}
      # Install ROS!
      {{{
      rosinstall ~/ros /opt/ros/electric http://ua-ros-pkg.googlecode.com/svn/trunk/config/electric-deb.rosinstall
      }}}
        # Update your .bashrc
        {{{
        echo "source ~/ros/setup.bash" >> ~/.bashrc
        echo "export ROBOT=sim" >> ~/.bashrc
        source ~/.bashrc
        }}}
          # Run the script that makes all the UA packages
          {{{
          ~/ros/ua-ros-pkg/make-all.sh
          }}}
