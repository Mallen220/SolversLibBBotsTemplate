package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.CRServo;

import org.firstinspires.ftc.teamcode.Constants;

public class HorizontalExtension extends SubsystemBase {
  private final CRServo rightExtensionServo;
  private final CRServo leftExtensionServo;

  private final double MAX_POWER = 1;
  private final double MIN_POWER = -1;

  public HorizontalExtension(HardwareMap hwMap) {
    rightExtensionServo = new CRServo(hwMap, Constants.HorizontalConstants.RIGHT_EXTENSION_ID);
    leftExtensionServo = new CRServo(hwMap, Constants.HorizontalConstants.LEFT_EXTENSION_ID);
    rightExtensionServo.setInverted(true);
  }

  public void setPower(double power) {
    if (power < MAX_POWER && power > MIN_POWER) {
      leftExtensionServo.set(power);
      rightExtensionServo.set(power);
    }
  }

  public void extend() {
    setPower(Constants.HorizontalConstants.EXTENSION_POWER);
  }

  public void retract() {
    setPower(-Constants.HorizontalConstants.EXTENSION_POWER);
  }

  public void stopServos() {
    setPower(0);
  }
}
