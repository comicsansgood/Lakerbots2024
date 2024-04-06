package frc.robot.commands.ClimberCommands;

import frc.robot.subsystems.ClimberSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class ClimberIncrement extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  
  private final ClimberSubsystem m_climber;
  public double increment;

  
  public ClimberIncrement(ClimberSubsystem climber, double increment){
    m_climber = climber;
    this.increment = increment;
    addRequirements(m_climber);
  }

  @Override
  public void initialize() {
    //m_climber.climberIncrement(increment);
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
