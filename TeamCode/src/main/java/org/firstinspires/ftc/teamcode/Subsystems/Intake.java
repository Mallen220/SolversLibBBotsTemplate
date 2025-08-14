package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.ServoEx;
import com.seattlesolvers.solverslib.hardware.SimpleServo;
import com.seattlesolvers.solverslib.hardware.motors.CRServo;

import org.firstinspires.ftc.teamcode.Constants.IntakeConstants;
import org.firstinspires.ftc.teamcode.Constants.IntakePosition;

public class Intake extends SubsystemBase { // This class was left mostly unchanged. You might need to add more methods or change the existing ones to suit your needs.

  private IntakePosition goalPositionClaw;
  private IntakePosition goalPositionFlip;
  private final ServoEx clawIntakeServo;
  private final ServoEx intakeFlipServo;
  private final CRServo clawRotationServo;
  private final double minDegree = 0, maxDegree = 0;

  public Intake(final HardwareMap hwMap) {
    clawIntakeServo = new SimpleServo(hwMap, IntakeConstants.CLAW_INTAKE_SERVO_ID, minDegree, maxDegree);
    intakeFlipServo = new SimpleServo(hwMap, IntakeConstants.INTAKE_FLIP_SERVO_ID, minDegree, maxDegree);
    clawIntakeServo.setInverted(true);
    clawRotationServo = new CRServo(hwMap, IntakeConstants.CLAW_ROTATION_SERVO_ID);
    clawRotationServo.setInverted(true);
  }

  public void goToPositionFlip(final IntakePosition position) {
    if (position.position >= minDegree && position.position <= maxDegree) {
      intakeFlipServo.setPosition(position.position);
    }
    goalPositionFlip = position;
  }

  public void openClaw() {
    clawIntakeServo.setPosition(IntakePosition.CLAW_OPEN_POSITION.position);
    goalPositionClaw = IntakePosition.CLAW_OPEN_POSITION;
  }

  public void closeClaw() {
    clawIntakeServo.setPosition(IntakePosition.CLAW_CLOSE_POSITION.position);
    goalPositionClaw = IntakePosition.CLAW_CLOSE_POSITION;
  }

  public IntakePosition getGoalPositionClaw() {
    return goalPositionClaw;
  }

  public void rotateIntake(double power) {
    clawRotationServo.set(power);
  }

  public void stopRotate() {
    clawRotationServo.stop();
  }

  public IntakePosition[] getGoalPositions() {
    return new IntakePosition[] {goalPositionClaw, goalPositionFlip};
  }
}
