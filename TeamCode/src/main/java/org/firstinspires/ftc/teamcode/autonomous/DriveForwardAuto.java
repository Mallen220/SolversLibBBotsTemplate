package org.firstinspires.ftc.teamcode.autonomous;

import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.DriveForward;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;

/**
 * Since our goal is to not use this type of command next season, this is an example of how
 * to structure a command group for scoring a specimen in autonomous mode. We will not be
 * using this command next season, but it serves as a reference for how to implement
 * autonomous scoring commands using a SeuqentialCommandGroup. Instead, we will be using
 * Pedros Pathing to implement autonomous scoring commands next season. Check it out!
 */
public class DriveForwardAuto extends SequentialCommandGroup {
  public DriveForwardAuto(Drivetrain drive) {
    addCommands(
        // Movement sequence
        new DriveForward(drive, 45));
  }
}
