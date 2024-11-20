package frc.robot.commands.IntakeCommands;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class IntakeSpin extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final IntakeSubsystem m_intake;
  public double speed;

  public IntakeSpin(IntakeSubsystem intake, double speed) {
    m_intake = intake;
    this.speed = speed;
    addRequirements(m_intake);
  }

  @Override
  public void initialize() {
    m_intake.intakeSpin(speed);
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
