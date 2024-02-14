package frc.robot.commands.ClimberCommands;

import frc.robot.subsystems.ClimberSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class ClimberSpin extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  
  private final ClimberSubsystem m_climber;
  public double speed;

  
  public ClimberSpin(ClimberSubsystem climber, double speed)  {
    m_climber = climber;
    this.speed = speed;
    addRequirements(m_climber);
  }

  @Override
  public void initialize() {
   
  }

  @Override
  public void execute() {
     m_climber.climberSpin(speed);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return m_climber.climberAtTargetPosition();
  }
}
