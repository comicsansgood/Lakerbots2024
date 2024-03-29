package frc.robot.commands.IntakeCommands;

import frc.robot.Constants;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;

public class IntakeCollect extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  
  private final IntakeSubsystem m_intake;
  private final FeederSubsystem m_feeder;


  private final double tolerence = 1;


  
  public IntakeCollect(IntakeSubsystem intake, FeederSubsystem feeder) {
    m_intake = intake;
    m_feeder = feeder;
    addRequirements(m_intake);
    addRequirements(m_feeder);
  }

  @Override
  public void initialize() {
    //m_intake.intakeSpin(.4);
    m_intake.intakeSetVelocity(2000);
    
    m_intake.intakeOut();
    m_feeder.feederGo(-.6);
  }

  @Override
  public void execute() {

  }

  @Override
  public void end(boolean interrupted) {
    m_feeder.feederGo(0);
  }

  @Override
  public boolean isFinished() {
    return m_intake.isNoteIntaked();

  }
}
