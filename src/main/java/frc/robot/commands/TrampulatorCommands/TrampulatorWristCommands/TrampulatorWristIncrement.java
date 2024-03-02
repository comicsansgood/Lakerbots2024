package frc.robot.commands.TrampulatorCommands.TrampulatorWristCommands;

import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.TrampulatorSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class TrampulatorWristIncrement extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  
  private final TrampulatorSubsystem m_trampulator;
  public double position;
  public double increment;
  public double newTarget;

  
  public TrampulatorWristIncrement(TrampulatorSubsystem trampulator, double increment) {
    m_trampulator = trampulator;
    this.increment = increment;
    addRequirements(m_trampulator);
  }

  @Override
  public void initialize() {
    position = m_trampulator.trampulatorWristGetPosition();
    newTarget= position +increment;
    m_trampulator.trampulatorWristSetTarget(newTarget);
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
