package frc.robot.commands.FeederCommands;
import frc.robot.subsystems.FeederSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FeederSubsystem;

public class FeederStop extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final FeederSubsystem m_feederSubsystem;

  public FeederStop(FeederSubsystem feeder) {
    
    m_feederSubsystem = feeder;
    
    addRequirements(m_feederSubsystem);
  }

  @Override
  public void initialize() {
    m_feederSubsystem.feederStop();
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}
