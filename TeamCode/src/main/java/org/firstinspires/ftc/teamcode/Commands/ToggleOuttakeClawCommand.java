package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.command.Subsystem;
import com.seattlesolvers.solverslib.command.WaitCommand;
import org.firstinspires.ftc.teamcode.Constants.OuttakePosition;
import org.firstinspires.ftc.teamcode.Subsystems.Outtake;

public class ToggleOuttakeClawCommand extends CommandBase {
  private final Outtake claw;
  private OuttakePosition initialPosition;

  public ToggleOuttakeClawCommand(Outtake claw) {
    this.claw = claw;
    addRequirements((Subsystem) claw);
  }

  public void initialize() {
    initialPosition = claw.getGoalPosition();
  }

  @Override
  public void execute() {
    switch (initialPosition) {
      case OPEN_POSITION:
        ...
        break;
      case CLOSE_POSITION:
        ... // Not needed because of default case, but included for clarity.
        break;
      default:
        ...
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
