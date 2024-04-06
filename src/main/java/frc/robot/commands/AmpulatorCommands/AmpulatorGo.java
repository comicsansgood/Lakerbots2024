package frc.robot.commands.AmpulatorCommands;

import frc.robot.subsystems.AmpulatorSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class AmpulatorGo extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  
  private final AmpulatorSubsystem ampulator;
  private double value;

  
  public AmpulatorGo(AmpulatorSubsystem ampulator, double value) {
    this.ampulator = ampulator;
    this.value = value;
    addRequirements(ampulator);
  }

  @Override
  public void initialize() {
    ampulator.ampulatorGo(value);
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
