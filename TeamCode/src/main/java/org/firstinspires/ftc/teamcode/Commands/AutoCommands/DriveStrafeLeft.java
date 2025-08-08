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

public class DriveStrafeLeft extends SequentialCommandGroup {
  public DriveStrafeLeft(Drivetrain drive, long cm) {
    addCommands(
        new InstantCommand(() -> drive.driveRobotCentric(0, -0.5, 0)),
        new WaitCommand(cm * (1000 / 37)),
        new InstantCommand(drive::stopMotors));
  }

    public static class AutonomousScoreSpecimen extends SequentialCommandGroup {
      public AutonomousScoreSpecimen(
          Drivetrain drive,
          OuttakeArm outtakeArm,
          Outtake outtake,
          VerticalArm verticalArm,
          long cm) {
        addCommands(
            // Go to High Bar
            new OuttakeArmToPosition(outtakeArm, Constants.OuttakeArmPosition.GO_TO_HIGH_BAR_POSITION),
            new WaitCommand(500),
            new InstantCommand(outtake::closeClaw),
            new WaitCommand(100),
            new InstantCommand(outtake::closeClaw),
            new InstantCommand(() -> verticalArm.setArmPowers(0.9)),
            new WaitCommand(cm * (1000 / 121)),
            new InstantCommand(() -> verticalArm.setArmPowers(0)),

            // Go to High bar
            new OuttakeArmToPosition(outtakeArm, Constants.OuttakeArmPosition.GO_TO_HIGH_BAR_POSITION),
            new WaitCommand(500),
            new InstantCommand(outtake::closeClaw),
            new WaitCommand(100),
            new InstantCommand(outtake::closeClaw),
            new InstantCommand(() -> verticalArm.setArmPowers(0.9)),
            new WaitCommand(cm * (1000 / 121)),
            new InstantCommand(() -> verticalArm.setArmPowers(0.0)),

            // Backwards
            new DriveBackward(drive, 50),

            // Score Specimen
            new InstantCommand(outtake::closeClaw),
            new OuttakeArmToPosition(outtakeArm, Constants.OuttakeArmPosition.GO_TO_HIGH_BAR_POSITION),
            new InstantCommand(() -> verticalArm.setArmPowers(-0.9)),
            new WaitCommand(cm * (1000 / 121)),
            new InstantCommand(() -> verticalArm.setArmPowers(0)),
            new WaitCommand(100),
            new InstantCommand(outtake::openClaw),
            new WaitCommand(500));
      }
    }
}
