package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;


public class SmartIntake extends SequentialCommandGroup {

  public SmartIntake(IntakeSubsystem m_intake, FeederSubsystem m_feeder) {
    addCommands(
      new IntakeCollect(m_intake, m_feeder),
      new IntakeStopCollect(m_intake, m_feeder)
    );  
  }

}