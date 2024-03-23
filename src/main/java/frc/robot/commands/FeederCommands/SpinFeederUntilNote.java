package frc.robot.commands.FeederCommands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.FeederCommands.FeederGo;
import frc.robot.commands.IntakeCommands.WaitForNote;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;


public class SpinFeederUntilNote extends SequentialCommandGroup {

  public SpinFeederUntilNote(IntakeSubsystem m_intake, FeederSubsystem m_feeder) {
    addCommands(
      new FeederGo(m_feeder, -.6),
      new WaitForNote(m_intake).withTimeout(.25),
      new FeederGo(m_feeder, 0)
    );  
  }

}