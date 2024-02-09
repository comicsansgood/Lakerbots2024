package frc.robot.commands.TrampulatorCommands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.TrampulatorCommands.TrampulatorManipulatorCommands.TrampulatorManipulatorOrient;
import frc.robot.commands.TrampulatorCommands.TrampulatorManipulatorCommands.TrampulatorManipulatorSpin;
import frc.robot.commands.TrampulatorCommands.TrampulatorWristCommands.TrampulatorWristGoToPosition;
import frc.robot.subsystems.TrampulatorSubsystem;

public class SmartAmpScore extends SequentialCommandGroup {

  public SmartAmpScore(TrampulatorSubsystem trampulator) {
    addCommands(
        new TrampulatorManipulatorSpin(trampulator, 0.2),
        new WaitCommand(1),
        new TrampulatorManipulatorSpin(trampulator, 0),

        new TrampulatorManipulatorOrient(trampulator, Constants.TrampulatorConstants.trampulatorOrientAmp)
        .alongWith(new TrampulatorWristGoToPosition(trampulator, Constants.TrampulatorConstants.trampulatorWristAmp)),
        new TrampulatorManipulatorSpin(trampulator, -0.2),
        new WaitCommand(1),
        new TrampulatorManipulatorSpin(trampulator, 0),
        new TrampulatorWristGoToPosition(trampulator,Constants.TrampulatorConstants.trampulatorWristMin)
    );
  }

}