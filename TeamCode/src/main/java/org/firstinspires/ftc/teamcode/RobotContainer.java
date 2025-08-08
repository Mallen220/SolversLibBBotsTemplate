package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.WaitUntilCommand;
import com.seattlesolvers.solverslib.command.button.GamepadButton;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.AutoChooser;
import org.firstinspires.ftc.teamcode.Commands.DriveCommand;
import org.firstinspires.ftc.teamcode.Commands.HorizontalDefaultCommand;
import org.firstinspires.ftc.teamcode.Commands.IntakeGoToFlipPosition;
import org.firstinspires.ftc.teamcode.Commands.IntakeRotateCommand;
import org.firstinspires.ftc.teamcode.Commands.ManualArmCommand;
import org.firstinspires.ftc.teamcode.Commands.OuttakeArmToPosition;
import org.firstinspires.ftc.teamcode.Commands.ResetHeading;
import org.firstinspires.ftc.teamcode.Commands.ToggleOuttakeClawCommand;
import org.firstinspires.ftc.teamcode.Subsystems.HomingSensor;
import org.firstinspires.ftc.teamcode.Subsystems.HorizontalExtension;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Outtake;
import org.firstinspires.ftc.teamcode.Subsystems.OuttakeArm;
import org.firstinspires.ftc.teamcode.Subsystems.VerticalArm;
import org.firstinspires.ftc.teamcode.autonomous.BringSpecimensAuto;
import org.firstinspires.ftc.teamcode.autonomous.DriveForwardAuto;
import org.firstinspires.ftc.teamcode.autonomous.DriveRightAuto;
import org.firstinspires.ftc.teamcode.autonomous.ScoreSpecimenAuto;

public class RobotContainer {
  // Subsystems
  private VerticalArm verticalArm;
  private Outtake outtake;
  private OuttakeArm outtakeArm;
  private Drivetrain drive;
  private HorizontalExtension horizontal;
  private Intake intake;
  private HomingSensor homingSensor;

  // Dependencies
  private final HardwareMap hardwareMap;
  private final Telemetry telemetry;
  private GamepadEx gamepad1;
  private GamepadEx gamepad2;
  private final CommandOpMode JavaBot;

  public enum gameMode {
    Auto,
    TeleOp
  }

  private gameMode currentGameMode = null;

  public enum AutoMode { // Enum of all valid autonomous modes
    BringSpecimensAuto,
    DriveForwardAuto,
    DriveRightAuto,
    ExampleCommand,
    ScoreSpecimenAuto,
    DoNothingAuto;
  }

  public RobotContainer(
      HardwareMap hardwareMap,
      Gamepad gamepad1,
      Gamepad gamepad2,
      Telemetry telemetry,
      CommandOpMode JavaBot) {
    this.hardwareMap = hardwareMap;
    this.gamepad1 = new GamepadEx(gamepad1);
    this.gamepad2 = new GamepadEx(gamepad2);
    this.telemetry = telemetry;
    this.JavaBot = JavaBot;
  }

  public CommandOpMode getJavaBot() {
    return JavaBot;
  }

  public void initializeSubsystems() {
    verticalArm = new VerticalArm(hardwareMap);
    outtake = new Outtake(hardwareMap);
    outtakeArm = new OuttakeArm(hardwareMap);
    drive = new Drivetrain(hardwareMap);
    horizontal = new HorizontalExtension(hardwareMap);
    intake = new Intake(hardwareMap);
    homingSensor = new HomingSensor(hardwareMap);

    // Register subsystems with scheduler
    CommandScheduler.getInstance()
        .registerSubsystem(
            drive, verticalArm, outtakeArm, outtake, intake, horizontal, homingSensor);
  }

  public void configureTeleOp() {
    currentGameMode = gameMode.TeleOp;
    initializeSubsystems();

    // Default commands
    drive.setDefaultCommand(/* Default Drive Command */);
    verticalArm.setDefaultCommand(/* Manual Arm Command */);
    intake.setDefaultCommand(/* Intake Rotate Command */);
    horizontal.setDefaultCommand(/* Horizontal Default Command (horizontal, homingSensor, gamepad1) */);

    // Button bindings
    configureButtonBindings();
  }

  public void configureAuto() { //Note that I'm still working on this. It does not work yet. Feel free to mess with it.
    currentGameMode = gameMode.Auto;
    initializeSubsystems();
    startAutoChooser();
  }

  private void configureButtonBindings() {
    // Gamepad 1 buttons
    // TODO: Y should go to Flip transfer position
    // TODO: A should go to Flip pickup position
    // TODO: B should close the claw
    // TODO: X should open the claw

    // Gamepad 2 buttons
    // TODO: B should reset the heading
    // TODO: Left Bumper should toggle the outtake claw
    // TODO: D-Pad Up should go to score high bucket position
    // TODO: D-Pad Left should go to transfer position
    // TODO: D-Pad Right should go to high bar position
    // TODO: D-Pad Down should go to pickup position

  }

  public void startAutoChooser() { //Note that I'm still working on this. It does not work yet.
    AutoMode selectedMode = new AutoChooser(this, gamepad1, telemetry).getSelectedMode();
    scheduleAutoCommands(selectedMode);
  }

  public void scheduleAutoCommands(AutoMode selectedAutoMode) { //Note that I'm still working on this. It does not work yet.
    new WaitUntilCommand(JavaBot::isStarted);
    if (selectedAutoMode
        == AutoMode.BringSpecimensAuto) { // The switches we need here are not supported
      // till Java 14 :(.
      CommandScheduler.getInstance()
          .schedule(new BringSpecimensAuto(drive, intake, horizontal, homingSensor));
    } else if (selectedAutoMode == AutoMode.DriveForwardAuto) {
      CommandScheduler.getInstance().schedule(new DriveForwardAuto(drive));
    } else if (selectedAutoMode == AutoMode.DriveRightAuto) {
      CommandScheduler.getInstance().schedule(new DriveRightAuto(drive));
    } else if (selectedAutoMode == AutoMode.ScoreSpecimenAuto) {
      CommandScheduler.getInstance()
          .schedule(
              new ScoreSpecimenAuto(
                  drive, intake, outtake, outtakeArm, verticalArm, horizontal, homingSensor));
    } else if (selectedAutoMode == AutoMode.DoNothingAuto) {
      telemetry.addLine("Do Nothing auto selected. Doing nothing!");
    } else {
      telemetry.addLine("No auto was selected! There was likely an error.");
    }
  }

  public void run() {
    // telemetry
    telemetry.addData(/* Add any data you want in this section! This will run periodically */);
    telemetry.update();

    if (currentGameMode == gameMode.TeleOp) { // This is how we read the gamepad inputs in TeleOp mode.
      gamepad1.readButtons();
      gamepad2.readButtons();
    }
  }
}
