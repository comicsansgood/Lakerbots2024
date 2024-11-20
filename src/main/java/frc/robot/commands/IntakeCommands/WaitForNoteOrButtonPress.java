package frc.robot.commands.IntakeCommands;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;

public class WaitForNoteOrButtonPress extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  IntakeSubsystem intake;
  XboxController controller;

  public WaitForNoteOrButtonPress(IntakeSubsystem intake, XboxController controller) {
    this.intake = intake;
    this.controller = controller;
    addRequirements(intake);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return intake.isNoteIntaked() || controller.getLeftBumper();
  }
}
