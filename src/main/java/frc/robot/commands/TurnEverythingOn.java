package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.FeederCommands.FeederGo;
import frc.robot.commands.IntakeCommands.IntakeSpin;
import frc.robot.commands.IntakeCommands.IntakeWristOut;
import frc.robot.commands.LauncherCommands.LauncherGo;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LauncherSubsystem;

public class TurnEverythingOn extends SequentialCommandGroup {

  public TurnEverythingOn(IntakeSubsystem intake, FeederSubsystem feeder, LauncherSubsystem launcher) {
    addCommands(
      new IntakeSpin(intake, 0.75),
      new IntakeWristOut(intake).withTimeout(0.5),
      new LauncherGo(launcher),
      new FeederGo(feeder, -.6)
    );  
  }
}