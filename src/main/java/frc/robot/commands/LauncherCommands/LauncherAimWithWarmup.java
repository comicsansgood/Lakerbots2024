package frc.robot.commands.LauncherCommands;

import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class LauncherAimWithWarmup extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  
  private final LauncherSubsystem m_launcher;
  //private final ElevatorSubsystem m_elevator;
  public double theta;

  
  public LauncherAimWithWarmup(LauncherSubsystem launcher/* , ElevatorSubsystem elevator*/, double theta) {
    m_launcher = launcher;
    //m_elevator = elevator;
    this.theta = theta;
    addRequirements(m_launcher);
  }

  @Override
  public void initialize() {
    //m_elevator.elevatorSetTarget(-20);
    m_launcher.launcherGo();
    m_launcher.launcherAim(theta);
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
