package frc.robot.commands.LauncherCommands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.Constants.AmpulatorConstants;
import frc.robot.commands.AmpulatorCommands.AmpulatorOut;
import frc.robot.commands.ElevatorCommands.ElevatorGoToPosition;
import frc.robot.commands.FeederCommands.FeederGo;
import frc.robot.commands.FeederCommands.FeederStop;
import frc.robot.commands.IntakeCommands.IntakeSpin;
import frc.robot.commands.IntakeCommands.IntakeWristOut;
import frc.robot.commands.LauncherCommands.LauncherGo;
import frc.robot.subsystems.AmpulatorSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LauncherSubsystem;


public class LauncherAmpScore extends SequentialCommandGroup {
  public boolean ampulatorState = false; //false -> in, true -> out

  public LauncherAmpScore(LauncherSubsystem launcher, FeederSubsystem feeder, ElevatorSubsystem elevator, AmpulatorSubsystem ampulator, XboxController controller) {
    addCommands(
      new LauncherAim(launcher, Constants.LauncherConstants.launcherAngleAmpScore),
      new ElevatorGoToPosition(elevator, -5),
      new AmpulatorOut(ampulator)

    );  
  }

}