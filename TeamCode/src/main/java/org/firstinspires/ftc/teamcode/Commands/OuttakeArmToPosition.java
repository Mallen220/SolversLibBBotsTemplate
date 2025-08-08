package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.Subsystem;
import org.firstinspires.ftc.teamcode.Constants.OuttakeArmPosition;
import org.firstinspires.ftc.teamcode.Subsystems.OuttakeArm;

public class OuttakeArmToPosition extends InstantCommand {

  public OuttakeArmToPosition(OuttakeArm arm, OuttakeArmPosition position) {
    arm.goToPosition(position);
    addRequirements((Subsystem) arm);
  }
}
