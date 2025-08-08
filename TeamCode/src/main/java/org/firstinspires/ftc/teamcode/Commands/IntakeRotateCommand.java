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
    addRequirements(...);
  }

  public void initialize() {
    intake.goToPositionFlip(
        Constants.IntakePosition
            .FLIP_TRANSFER_POSITION); // Initialize intake to flip transfer position
    // Is this correct? Should we set the position here? Could this lead to an issue?
    // If it's wrong, we can remove this line. Otherwise, we can keep it.
    // Justify this decision in a comment or two please.
  }

  @Override
  public void execute() {
    if (!isWithinTolerance(0, gamepad.getRightX(), tolerance)) {
      ... // What should we do here? Should we rotate the intake based on the joystick input? Look at the old code for reference.
    } else {
      ... // If the joystick isn't moved, what should we do?
    }
  }

  public void end(boolean interrupted) {
    // We need something here (or we will break the bot). Consider the state of the intake when this command ends.
  }

  @Override
  public boolean isFinished() {
    return false; // Why is this return false? What might that be indicative of?
  }
}
