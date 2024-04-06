package frc.robot.commands.AmpulatorCommands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.FeederCommands.FeederGo;
import frc.robot.commands.LauncherCommands.LauncherAim;
import frc.robot.commands.LauncherCommands.LauncherAimHome;
import frc.robot.commands.LauncherCommands.LauncherAimWithWarmup;
import frc.robot.commands.LauncherCommands.LauncherSet;
import frc.robot.Constants;
import frc.robot.commands.ElevatorCommands.ElevatorGoToPosition;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
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