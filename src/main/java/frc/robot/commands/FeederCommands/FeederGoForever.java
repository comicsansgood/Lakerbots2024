package frc.robot.commands.FeederCommands;
import frc.robot.subsystems.FeederSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FeederSubsystem;

public class FeederGoForever extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final FeederSubsystem m_feederSubsystem;
  private double speed;

  public FeederGoForever(FeederSubsystem feeder, double speed) {
    
    m_feederSubsystem = feeder;
    this.speed = speed;
    
    addRequirements(m_feederSubsystem);
  }

  @Override
  public void initialize() {
    m_feederSubsystem.feederGo(speed);
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
