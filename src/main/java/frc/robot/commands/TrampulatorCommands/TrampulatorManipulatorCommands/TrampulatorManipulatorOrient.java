package frc.robot.commands.TrampulatorCommands.TrampulatorManipulatorCommands;

import frc.robot.subsystems.TrampulatorSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;

public class TrampulatorManipulatorOrient extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final TrampulatorSubsystem m_trampulator;
  double topWheelSpeed;
  double bottomWheelSpeed;
  XboxController controller;

  public TrampulatorManipulatorOrient(TrampulatorSubsystem trampulator, double topWheelSpeed, double bottomWheelSpeed, XboxController controller) {
   m_trampulator = trampulator;
   this.topWheelSpeed = topWheelSpeed;
   this.bottomWheelSpeed = bottomWheelSpeed;
   this.controller = controller;

    addRequirements(m_trampulator);
  }

  @Override
  public void initialize() {

    //m_trampulator.trampulatorManipulatorSpin(topWheelSpeed, bottomWheelSpeed);
    m_trampulator.trampulatorManipulatorSpin(controller.getLeftY(), controller.getRightY());

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
