package frc.robot.commands.LauncherCommands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.FeederCommands.FeederCenter;
import frc.robot.commands.FeederCommands.FeederGo;
import frc.robot.commands.IntakeCommands.IntakeWristLaunch;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.LedsSubsystem;


public class LauncherAimNEChamps extends ParallelCommandGroup {

  public LauncherAimNEChamps(LauncherSubsystem launcher, FeederSubsystem feeder, IntakeSubsystem intake, double angle) {
    addCommands(
      new LauncherAimWithWarmupAndFeederReverse(launcher, feeder, angle),
      new IntakeWristLaunch(intake)
    );  
  }

}