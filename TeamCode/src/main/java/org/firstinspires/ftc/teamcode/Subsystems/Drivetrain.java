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
    backLeftMotor = ...;
    frontRightMotor = ...;
    backRightMotor = ...;

    frontLeftMotor.; //Invert this motor!
    backLeftMotor.; //Invert this motor!

    frontLeftMotor.setRunMode(Motor.RunMode.); // Set the run mode for the motors! Read the docs if you don't know what this is or what to do here!
    frontRightMotor.;
    backLeftMotor.;
    backRightMotor.;

    // Retrieve the IMU from the hardware map
    revIMU = new RevIMU(...); // Constants.DriveConstants.IMU_ID

    revIMU.init(); // FIXME: The orientation is very likely wrong. Needs tested.

    /* Old parameters settings */
    // Adjust the orientation parameters to match your robot
    //    IMU.Parameters parameters =
    //        new IMU.Parameters(
    //            new RevHubOrientationOnRobot(
    //                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
    //                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));

    drive = new MecanumDrive(...); // Read the docs
  }

  public void resetYaw() {
    // FIXME: We need to do 2 things here!
    // 1. Reset the IMU's heading to 0
    // 2. Reset our class variable to 0
  }

  public double getBotHeading() { // Returns the robot's heading in radians

    // FIXME: This should be the imu's heading minus the class variable offset
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
  public void setPowers(double fLPower, double fRPower, double bLPower, double bRPower) { //This is an old method, left here for reference but not used.

    frontLeftMotor.set(fLPower);
    frontRightMotor.set(fRPower);
    backLeftMotor.set(bLPower);
    backRightMotor.set(bRPower);
  }

  public void stopMotors() { // Stops all motors
    //FIXME: Stop all motors
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
    drive.driveRobotCentric(...); // FIXME: Look at the MecanumDrive docs to see what parameters you need to pass in here.
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
        ..., // FIXME: Look at the MecanumDrive docs to see what parameters you need to pass in here.
        ...,
        ...,
        revIMU.getRotation2d().getDegrees(), // gyro value passed in here must be in degrees
        false);
  }
}
