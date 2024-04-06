package frc.robot.commands.LedsCommands;

import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.LedsSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class RedAndGreen extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  
  private final LedsSubsystem leds;

  
  public RedAndGreen(LedsSubsystem leds) {
    this.leds = leds;
    addRequirements(leds);
  }

  @Override
  public void initialize() {
    leds.redAndGreen();
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
