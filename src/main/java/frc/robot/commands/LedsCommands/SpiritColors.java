package frc.robot.commands.LedsCommands;

import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.LedsSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class SpiritColors extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  
  private final LedsSubsystem leds;

  
  public SpiritColors(LedsSubsystem leds) {
    this.leds = leds;
    addRequirements(leds);
  }

  @Override
  public void initialize() {
    leds.spiritColors();
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
