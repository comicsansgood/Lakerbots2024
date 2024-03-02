package frc.robot.commands.TrampulatorCommands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.ElevatorCommands.ElevatorGoToPosition;
import frc.robot.commands.FeederCommands.FeederGo;
import frc.robot.commands.LauncherCommands.LauncherAim;
import frc.robot.commands.TrampulatorCommands.TrampulatorManipulatorCommands.TrampulatorManipulatorOrient;
import frc.robot.commands.TrampulatorCommands.TrampulatorManipulatorCommands.TrampulatorManipulatorSpin;
import frc.robot.commands.TrampulatorCommands.TrampulatorWristCommands.TrampulatorWristGoToPosition;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.TrampulatorSubsystem;

public class SmartAmpScore extends SequentialCommandGroup {

  public SmartAmpScore(TrampulatorSubsystem trampulator, ElevatorSubsystem elevator, FeederSubsystem feeder, LauncherSubsystem launcher) {
    addCommands(
      //new FeederGo(feeder, -.3),
      new LauncherAim(launcher, 10),
      new TrampulatorManipulatorSpin(trampulator, .3),
        new ElevatorGoToPosition(elevator, -20).alongWith(
          new TrampulatorWristGoToPosition(trampulator, 35.5)).alongWith(new FeederGo(feeder, -.3)),
      new TrampulatorManipulatorSpin(trampulator, 0)
  

    );
  }

}