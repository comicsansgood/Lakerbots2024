package frc.robot.commands;

import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class EndAuto extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  
  private final LauncherSubsystem launcher;
  private final IntakeSubsystem intake;
  private final FeederSubsystem feeder;
  

  
  public EndAuto(LauncherSubsystem launcher, IntakeSubsystem intake, FeederSubsystem feeder) {
    this.launcher = launcher;
    this.intake = intake;
    this.feeder = feeder;
    addRequirements(launcher, intake, feeder);
  }

  @Override
  public void initialize() {
    launcher.launcherStop();
    feeder.feederStop();
    intake.intakeStop();
    launcher.launcherAimHome();
    intake.intakeIn();
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
