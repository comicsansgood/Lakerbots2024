package frc.robot.commands.AmpulatorCommands;
import frc.robot.subsystems.AmpulatorSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class AmpulatorIncrement extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final AmpulatorSubsystem ampulator;
  private double increment;

  public AmpulatorIncrement(AmpulatorSubsystem ampulator, double increment) {
    this.ampulator = ampulator;
    this.increment = increment;

    addRequirements(ampulator);
  }

  @Override
  public void initialize() {
    ampulator.ampulatorIncrement(increment);
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
