package frc.robot.commands.LauncherCommands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.FeederCommands.FeederCenter;
import frc.robot.commands.FeederCommands.FeederGo;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.LedsSubsystem;


public class LauncherAimWithWarmupAndFeederReverse extends SequentialCommandGroup {

  public LauncherAimWithWarmupAndFeederReverse(LauncherSubsystem launcher, FeederSubsystem m_feeder, double angle) {
    addCommands(
      new FeederGo(m_feeder, 0.2),
      new WaitCommand(0.3),
      new FeederGo(m_feeder, 0),
      new LauncherAimWithWarmup(launcher, angle)
    );  
  }

}