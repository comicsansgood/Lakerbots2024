package frc.robot.commands.FeederCommands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.FeederCommands.FeederGo;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;


public class FeederCenter extends SequentialCommandGroup {

  public FeederCenter(FeederSubsystem m_feeder) {
    addCommands(
      new FeederGo(m_feeder, -0.6),
      new WaitCommand(0.5),
      new FeederGo(m_feeder, 0.6),
      new WaitCommand(0.25), 
      new FeederGo(m_feeder, 0)
    );  
  }

}