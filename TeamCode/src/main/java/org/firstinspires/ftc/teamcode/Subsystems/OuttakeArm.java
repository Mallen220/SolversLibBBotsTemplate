package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.ServoEx;
import com.seattlesolvers.solverslib.hardware.SimpleServo;

import org.firstinspires.ftc.teamcode.Constants.*;

public class OuttakeArm extends SubsystemBase { // This class was left mostly unchanged. You might need to add more methods or change the existing ones to suit your needs.

  private OuttakeArmPosition goalPosition;
  private final ServoEx clawArm;

  public OuttakeArm(final HardwareMap hwMap) {
    clawArm = new SimpleServo(hwMap, OuttakeArmConstants.CLAW_ARM_SERVO_ID, 0, 1);
    clawArm.setInverted(true);
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
