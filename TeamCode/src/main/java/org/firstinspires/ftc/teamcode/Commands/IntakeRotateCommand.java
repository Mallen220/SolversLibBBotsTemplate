package org.firstinspires.ftc.teamcode.Commands;

import static org.firstinspires.ftc.teamcode.Utils.isWithinTolerance;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.command.Subsystem;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;

public class IntakeRotateCommand extends CommandBase {
  private final Intake intake;
  private final GamepadEx gamepad;

  private final double tolerance = 0.1; // Tolerance for joystick input

  public IntakeRotateCommand(Intake intake, GamepadEx gamepad) {
    this.intake = intake;
    this.gamepad = gamepad;
    addRequirements((Subsystem) intake);
  }

  public void initialize() {
    intake.goToPositionFlip(
        Constants.IntakePosition
            .FLIP_TRANSFER_POSITION); // Initialize intake to flip transfer position
  }

  @Override
  public void execute() {
    if (!isWithinTolerance(0, gamepad.getRightX(), tolerance)) {
      intake.rotateIntake(gamepad.getRightX());
    } else {
      intake.stopRotate();
    }
  }

  public void end(boolean interrupted) {
    intake.stopRotate(); // Stop the rotation when the command ends
  }

  @Override
  public boolean isFinished() {
    return false; // This command runs until explicitly stopped
  }
}
