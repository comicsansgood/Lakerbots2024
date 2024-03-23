package frc.robot.commands.LauncherCommands;

import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class LauncherAimHome extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  
  private final LauncherSubsystem m_launcher;
  public double theta;

  
  public LauncherAimHome(LauncherSubsystem launcher) {
    m_launcher = launcher;
    this.theta = theta;
    addRequirements(m_launcher);
  }

  @Override
  public void initialize() {
    m_launcher.launcherAimHome();
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return m_launcher.LauncherAtTargetPosition();
  }
}
