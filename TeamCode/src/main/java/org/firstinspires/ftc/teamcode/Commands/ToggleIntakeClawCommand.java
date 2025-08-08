package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.command.Subsystem;
import com.seattlesolvers.solverslib.command.WaitCommand;
import org.firstinspires.ftc.teamcode.Constants.IntakePosition;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;

public class ToggleIntakeClawCommand extends CommandBase {
  private final Intake claw;
  private IntakePosition initialPosition;

  public ToggleIntakeClawCommand(Intake claw) {
    this.claw = claw;
    addRequirements((Subsystem) claw);
  }

  public void initialize() {
    initialPosition = claw.getGoalPositionClaw();
  }

  @Override
  public void execute() {
    switch (initialPosition) {
      case CLAW_OPEN_POSITION:
        claw.closeClaw();
        break;
      case CLAW_CLOSE_POSITION:
        claw.openClaw(); // Not needed because of default case, but included for clarity.
        break;
      default:
        claw.openClaw();
        break;
    }
    new WaitCommand( // TODO: Test wait time
        100); // Wait for 100 milliseconds to allow the claw to move. Acts as a debounce.
  }

  public void end(boolean interrupted) {
    // No specific action needed on end
  }

  public boolean isFinished() {
    return true; // This command runs just once
  }
}
