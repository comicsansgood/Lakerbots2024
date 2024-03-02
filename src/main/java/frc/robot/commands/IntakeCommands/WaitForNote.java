package frc.robot.commands.IntakeCommands;

import frc.robot.Constants;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;

public class WaitForNote extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  
  IntakeSubsystem intake;


  
  public WaitForNote(IntakeSubsystem intake) {

    this.intake = intake;
    addRequirements(intake);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {

  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return intake.isNoteIntaked();

  }
}
