

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegister;

/**
 * Register Op Modes
 */
public class FtcOpModeRegister implements OpModeRegister {

  /**
   * The Op Mode Manager will call this method when it wants a list of all
   * available op modes. Add your op mode to the list to enable it.
   *
   * @param manager op mode manager
   */
  public void register(OpModeManager manager) {

    /*
     * register your op modes here.
     * The first parameter is the name of the op mode
     * The second parameter is the op mode class property
     *
     * If two or more op modes are registered with the same name, the app will display an error.
     */


    manager.register ("Tank Drive", WWTankDrive.class);
    manager.register ("Teleop",Teleop.class);
    manager.register ("Red",TeleopRed.class);
    manager.register ("Blue",TeleopBlue.class);
    manager.register ("Auto Blue Floor", WWAutoBlueFloor.class);
    manager.register ("Auto Red Floor", WWAutoRedFloor.class);
    manager.register ("Auto LeadScrew Test",WWLeadScrewTest.class);
    manager.register ("Climb Red Side",ClimbRed.class);


    
    
  }
}
