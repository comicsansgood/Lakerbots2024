package frc.robot.commands.TrampulatorCommands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.commands.ElevatorCommands.ElevatorGoToPosition;
import frc.robot.commands.TrampulatorCommands.TrampulatorManipulatorCommands.TrampulatorManipulatorSpin;
import frc.robot.commands.TrampulatorCommands.TrampulatorWristCommands.TrampulatorWristGoToPosition;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.TrampulatorSubsystem;


public class TrampulatorCollect extends SequentialCommandGroup {

  public TrampulatorCollect(TrampulatorSubsystem m_trampulator, ElevatorSubsystem m_elevator) {
    addCommands(
      new ElevatorGoToPosition(m_elevator, 50/*TODO:set to correct pos*/),
      new TrampulatorWristGoToPosition(m_trampulator, 10/*TODO: set to correct pos */),
      new TrampulatorManipulatorSpin(m_trampulator, .3),
      new WaitCommand(.1),
      new WaitUntilCommand(() -> m_trampulator.trampulatorIsNoteIntaked()),
      new TrampulatorManipulatorSpin(m_trampulator, 0.1)
    );  
  }

}