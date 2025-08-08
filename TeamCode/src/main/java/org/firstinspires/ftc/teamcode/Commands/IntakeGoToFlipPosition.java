package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.Subsystem;
import org.firstinspires.ftc.teamcode.Constants.IntakePosition;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;

public class IntakeGoToFlipPosition extends InstantCommand {

  public IntakeGoToFlipPosition(Intake arm, IntakePosition position) {
    arm.goToPositionFlip(position);
    addRequirements((Subsystem) arm);
  }
}
