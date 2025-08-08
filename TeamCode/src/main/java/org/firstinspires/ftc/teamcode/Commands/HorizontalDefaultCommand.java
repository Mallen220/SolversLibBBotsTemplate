package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.command.Subsystem;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import org.firstinspires.ftc.teamcode.Subsystems.HomingSensor;
import org.firstinspires.ftc.teamcode.Subsystems.HorizontalExtension;

public class HorizontalDefaultCommand extends CommandBase {
  private final HorizontalExtension horizontalExtension;
  private final HomingSensor homingSensor;
  private final GamepadEx gamepad;

  private boolean isHoming = false; // Flag to check if homing is in progress

  private final double tolerance = 0.1; // Tolerance for joystick input

  public HorizontalDefaultCommand(
      HorizontalExtension horizontalExtension, HomingSensor homingSensor, GamepadEx gamepad) {
    this.horizontalExtension = horizontalExtension;
    this.homingSensor = homingSensor;
    this.gamepad = gamepad;
    addRequirements((Subsystem) ..., (Subsystem) ...);
  }

  public void initialize() {
    // Nothing to do here??
  }

  @Override
  public void execute() {
    if (gamepad.isDown(
        GamepadKeys.Button.RIGHT_BUMPER)) { // Start homing when right bumper is pressed
      ... // You should only set a variable here, why?
    } else if (...) { // Stop homing when left bumper is pressed and extend
      // Set a variable.
      // Extend the horizontal extension
    }

    if (!homingSensor.isPressed() && isHoming) {
      ... // What should we do here?
    } else if (homingSensor.isPressed()) {
      ... // This might be important.
    }
  }

  public void end(boolean interrupted) {
    // No specific action needed on end??
  }

  @Override
  public boolean isFinished() {
    return false; // Why might this always return false?
  }
}
