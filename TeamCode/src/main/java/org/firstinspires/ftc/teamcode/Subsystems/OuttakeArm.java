package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import org.firstinspires.ftc.teamcode.Constants.*;

public class OuttakeArm extends SubsystemBase { // This class was left mostly unchanged. You might need to add more methods or change the existing ones to suit your needs.

  private OuttakeArmPosition goalPosition;
  private final Servo clawArm;

  public OuttakeArm(final HardwareMap hwMap) {
    clawArm = hwMap.get(Servo.class, OuttakeArmConstants.CLAW_ARM_SERVO_ID);
    clawArm.setDirection(Servo.Direction.REVERSE);
  }

  public void goToPosition(final OuttakeArmPosition position) {
    if (position.position >= Servo.MIN_POSITION && position.position <= Servo.MAX_POSITION) {
      clawArm.setPosition(position.position);
    }
    goalPosition = position;
  }

  public OuttakeArmPosition getGoalPosition() {
    return goalPosition;
  }
}
