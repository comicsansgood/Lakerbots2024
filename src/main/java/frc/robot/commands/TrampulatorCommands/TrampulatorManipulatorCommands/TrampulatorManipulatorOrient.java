package frc.robot.commands.TrampulatorCommands.TrampulatorManipulatorCommands;

import frc.robot.subsystems.TrampulatorSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class TrampulatorManipulatorOrient extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final TrampulatorSubsystem m_trampulator;
  double position;

  public TrampulatorManipulatorOrient(TrampulatorSubsystem trampulator, double position) {
   m_trampulator = trampulator;
   this.position = position;

    addRequirements(m_trampulator);
  }

  @Override
  public void initialize() {
    m_trampulator.trampulatorManipulatorGoToPosition(position);

  }

  @Override
  public void execute() {

  }

  @Override
  public void end(boolean interrupted) {

  }
  @Override
  public boolean isFinished() {
    return m_trampulator.trampulatorManipulatorAtTarget();
  }
}
