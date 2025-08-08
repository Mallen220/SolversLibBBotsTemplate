package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.Subsystems.HomingSensor;
import org.firstinspires.ftc.teamcode.Subsystems.HorizontalExtension;

public class HorizontalRetractUntilPressed extends CommandBase {
  private final HorizontalExtension horizontal;
  private final HomingSensor sensor;

  public HorizontalRetractUntilPressed(HorizontalExtension horizontal, HomingSensor sensor) {
    this.horizontal = horizontal;
    this.sensor = sensor;
    addRequirements(horizontal);
  }

  @Override
  public void initialize() {
    horizontal.retract();
  }

  @Override
  public boolean isFinished() {
    return sensor.isPressed();
  }

  @Override
  public void end(boolean interrupted) {
    horizontal.stopServos();
  }
}
