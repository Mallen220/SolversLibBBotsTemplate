package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.command.Subsystem;
import com.seattlesolvers.solverslib.command.WaitCommand;
import org.firstinspires.ftc.teamcode.Constants.IntakePosition;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;

public class ToggleIntakeClawCommand extends CommandBase { // Use ToggleOuttakeClawCommand as an example for this!!
  private final Intake claw;
  private IntakePosition initialPosition;

  public ToggleIntakeClawCommand(Intake claw) {
    this.claw = ...;
    addRequirements(...);
  }

  public void initialize() {
    ...
  }

  @Override
  public void execute() {
    switch (initialPosition) {
      case ... // What are the possible positions for the intake claw?:
        ...
        break;
      case ...:
        ... // Not needed because of default case, but included for clarity.
        break;
      default:
        ...
        break;
    }
    new WaitCommand( // TODO: Test wait time
            ...);
  }

  public void end(boolean interrupted) {
    // What should happen when the command ends? If nothing, leave this empty.
  }

  public boolean isFinished() {
   /* When is this done? If this command runs just once, return true. It it never ends return false. */
  }
}
