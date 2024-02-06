package frc.robot.commands.TrampulatorCommands.TrampulatorWristCommands;

import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.TrampulatorSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class TrampulatorWristGoToPosition extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  
  private final TrampulatorSubsystem m_trampulator;
  public double position;

  
  public TrampulatorWristGoToPosition(TrampulatorSubsystem trampulator, double position) {
    m_trampulator = trampulator;
    this.position = position;
    addRequirements(m_trampulator);
  }

  @Override
  public void initialize() {
    m_trampulator.trampulatorWristSetTarget(position);
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
