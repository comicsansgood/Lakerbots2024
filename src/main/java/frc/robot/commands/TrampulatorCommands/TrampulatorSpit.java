package frc.robot.commands.TrampulatorCommands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.ElevatorCommands.ElevatorGoToPosition;
import frc.robot.commands.FeederCommands.FeederGo;
import frc.robot.commands.TrampulatorCommands.TrampulatorManipulatorCommands.TrampulatorManipulatorOrient;
import frc.robot.commands.TrampulatorCommands.TrampulatorManipulatorCommands.TrampulatorManipulatorSpin;
import frc.robot.commands.TrampulatorCommands.TrampulatorManipulatorCommands.TrampulatorManipulatorSpinAdv;
import frc.robot.commands.TrampulatorCommands.TrampulatorWristCommands.TrampulatorWristGoToPosition;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.TrampulatorSubsystem;

public class TrampulatorSpit extends SequentialCommandGroup {

  public TrampulatorSpit(TrampulatorSubsystem trampulator) {
    addCommands(
      new TrampulatorManipulatorSpinAdv(trampulator, -.3, -.3),
      new WaitCommand(0.2),
      new TrampulatorManipulatorSpin(trampulator, 0)
  

    );
  }

}