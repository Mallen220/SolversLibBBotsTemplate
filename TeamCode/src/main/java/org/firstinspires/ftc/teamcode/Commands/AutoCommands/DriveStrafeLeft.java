package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;

public class DriveStrafeLeft extends SequentialCommandGroup {
  public DriveStrafeLeft(Drivetrain drive, long cm) {
    addCommands(
        new InstantCommand(() -> drive.driveRobotCentric(0, -0.5, 0)),
        new WaitCommand(cm * (1000 / 37)),
        new InstantCommand(drive::stopMotors));
  }
}
