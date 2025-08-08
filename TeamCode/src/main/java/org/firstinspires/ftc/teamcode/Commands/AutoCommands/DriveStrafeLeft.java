package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Commands.OuttakeArmToPosition;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Outtake;
import org.firstinspires.ftc.teamcode.Subsystems.OuttakeArm;
import org.firstinspires.ftc.teamcode.Subsystems.VerticalArm;

/*
 * Not changed, this is an example and should not be used next season.
 */
public class DriveStrafeLeft extends SequentialCommandGroup {
  public DriveStrafeLeft(Drivetrain drive, long cm) {
    addCommands(
        new InstantCommand(() -> drive.driveRobotCentric(0, -0.5, 0)),
        new WaitCommand(cm * (1000 / 37)),
        new InstantCommand(drive::stopMotors));
  }
}
