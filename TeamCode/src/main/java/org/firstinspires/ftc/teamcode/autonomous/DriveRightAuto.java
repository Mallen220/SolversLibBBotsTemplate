package org.firstinspires.ftc.teamcode.autonomous;

import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.DriveStrafeRight;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;

public class DriveRightAuto extends SequentialCommandGroup {
  public DriveRightAuto(Drivetrain drive) {
    addCommands(
        // Movement sequence
        new DriveStrafeRight(drive, 70));
  }
}
