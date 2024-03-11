package frc.robot.commands.IntakeCommands;

import frc.robot.Constants;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;

public class IntakeStopCollect extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  
  private final IntakeSubsystem m_intake;
  private final FeederSubsystem m_feeder;


  private final double tolerence = 1;


  
  public IntakeStopCollect(IntakeSubsystem intake,FeederSubsystem feeder) {
    m_intake = intake;
    m_feeder = feeder;
    addRequirements(m_intake);
  }

  @Override
  public void initialize() {
    m_intake.intakeSpin(0);
    m_feeder.feederStop();
    m_intake.intakeHome();//m_intake.intakeIn(); changed 3/10/24 9:08 am
  }

  @Override
  public void execute() {

  }

  @Override
  public void end(boolean interrupted) {

  }

  @Override
  public boolean isFinished() {
    return m_intake.intakeAtTargetPosition();

  }
}
