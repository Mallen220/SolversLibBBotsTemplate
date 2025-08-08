package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;

public class ResetHeading extends SequentialCommandGroup {
  private final Drivetrain drive;

  public ResetHeading(Drivetrain drive) {
    this.drive = drive;
    addRequirements((Subsystem) drive);
  }

  public void initialize() {
    // Nothing to do here
  }

  @Override
  public void execute() { // Will only iterate once since is finished is true
    double initialHeading = drive.getBotHeading();
    drive.resetYaw();
    drive.setFieldHeadingOffset(initialHeading + Math.PI);
  }

  @Override
  public boolean isFinished() {
    return true; // This command finishes immediately after resetting the heading
  }
}
