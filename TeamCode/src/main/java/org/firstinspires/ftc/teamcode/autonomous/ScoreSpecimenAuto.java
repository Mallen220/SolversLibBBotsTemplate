package org.firstinspires.ftc.teamcode.autonomous;

import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.DriveBackward;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.DriveForward;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.DriveStrafeRight;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.RotateLeft;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.RotateRight;
import org.firstinspires.ftc.teamcode.Commands.AutonomousScoreSpecimen;
import org.firstinspires.ftc.teamcode.Commands.HorizontalRetractUntilPressed;
import org.firstinspires.ftc.teamcode.Commands.IntakeGoToFlipPosition;
import org.firstinspires.ftc.teamcode.Commands.OuttakeArmToPosition;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.HomingSensor;
import org.firstinspires.ftc.teamcode.Subsystems.HorizontalExtension;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Outtake;
import org.firstinspires.ftc.teamcode.Subsystems.OuttakeArm;
import org.firstinspires.ftc.teamcode.Subsystems.VerticalArm;

public class ScoreSpecimenAuto extends SequentialCommandGroup {
  public ScoreSpecimenAuto(
      Drivetrain drive,
      Intake intake,
      Outtake outtake,
      OuttakeArm outtakeArm,
      VerticalArm verticalArm,
      HorizontalExtension horizontal,
      HomingSensor homingSensor) {
    addCommands(
        new OuttakeArmToPosition(outtakeArm, Constants.OuttakeArmPosition.AUTONOMOUS_POSITION),
        new IntakeGoToFlipPosition(intake, Constants.IntakePosition.FLIP_TRANSFER_POSITION),
        new HorizontalRetractUntilPressed(horizontal, homingSensor),

        // score first specimen
        new DriveBackward(drive, 50),
        new DriveStrafeRight(drive, 54),
        new RotateRight(drive, 2),
        new DriveBackward(drive, 50),
        new DriveStrafeRight(drive, 54),
        new RotateRight(drive, 2),
        new AutonomousScoreSpecimen(drive, outtakeArm, outtake, verticalArm, 65),

        // score second specimen
        new DriveForward(drive, 20),
        new WaitCommand(2000),
        new RotateLeft(drive, 180),
        new DriveStrafeRight(drive, 90),
        new DriveForward(drive, 130),
        new DriveStrafeRight(drive, 27),
        new RotateLeft(drive, 50),
        new DriveBackward(drive, 165),
        new DriveForward(drive, 40),
        new WaitCommand(3000),
        new DriveBackward(drive, 30),
        new DriveStrafeRight(drive, 54),
        new RotateRight(drive, 1),
        new AutonomousScoreSpecimen(drive, outtakeArm, outtake, verticalArm, 90));
  }
}
