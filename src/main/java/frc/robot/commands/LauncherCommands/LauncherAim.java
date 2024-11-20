package frc.robot.commands.LauncherCommands;
import frc.robot.subsystems.LauncherSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class LauncherAim extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final LauncherSubsystem m_launcher;
  public double theta;

  public LauncherAim(LauncherSubsystem launcher, double theta) {
    m_launcher = launcher;
    this.theta = theta;
    addRequirements(m_launcher);
  }

  @Override
  public void initialize() {
    m_launcher.launcherAim(theta);
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return m_launcher.LauncherAtTargetPosition();
  }
}
