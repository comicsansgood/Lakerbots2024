package frc.robot.commands.IntakeCommands;

import frc.robot.Constants;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;

public class SmartIntake extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  
  private final IntakeSubsystem m_intake;
  public double speed;
  public XboxController controller;
  public int buttonNumber;

  private final double tolerence = 5;

  private boolean isIntakeSafe;

  
  public SmartIntake(IntakeSubsystem intake, double speed, XboxController controller, int buttonNumber) {
    m_intake = intake;
    this.speed = speed;
    this.controller = controller;
    addRequirements(m_intake);
  }

  @Override
  public void initialize() {
    if(m_intake.intakeWristGetPosition() - Constants.IntakeConstants.intakeIn < tolerence){
    isIntakeSafe = true;
    }else{
      isIntakeSafe = false;
    }
  }

  @Override
  public void execute() {

    if(controller.getRawButton(buttonNumber) == true){
      if(isIntakeSafe == true){
        m_intake.intakeOut();
        m_intake.intakeSpin(speed);
      }
      else{
        m_intake.intakeIn();
        m_intake.intakeStop();
      }
    }

    if(isIntakeSafe == true && m_intake.isNoteIntaked()){
       m_intake.intakeIn();
      m_intake.intakeStop();
    }

  }

  @Override
  public void end(boolean interrupted) {
    m_intake.intakeStop();
    m_intake.intakeIn();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
