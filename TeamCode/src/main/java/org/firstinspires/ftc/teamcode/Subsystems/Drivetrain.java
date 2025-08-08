package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.hardware.RevIMU;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import org.firstinspires.ftc.teamcode.Constants;

public class Drivetrain extends SubsystemBase {
  // Declare our motors
  private final Motor frontLeftMotor;
  private final Motor backLeftMotor;
  private final Motor frontRightMotor;
  private final Motor backRightMotor;

  private final RevIMU revIMU;

  private double fieldHeadingOffset = 0.0; // in radians

  private final HardwareMap hwMap;

  private MecanumDrive drive = null;

  // Make sure your ID's match your configuration
  public Drivetrain(final HardwareMap hwMap) {
    this.hwMap = hwMap;

    frontLeftMotor = new Motor(hwMap, Constants.DriveConstants.FRONT_LEFT_MOTOR_ID);
    backLeftMotor = new Motor(hwMap, Constants.DriveConstants.BACK_LEFT_MOTOR_ID);
    frontRightMotor = new Motor(hwMap, Constants.DriveConstants.FRONT_RIGHT_MOTOR_ID);
    backRightMotor = new Motor(hwMap, Constants.DriveConstants.BACK_RIGHT_MOTOR_ID);

    frontLeftMotor.setInverted(true);
    backLeftMotor.setInverted(true);

    frontLeftMotor.setRunMode(Motor.RunMode.RawPower); // Let's use encoders for odometry
    frontRightMotor.setRunMode(Motor.RunMode.RawPower); // Let's use encoders for odometry
    backLeftMotor.setRunMode(Motor.RunMode.RawPower); // Let's use encoders for odometry
    backRightMotor.setRunMode(Motor.RunMode.RawPower); // Let's use encoders for odometry

    // Retrieve the IMU from the hardware map
    revIMU = new RevIMU(hwMap, Constants.DriveConstants.IMU_ID);

    revIMU.init(); // FIXME:  The orientation is very likely wrong. Needs tested.

    // Adjust the orientation parameters to match your robot
    //    IMU.Parameters parameters =
    //        new IMU.Parameters(
    //            new RevHubOrientationOnRobot(
    //                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
    //                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));

    drive = new MecanumDrive(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor);
  }

  public void resetYaw() {
    revIMU.reset();
    fieldHeadingOffset = 0.0; // Reset the field heading offset
  }

  public double getBotHeading() { // Returns the robot's heading in radians
    return revIMU.getHeading() - fieldHeadingOffset;
  }

  public void setFieldHeadingOffset(final double newOffset) {
    fieldHeadingOffset = newOffset;
  }

  /**
   * Sets exact motor powers
   *
   * @param fLPower = frontLeft speed, -1 to 1
   * @param fRPower = frontRight speed, -1 to 1
   * @param bLPower = backLeft speed, -1 to 1
   * @param bRPower = backRight speed, -1 to 1
   */
  public void setPowers(double fLPower, double fRPower, double bLPower, double bRPower) {

    frontLeftMotor.set(fLPower);
    frontRightMotor.set(fRPower);
    backLeftMotor.set(bLPower);
    backRightMotor.set(bRPower);
  }

  public void stopMotors() {
    frontLeftMotor.stopMotor();
    frontRightMotor.stopMotor();
    backRightMotor.stopMotor();
    backLeftMotor.stopMotor();
  }

  public void driveRobotCentric(GamepadEx Controller) {
    driveRobotCentric(-Controller.getLeftY(), Controller.getLeftX(), Controller.getRightX());
  }

  public void driveRobotCentric(double forward, double strafe, double rotate) {
    // For a robot centric model, the input of (0,1,0) for (leftX, leftY, rightX)
    // will move the robot in the direction of its current heading. Every movement
    // is relative to the frame of the robot itself.
    //
    //                 (0,1,0)
    //                   /
    //                  /
    //           ______/_____
    //          /           /
    //         /           /
    //        /___________/
    //           ____________
    //          /  (0,0,1)  /
    //         /     ↻     /
    //        /___________/

    // optional fourth parameter for squared inputs
    drive.driveRobotCentric(strafe, forward, rotate, false);
  }

  public void driveFieldCentric(GamepadEx Controller) {
    driveFieldCentric(-Controller.getLeftY(), Controller.getLeftX(), Controller.getRightX());
  }

  public void driveFieldCentric(double forward, double strafe, double rotate) {
    // Below is a model for how field centric will drive when given the inputs
    // for (leftX, leftY, rightX). As you can see, for (0,1,0), it will travel forward
    // regardless of the heading. For (1,0,0) it will strafe right (ref to the 0 heading)
    // regardless of the heading.
    //
    //                   heading
    //                     /
    //            (0,1,0) /
    //               |   /
    //               |  /
    //            ___|_/_____
    //          /           /
    //         /           / ---------- (1,0,0)
    //        /__________ /

    // optional fifth parameter for squared inputs
    drive.driveFieldCentric(
        strafe,
        forward,
        rotate,
        revIMU.getRotation2d().getDegrees(), // gyro value passed in here must be in degrees
        false);
  }
}
