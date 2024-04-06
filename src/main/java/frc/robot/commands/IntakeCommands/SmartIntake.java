package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.FeederCommands.FeederCenter;
import frc.robot.commands.FeederCommands.FeederGo;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LedsSubsystem;


public class SmartIntake extends SequentialCommandGroup {

  public SmartIntake(IntakeSubsystem m_intake, FeederSubsystem m_feeder, XboxController driverController) {
    addCommands(
      new IntakeSpin(m_intake, 1),
      //new WaitCommand(0.5),
      new IntakeWristOut(m_intake).withTimeout(1),
      new FeederGo(m_feeder, -.35),
      new WaitForNoteOrButtonPress(m_intake, driverController),
      //new WaitCommand(0.25),
      new FeederGo(m_feeder, 0),
      new IntakeStopCollect(m_intake, m_feeder),
      new FeederCenter(m_feeder)
    );  
  }

}