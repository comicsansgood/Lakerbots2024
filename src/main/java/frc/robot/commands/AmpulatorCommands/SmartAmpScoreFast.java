package frc.robot.commands.AmpulatorCommands;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.LauncherCommands.LauncherAimWithWarmup;
import frc.robot.Constants;
import frc.robot.commands.ElevatorCommands.ElevatorGoToPosition;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.AmpulatorSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;

public class SmartAmpScoreFast extends ParallelCommandGroup {

  public SmartAmpScoreFast(LauncherSubsystem launcher,ElevatorSubsystem m_Elevator, AmpulatorSubsystem ampulator) {
    addCommands(
      new LauncherAimWithWarmup(launcher, Constants.LauncherConstants.launcherAngleAmpScore),
      new ElevatorGoToPosition(m_Elevator, -30),
      new AmpulatorOut(ampulator)
    );  
  }

}