package frc.robot.commands.AmpulatorCommands;
import frc.robot.subsystems.AmpulatorSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class AmpulatorIn extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final AmpulatorSubsystem ampulator;

  public AmpulatorIn(AmpulatorSubsystem ampulator) {
    this.ampulator = ampulator;
    addRequirements(ampulator);
  }

  @Override
  public void initialize() {
    ampulator.ampulatorIn();
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
