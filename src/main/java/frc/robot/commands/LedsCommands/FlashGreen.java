package frc.robot.commands.LedsCommands;
import frc.robot.subsystems.LedsSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class FlashGreen extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final LedsSubsystem leds;

  public FlashGreen(LedsSubsystem leds) {
    this.leds = leds;
    addRequirements(leds);
  }

  @Override
  public void initialize() {
    leds.flashGreen();
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}
