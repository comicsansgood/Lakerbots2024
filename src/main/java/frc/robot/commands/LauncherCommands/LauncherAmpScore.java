package frc.robot.commands.LauncherCommands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.ElevatorCommands.ElevatorGoToPosition;
import frc.robot.commands.FeederCommands.FeederGo;
import frc.robot.commands.FeederCommands.FeederStop;
import frc.robot.commands.IntakeCommands.IntakeSpin;
import frc.robot.commands.IntakeCommands.IntakeWristOut;
import frc.robot.commands.LauncherCommands.LauncherGo;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LauncherSubsystem;


public class LauncherAmpScore extends SequentialCommandGroup {

  public LauncherAmpScore(LauncherSubsystem launcher, FeederSubsystem feeder, ElevatorSubsystem elevator) {
    addCommands(
      new LauncherAim(launcher, 2),
      new ElevatorGoToPosition(elevator, -100),
      new LauncherSet(launcher, -0.15, -0.15),
      new FeederGo(feeder, -0.3),
      new WaitCommand(2),
      new LauncherStop(launcher),
      new ElevatorGoToPosition(elevator, 0),
      new LauncherAim(launcher, 0),
      new FeederStop(feeder)
    );  
  }

}