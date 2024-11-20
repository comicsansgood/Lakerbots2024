package frc.robot.commands.LauncherCommands;
import frc.robot.subsystems.LauncherSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class LauncherSetVelocity extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final LauncherSubsystem m_launcher;
  private double leftVelocity;
  private double rightVelocity;

  public LauncherSetVelocity(LauncherSubsystem launcher, double leftVelocity, double rightVelocity) {
    m_launcher = launcher;
    this.leftVelocity = leftVelocity;
    this.rightVelocity = rightVelocity;
    addRequirements(m_launcher);
  }

  @Override
  public void initialize() {
    m_launcher.launcherSetVelocity(leftVelocity, rightVelocity);
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
