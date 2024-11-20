package frc.robot.commands.LedsCommands;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LedsSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class UpdateLeds extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final LedsSubsystem leds;
  private final IntakeSubsystem intake;
  private final FeederSubsystem feeder;
  public int x;

  public UpdateLeds(LedsSubsystem leds, IntakeSubsystem intake, FeederSubsystem feeder) {
    this.leds = leds;
    this.intake = intake;
    this.feeder = feeder;
    addRequirements(leds);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {

    if(feeder.getFeederState() & intake.isNoteIntaked()){// if its centering
      leds.yellow();
    }
    else if(feeder.getFeederState()){// if intaking but no note
      leds.red();
    }
    else if(intake.isNoteIntaked()){//if note but done centering
      leds.green();
    }
    else{//normal operation
      leds.spiritColors();
    }
  }



  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
