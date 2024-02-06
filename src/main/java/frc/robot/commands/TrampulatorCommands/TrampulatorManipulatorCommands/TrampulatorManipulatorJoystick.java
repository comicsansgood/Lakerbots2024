package frc.robot.commands.TrampulatorCommands.TrampulatorManipulatorCommands;

import frc.robot.subsystems.TrampulatorSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;

public class TrampulatorManipulatorOrient extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final TrampulatorSubsystem m_trampulator;
  XboxController controller;
  public double[] scaledValues;
  private final double scaleFactor = 0.2;

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
    scaledValues[0] = controller.getLeftY() * scaleFactor;
    scaledValues[1] = controller.getRightY() * scaleFactor;

    if(controller.getLeftY() != 0 && controller.getRightY() != 0){ //if not both joystick inputs
      if(controller.getRightY() != 0){ //if right
        m_trampulator.trampulatorManipulatorSpin(scaledValues[1], -scaledValues[1]);//orient piece
      }
      else if (controller.getLeftY() != 0){//if left
        m_trampulator.trampulatorManipulatorSpin(scaledValues[0], scaledValues[0]);//collect/shoot
      }
    }
  }

  @Override
  public void end(boolean interrupted) {

  }
  @Override
  public boolean isFinished() {
    return false;
  }
}
