package frc.robot.commands;

import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.drive.Drive;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;

public class RotateToTarget extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final LimelightSubsystem limelight;
  private final Drive drive;

  public double tagX;
  public double error;
  public double tolerence;
  public double p = 0.1;

  public PIDController pidController = new PIDController(p, 0, 0);

   

  
  public RotateToTarget(LimelightSubsystem limelight, Drive drive) {
    this.limelight = limelight;
    this.drive = drive;
    addRequirements(drive, limelight);

  }

  @Override
  public void initialize() {
    //pidController.calculate(error, p)
  }

  @Override
  public void execute() {
    drive.runVelocity(new ChassisSpeeds(0,0, 0));
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
