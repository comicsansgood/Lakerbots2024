package frc.robot.commands.IntakeCommands;

import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class IntakeWristSpin extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  
  private final IntakeSubsystem m_intake;
  public double speed;

  
  public IntakeWristSpin(IntakeSubsystem intake,double speed) {
    m_intake = intake;
    this.speed=speed;
    addRequirements(m_intake);
  }

  @Override
  public void initialize() {
    
  }

  @Override
  public void execute() {
    m_intake.intakeWristSpin(speed);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return m_intake.intakeAtTargetPosition();
  }
}
