package frc.robot.commands.IntakeCommands;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class IntakeSetVelocity extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final IntakeSubsystem m_intake;
  private double velocity;

  public IntakeSetVelocity(IntakeSubsystem intake, double velocity) {
    m_intake = intake;
    this.velocity = velocity;
    addRequirements(m_intake);
  }

  @Override
  public void initialize() {
    m_intake.intakeSetVelocity(velocity);
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
