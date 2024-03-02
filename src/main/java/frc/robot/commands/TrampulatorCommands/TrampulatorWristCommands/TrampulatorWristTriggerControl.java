package frc.robot.commands.TrampulatorCommands.TrampulatorWristCommands;

import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.TrampulatorSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;

public class TrampulatorWristTriggerControl extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  
  private final TrampulatorSubsystem trampulator;
  private final XboxController controller;

  
  public final double incrementFactor = 2;
  public final double deadband = 0.05;

  public double reference;
  public double averageTriggerValue;
  
  public TrampulatorWristTriggerControl(TrampulatorSubsystem trampulator, XboxController controller) {
    this.trampulator = trampulator;
    this.controller = controller;
    addRequirements(trampulator);
  }

  @Override
  public void initialize() {
    
  }

  @Override
  public void execute() {
    averageTriggerValue = controller.getLeftTriggerAxis() - controller.getRightTriggerAxis();

    trampulator.trampulatorManipulatorGoToPosition(
      trampulator.trampulatorWristGetPosition() + (incrementFactor * averageTriggerValue)
      );

  }

  @Override
  public void end(boolean interrupted) {
  }
 
  @Override
  public boolean isFinished() {
    return false;
  }
}

