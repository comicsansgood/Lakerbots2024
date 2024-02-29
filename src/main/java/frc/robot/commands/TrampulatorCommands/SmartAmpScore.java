package frc.robot.commands.TrampulatorCommands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.ElevatorCommands.ElevatorGoToPosition;
import frc.robot.commands.TrampulatorCommands.TrampulatorManipulatorCommands.TrampulatorManipulatorOrient;
import frc.robot.commands.TrampulatorCommands.TrampulatorManipulatorCommands.TrampulatorManipulatorSpin;
import frc.robot.commands.TrampulatorCommands.TrampulatorWristCommands.TrampulatorWristGoToPosition;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.TrampulatorSubsystem;

public class SmartAmpScore extends SequentialCommandGroup {

  public SmartAmpScore(TrampulatorSubsystem trampulator, ElevatorSubsystem elevator) {
    addCommands(
        new ElevatorGoToPosition(elevator, 100/*TODO: position */).alongWith(
          new TrampulatorWristGoToPosition(trampulator, 50/*TODO: position */)),
        new TrampulatorManipulatorOrient(trampulator, 20/*TODO: position */)
    );
  }

}