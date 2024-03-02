package frc.robot.commands.TrampulatorCommands.TrampulatorManipulatorCommands;

import frc.robot.subsystems.TrampulatorSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class TrampulatorManipulatorSpinAdv extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final TrampulatorSubsystem m_trampulator;
  double speed1;
  double speed2;

  public TrampulatorManipulatorSpinAdv(TrampulatorSubsystem trampulator, double speed1, double speed2) {
   m_trampulator = trampulator;
   this.speed1 = speed1;
   this.speed2 = speed2;

    addRequirements(m_trampulator);
  }

  @Override
  public void initialize() {
    m_trampulator.trampulatorManipulatorSpin(speed1, speed2);

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
