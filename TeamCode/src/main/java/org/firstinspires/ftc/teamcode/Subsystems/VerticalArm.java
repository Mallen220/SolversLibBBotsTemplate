package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDController;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Constants.*;

public class VerticalArm extends SubsystemBase {

  private final DcMotor leftArm;
  private final DcMotor rightArm;
  private final PIDController pidController;
  private ArmPosition goalPosition;
  private boolean isPidActive = false;

  public VerticalArm(final HardwareMap hwMap) {
    leftArm = hwMap.dcMotor.get(Constants.ArmConstants.LEFT_ARM_ID);
    rightArm = hwMap.dcMotor.get(Constants.ArmConstants.RIGHT_ARM_ID);

    leftArm.setDirection(DcMotor.Direction.REVERSE);

    leftArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    rightArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    resetEncoders();
//    setRunWithoutEncoder();

    // Initialize PID controller with gains from constants
    pidController =
        new PIDController(ArmConstants.ARM_KP, ArmConstants.ARM_KI, ArmConstants.ARM_KD);
    pidController.setTolerance(ArmConstants.PID_TOLERANCE);
  }

  private void resetEncoders() {
    leftArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    rightArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//    setRunWithoutEncoder();
  }

  private void setRunWithoutEncoder() {
    leftArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    rightArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
  }

  public void goToPosition(final ArmPosition position) {
    goalPosition = position;
    pidController.setSetPoint(position.encoderTicks);
    pidController.reset(); // Clear accumulated error
    isPidActive = true;
  }

  public void stop() {
    isPidActive = false;
    leftArm.setPower(0);
    rightArm.setPower(0);
  }

  @Override
  public void periodic() {
    if (isPidActive) {
      double currentPosition = getPosition();

      // Safety checks
      if (isOutOfBounds() || isMotorDifferenceExceeded()) {
        stop();
        return;
      }

      // Calculate PID output and apply to motors
      double power = pidController.calculate(currentPosition);
      setArmPowers(power);

      // Stop PID when target is reached
      if (pidController.atSetPoint()) {
        stop();
      }
    }
  }

  private double getPosition() { // Returns average position of both arms, should be about the same.
    double[] positions = getCurrentPosition();
    return (positions[0] + positions[1]) / 2.0;
  }

  private boolean isOutOfBounds() {
    double[] positions = getCurrentPosition();
    return (positions[0] < ArmConstants.VERTICAL_MIN_POSITION
        || positions[0] > ArmConstants.VERTICAL_MAX_POSITION
        || positions[1] < ArmConstants.VERTICAL_MIN_POSITION
        || positions[1] > ArmConstants.VERTICAL_MAX_POSITION);
  }

  private boolean isMotorDifferenceExceeded() {
    double[] positions = getCurrentPosition();
    return Math.abs(positions[0] - positions[1]) > ArmConstants.MAX_ALLOWED_DIFFERENCE;
  }

  public double[] getCurrentPosition() {
    return new double[] {
      Math.abs(leftArm.getCurrentPosition()), Math.abs(rightArm.getCurrentPosition())
    };
  }

  public ArmPosition getGoalPosition() {
    return goalPosition;
  }

  public void setArmPowers(final double power) {
    // Disable PID when manually setting power
    isPidActive = false;
    leftArm.setPower(power);
    rightArm.setPower(power);
  }
}
