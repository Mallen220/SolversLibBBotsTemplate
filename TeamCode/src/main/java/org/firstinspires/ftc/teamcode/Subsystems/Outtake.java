package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.ServoEx;
import com.seattlesolvers.solverslib.hardware.SimpleServo;

public class Outtake extends SubsystemBase { // This class was left mostly unchanged. You might need to add more methods or change the existing ones to suit your needs.
import org.firstinspires.ftc.teamcode.Constants.OuttakeConstants;
import org.firstinspires.ftc.teamcode.Constants.OuttakePosition;

public class Outtake extends SubsystemBase {

  private final double MAX_POSITION = 1;
  private final double MIN_POSITION = 0;
  private OuttakePosition goalPosition;
  private final ServoEx outtake;

  public Outtake(final HardwareMap hwMap) {
    outtake = new SimpleServo(hwMap, OuttakeConstants.CLAW_SERVO_ID, MIN_POSITION, MAX_POSITION);
    outtake.setInverted(true);
    goalPosition = OuttakePosition.OPEN_POSITION;
  }

  public void openClaw() {
    setPosition(OuttakePosition.OPEN_POSITION.position);
    goalPosition = OuttakePosition.OPEN_POSITION;
  }

  public void closeClaw() {
    setPosition(OuttakePosition.CLOSE_POSITION.position);
    goalPosition = OuttakePosition.CLOSE_POSITION;
  }

  public OuttakePosition getGoalPosition() {
    return goalPosition;
  }

  public void setPosition(final double position) {
    if (position >= MIN_POSITION && position <= MAX_POSITION) {
      outtake.setPosition(position);
    }
  }

  public double getClawPosition() {
    return outtake.getPosition();
  }
}
