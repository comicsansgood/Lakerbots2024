package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.FeederCommands.FeederGo;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

// same as SmartIntakeAuto but with no timeout on the waitForNote
public class SmartIntakeAutoLong extends SequentialCommandGroup {

  public SmartIntakeAutoLong(IntakeSubsystem m_intake, FeederSubsystem m_feeder) {
    addCommands(
      new IntakeSpin(m_intake, 1),
      //new WaitCommand(0.5),
      new IntakeWristOut(m_intake).withTimeout(0.3),
      new FeederGo(m_feeder, -.2),
      new WaitForNote(m_intake),
      //new WaitCommand(0.25),
      new FeederGo(m_feeder, 0),
      new IntakeStopCollect(m_intake, m_feeder)
    );  
  }

}