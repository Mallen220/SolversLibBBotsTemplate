package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.command.Subsystem;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Utils;

public class DriveCommand extends CommandBase {
  private final Drivetrain drive;
  private final GamepadEx gamepad;

  private final double tolerance = 0.1; // Tolerance for joystick input

  public DriveCommand(Drivetrain drive, GamepadEx gamepad) {
    this.drive = drive;
    this.gamepad = gamepad;
    addRequirements((Subsystem) drive);
  }

  public void initialize() {
    // Nothing to do here
  }

  @Override
  public void execute() {

    if (!Utils.isWithinTolerance(0, gamepad.getLeftY(), tolerance)
        || !Utils.isWithinTolerance(0, gamepad.getLeftX(), tolerance)
        || !Utils.isWithinTolerance(0, gamepad.getRightX(), tolerance)) {
      drive.driveFieldCentric(gamepad);
    } else {
      drive.stopMotors();
    }
  }

  public void end(boolean interrupted) {
    drive.stopMotors(); // Stop the drive motors when the command ends
  }

  @Override
  public boolean isFinished() {
    return false; // This command runs until explicitly stopped
  }
}
