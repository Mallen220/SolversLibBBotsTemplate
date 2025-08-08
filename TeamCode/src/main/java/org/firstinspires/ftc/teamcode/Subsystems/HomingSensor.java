package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import org.firstinspires.ftc.teamcode.Constants;

public class HomingSensor extends SubsystemBase {
  private final TouchSensor homingSensor;

  private final HardwareMap hwMap;

  public HomingSensor(HardwareMap hwMap) {
    this.hwMap = hwMap;

    homingSensor = hwMap.touchSensor.get(Constants.HomingSensorConstants.SENSOR_ID);
  }

  public boolean isPressed() {
    return homingSensor.isPressed();
  }
}
