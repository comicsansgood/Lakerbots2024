package frc.robot.commands.ClimberCommands;

import frc.robot.subsystems.ClimberSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class ClimberGoToPosition extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  
  private final ClimberSubsystem m_climber;
  public double position;

  
  public ClimberGoToPosition(ClimberSubsystem climber, double position)  {
    m_climber = climber;
    this.position = position;
    addRequirements(m_climber);
  }

  @Override
  public void initialize() {
    m_climber.climberSetTarget(position);
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return m_climber.climberAtTargetPosition();
  }
}
