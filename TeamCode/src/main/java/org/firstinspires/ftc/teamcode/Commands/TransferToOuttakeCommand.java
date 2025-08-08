package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.OuttakeArm;

public class TransferToOuttakeCommand extends SequentialCommandGroup {
  public TransferToOuttakeCommand(Intake intake, OuttakeArm outtakeArm) {
    addCommands(
            ...
            );
  }
}
