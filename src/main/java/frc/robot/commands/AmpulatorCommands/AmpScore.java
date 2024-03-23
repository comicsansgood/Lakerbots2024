package frc.robot.commands.AmpulatorCommands;
import frc.robot.Constants;
import frc.robot.subsystems.AmpulatorSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import edu.wpi.first.wpilibj2.command.Command;


public class AmpScore extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final AmpulatorSubsystem ampulator;
  private final LauncherSubsystem launcher;
  private final ElevatorSubsystem elevator;

  public AmpScore(AmpulatorSubsystem ampulator, LauncherSubsystem launcher, ElevatorSubsystem elevator) {
    this.ampulator = ampulator;
    this.launcher = launcher;
    this.elevator = elevator;
    addRequirements(ampulator, launcher, elevator);
  }

  @Override
  public void initialize() {

    if(true){
      launcher.launcherStop();
      launcher.launcherAimHome();
      elevator.elevatorSetTarget(0);
      ampulator.ampulatorIn();
    }
    else{
      launcher.launcherSet(-.4, -.4);
      launcher.launcherAim(Constants.LauncherConstants.launcherAngleAmpScore);
      ampulator.ampulatorOut();

      elevator.elevatorSetTarget(-30);


    }
    // not uded sdg bufgffConstants.AmpulatorConstants.isAmpulatorOut = Constants.AmpulatorConstants.isAmpulatorOut ? false : true;//flip the value

  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    /*return ampulator.ampulatorAtTargetPosition() & 
           launcher.LauncherAtTargetPosition() & 
           elevator.elevatorIsAtTarget();*/
      return true;
  }
}

