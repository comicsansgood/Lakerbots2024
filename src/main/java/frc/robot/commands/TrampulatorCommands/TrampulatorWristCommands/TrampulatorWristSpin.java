package frc.robot.commands.TrampulatorCommands.TrampulatorWristCommands;

import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.TrampulatorSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class TrampulatorWristSpin extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  
  private final TrampulatorSubsystem m_trampulator;
  public double speed;

  
  public TrampulatorWristSpin(TrampulatorSubsystem trampulator, double speed) {
    m_trampulator = trampulator;
    this.speed=speed;
    addRequirements(m_trampulator);
  }

  @Override
  public void initialize() {
    m_trampulator.trampulatorWristSpin(speed);
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return m_trampulator.TrampulatorAtTargetPosition();
  }
}
