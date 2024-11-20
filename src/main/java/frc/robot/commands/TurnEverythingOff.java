package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.FeederCommands.FeederGo;
import frc.robot.commands.IntakeCommands.IntakeSpin;
import frc.robot.commands.IntakeCommands.IntakeWristIn;
import frc.robot.commands.LauncherCommands.LauncherStop;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LauncherSubsystem;

public class TurnEverythingOff extends SequentialCommandGroup {

  public TurnEverythingOff(IntakeSubsystem intake, FeederSubsystem feeder, LauncherSubsystem launcher) {
    addCommands(
      new IntakeSpin(intake, 0),
      new IntakeWristIn(intake).withTimeout(0.5),
      new LauncherStop(launcher),
      new FeederGo(feeder, 0)
    );  
  }
}