package frc.robot.commands.IntakeCommands;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class IntakeWristOut extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final IntakeSubsystem m_intake;

  public IntakeWristOut(IntakeSubsystem intake) {
    m_intake = intake;
    addRequirements(m_intake);
  }

  @Override
  public void initialize() {
    m_intake.intakeOut();
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return m_intake.intakeAtTargetPosition();
  }
}
