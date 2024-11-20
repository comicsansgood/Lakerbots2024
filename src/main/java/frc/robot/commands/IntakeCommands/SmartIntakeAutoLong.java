package frc.robot.commands.IntakeCommands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.FeederCommands.FeederGo;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

// same as SmartIntakeAuto but with no timeout on the waitForNote
public class SmartIntakeAutoLong extends SequentialCommandGroup {

  public SmartIntakeAutoLong(IntakeSubsystem m_intake, FeederSubsystem m_feeder) {
    addCommands(
      new IntakeSpin(m_intake, 1),
      new IntakeWristOut(m_intake).withTimeout(0.3),
      new FeederGo(m_feeder, -.35),
      new WaitForNote(m_intake),
      new FeederGo(m_feeder, 0),
      new IntakeStopCollect(m_intake, m_feeder)
    );  
  }
}