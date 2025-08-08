package org.firstinspires.ftc.teamcode.autonomous;

import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.DriveBackward;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.DriveForward;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.DriveStrafeLeft;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.DriveStrafeRight;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.RotateRight;
import org.firstinspires.ftc.teamcode.Commands.HorizontalRetractUntilPressed;
import org.firstinspires.ftc.teamcode.Commands.IntakeGoToFlipPosition;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.HomingSensor;
import org.firstinspires.ftc.teamcode.Subsystems.HorizontalExtension;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;

/**
 * Since our goal is to not use this type of command next season, this is an example of how
 * to structure a command group for scoring a specimen in autonomous mode. We will not be
 * using this command next season, but it serves as a reference for how to implement
 * autonomous scoring commands using a SeuqentialCommandGroup. Instead, we will be using
 * Pedros Pathing to implement autonomous scoring commands next season. Check it out!
 */
public class BringSpecimensAuto extends SequentialCommandGroup {
  public BringSpecimensAuto(
      Drivetrain drive,
      Intake intake,
      HorizontalExtension horizontal,
      HomingSensor homingSensor) {
    addCommands(
        // Set initial positions
        new IntakeGoToFlipPosition(intake, Constants.IntakePosition.FLIP_TRANSFER_POSITION),
        new HorizontalRetractUntilPressed(horizontal, homingSensor),

        // Movement sequence
        new DriveForward(drive, 45),
        new DriveStrafeRight(drive, 45),
        new DriveForward(drive, 110),
        new DriveStrafeRight(drive, 27),
        new DriveBackward(drive, 135),
        new DriveForward(drive, 10),
        new DriveStrafeLeft(drive, 10),
        new DriveForward(drive, 135),
        new DriveStrafeRight(drive, 35),
        new RotateRight(drive, 50),
        new DriveBackward(drive, 135),
        new DriveForward(drive, 145),
        new DriveStrafeRight(drive, 19),
        new DriveBackward(drive, 135));
  }
}
