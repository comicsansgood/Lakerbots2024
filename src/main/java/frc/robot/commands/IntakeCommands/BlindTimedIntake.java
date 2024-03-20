package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.FeederCommands.FeederGo;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;


public class BlindTimedIntake extends SequentialCommandGroup {

  public BlindTimedIntake(IntakeSubsystem m_intake, FeederSubsystem m_feeder) {
    addCommands(
      new IntakeSpin(m_intake, 1),
      new IntakeWristOut(m_intake).withTimeout(0.5),
      new FeederGo(m_feeder, -.6),
      new WaitCommand(.8),
      new IntakeStopCollect(m_intake, m_feeder)
    );  
  }

}