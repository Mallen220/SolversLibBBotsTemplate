package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.command.Subsystem;

import org.firstinspires.ftc.teamcode.Subsystems.HomingSensor;
import org.firstinspires.ftc.teamcode.Subsystems.HorizontalExtension;

public class HorizontalRetractUntilPressed extends CommandBase {
  private final HorizontalExtension horizontal;
  private final HomingSensor sensor;

  public HorizontalRetractUntilPressed(HorizontalExtension horizontal, HomingSensor sensor) {
    this.horizontal = horizontal;
    this.sensor = sensor;
    addRequirements((Subsystem) ...);
  }

  @Override
  public void initialize() {
    ... // Do something here!
  }

  @Override
  public boolean isFinished() {
    // When does this command finish? There is a specific condition that needs to be met.
  }

  @Override
  public void end(boolean interrupted) {
    //When the condition is met, what should happen? If nothing, leave this empty. If empty then you're wrong.
  }
}
