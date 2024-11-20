package frc.robot.commands.IntakeCommands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.FeederCommands.FeederGo;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;


public class SmartIntakeAuto extends SequentialCommandGroup {

  public SmartIntakeAuto(IntakeSubsystem m_intake, FeederSubsystem m_feeder) {
    addCommands(
      new IntakeSpin(m_intake, 1),
      new IntakeWristOut(m_intake).withTimeout(0.3),
      new FeederGo(m_feeder, -.2),
      new WaitForNote(m_intake).withTimeout(1),
      new FeederGo(m_feeder, 0)
    );  
  }
}