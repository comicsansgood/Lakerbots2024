package frc.robot.commands.LauncherCommands;
import frc.robot.subsystems.LauncherSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class LauncherSet extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final LauncherSubsystem m_launcher;
  public double val1;
  public double val2;

  public LauncherSet(LauncherSubsystem launcher, double val1, double val2) {
    m_launcher = launcher;
    this.val1 = val1;
    this.val2 = val2;
    addRequirements(m_launcher);
  }

  @Override
  public void initialize() {
    m_launcher.launcherSet(val1, val2);

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
