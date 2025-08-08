package org.firstinspires.ftc.teamcode.autonomous;

import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.DriveForward;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;

public class DriveForwardAuto extends SequentialCommandGroup {
  public DriveForwardAuto(Drivetrain drive) {
    addCommands(
        // Movement sequence
        new DriveForward(drive, 45));
  }
}
