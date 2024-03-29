package frc.robot.commands.TrampulatorCommands.TrampulatorManipulatorCommands;

import frc.robot.subsystems.TrampulatorSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;

public class TrampulatorManipulatorOrient extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final TrampulatorSubsystem m_trampulator;
  private XboxController controller;

  public TrampulatorManipulatorOrient(TrampulatorSubsystem trampulator, XboxController controller) {
   m_trampulator = trampulator;
   this.controller = controller;

    addRequirements(m_trampulator);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
      m_trampulator.trampulatorManipulatorSpin(controller.getRightY()/2, -controller.getRightY()/2);
  }

  @Override
  public void end(boolean interrupted) {

  }
  @Override
  public boolean isFinished() {
    return false;
  }
}
