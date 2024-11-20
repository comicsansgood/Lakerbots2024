package frc.robot.commands.LauncherCommands;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.IntakeCommands.IntakeWristLaunch;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LauncherSubsystem;

public class LauncherAimNEChamps extends ParallelCommandGroup {

  public LauncherAimNEChamps(LauncherSubsystem launcher, FeederSubsystem feeder, IntakeSubsystem intake, double angle) {
    addCommands(
      new LauncherAimWithWarmupAndFeederReverse(launcher, feeder, angle),
      new IntakeWristLaunch(intake)
    );  
  }
}