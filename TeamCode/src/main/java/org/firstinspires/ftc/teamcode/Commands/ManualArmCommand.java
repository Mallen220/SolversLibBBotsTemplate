package org.firstinspires.ftc.teamcode.Commands;

import static org.firstinspires.ftc.teamcode.Utils.isWithinTolerance;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.command.Subsystem;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import org.firstinspires.ftc.teamcode.Subsystems.VerticalArm;

public class ManualArmCommand extends CommandBase {
  private final VerticalArm arm;
  private final GamepadEx gamepad;

  public ManualArmCommand(VerticalArm arm, GamepadEx gamepad) {
    this.arm = arm;
    this.gamepad = gamepad;
    addRequirements((Subsystem) arm);
  }

  public void initialize() {
    // Since the arm subsystem is set to encoder control, we don't need to do anything here.
    // This command is technically logically flawed but serves as a demo so is ok. This is for
    // manual control of the arm using the gamepad.
  }

  @Override
  public void execute() {
    if (!isWithinTolerance(0, gamepad.getLeftY(), 0.1)) {
      final double power = -gamepad.getLeftY();
      // limit power
      // power *= 0.5;
      arm.setArmPowers(power);
    } else {
      arm.stop();
    }
  }

  public void end(boolean interrupted) {
    arm.stop();
  }

  public boolean isFinished() {
    return false; // This command runs until explicitly stopped
  }
}
