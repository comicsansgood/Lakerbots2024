package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.FeederCommands.FeederGo;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;


public class SmartIntake extends SequentialCommandGroup {

  public SmartIntake(IntakeSubsystem m_intake, FeederSubsystem m_feeder, XboxController controller) {
    addCommands(
      new IntakeSpin(m_intake, 1),
      new WaitCommand(0.5),
      new IntakeWristOut(m_intake).withTimeout(1),
      new FeederGo(m_feeder, -.6),
      new WaitForNoteOrButtonPress(m_intake, controller),
      new FeederGo(m_feeder, 0),
      new IntakeStopCollect(m_intake, m_feeder)
    );  
  }

}