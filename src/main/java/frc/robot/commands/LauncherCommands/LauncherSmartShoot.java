/*package frc.robot.commands.LauncherCommands;

import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class LauncherSmartShoot extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final LauncherSubsystem launcher;
  private final LimelightSubsystem limelight;

  private double[] data;
  private double scaleFactor = 0.1;//TODO: programmer math



  public LauncherSmartShoot(LauncherSubsystem launcher, LimelightSubsystem limelight) {
    this.launcher = launcher;
    this.limelight = limelight;
    addRequirements(launcher, limelight);
  }

  @Override
  public void initialize() {
    data = limelight.getLimelightData();
    launcher.launcherSetVelocity(scaleFactor * data[2], scaleFactor* data[2]);


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
}*/
