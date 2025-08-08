package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDController;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Constants.*;

/*This class was heavily modified to use PID control for the vertical arm movement.
It is not configured to use encoders, but it can be easily adapted to do so. The PID
controller is used to control the arm's position based on the desired goal position.
The PID is not tuned right now so is considered dangerous to run without tuning first.
The is also an option to turn PID on or off. */
public class VerticalArm extends SubsystemBase {

  private final DcMotor leftArm;
  private final DcMotor rightArm;
  private final PIDController pidController;
  private ArmPosition goalPosition;
  private boolean isPidActive = false;

  public VerticalArm(final HardwareMap hwMap) {
    leftArm = hwMap.dcMotor.get(Constants.ArmConstants.LEFT_ARM_ID);
    rightArm = hwMap.dcMotor.get(Constants.ArmConstants.RIGHT_ARM_ID);

    leftArm.; // Invert this motor

    leftArm.; // Brake when power is set to zero?? Or should it do something else?
    rightArm.; // Brake when power is set to zero?? Or should it do something else?

    resetEncoders();

    // Initialize PID controller with gains from constants
    pidController =
        new PIDController(ArmConstants.ARM_KP, ArmConstants.ARM_KI, ArmConstants.ARM_KD); //TODO: Configure PID gains!!
    pidController.setTolerance(ArmConstants.PID_TOLERANCE); // Tune this!
  }

  private void resetEncoders() {
    leftArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    rightArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    // setRunWithoutEncoder();
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
    leftArm.; // How do we stop the left arm?
    rightArm.; // How do we stop the right arm?
  }

  @Override
  public void periodic() { // You likely won't need to change this method. It is untested though so be careful!
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
      if (pidController.atSetPoint()) { // Automatically checks if the current position is within the tolerance of the set point
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
