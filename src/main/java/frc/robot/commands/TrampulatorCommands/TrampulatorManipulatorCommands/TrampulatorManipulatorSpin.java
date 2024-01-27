package frc.robot.commands.TrampulatorCommands.TrampulatorManipulatorCommands;

import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.TrampulatorSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class TrampulatorManipulatorSpin extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final TrampulatorSubsystem m_trampulator;
  double speed;

  public TrampulatorManipulatorSpin(TrampulatorSubsystem trampulator, double speed) {
   m_trampulator = trampulator;
   this.speed = speed;

    addRequirements(m_trampulator);
  }

  @Override
  public void initialize() {
    m_trampulator.trampulatorManipulatorSpin(speed);

  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {

  }
  @Override
  public boolean isFinished() {
    return true;
  }
}
