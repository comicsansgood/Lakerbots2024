package frc.robot.commands.swervedrive.drivebase;

import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;

public class AimToTargetMoving extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private SwerveSubsystem drivetrain;
  private LimelightSubsystem limelight;

  private Translation2d stationaryShotVector;
  private Translation2d velocityVector;
  private Translation2d calculatedVector;

  private double[] scaleFactors = {1,2};//TODO:programmer math
  private double limelightScaleFactor;

  private ChassisSpeeds velocity;
  private double[] limelightData;

  private double requiredAngle;

  public AimToTargetMoving(SwerveSubsystem drivetrain, LimelightSubsystem limelight) {
    this.drivetrain = drivetrain;
    this.limelight = limelight;
    addRequirements(drivetrain, limelight);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    limelightData = limelight.getLimelightData();
    velocity = drivetrain.getRobotVelocity();
    //add calculation of stationary shot
    stationaryShotVector = new Translation2d(limelightData[0], limelightData[2]*limelightScaleFactor);
    velocityVector = new Translation2d(velocity.vxMetersPerSecond, velocity.vyMetersPerSecond);

    calculatedVector = new Translation2d(
      (scaleFactors[0]*stationaryShotVector.getX())-(scaleFactors[1]*velocityVector.getX()),
      (scaleFactors[0]*stationaryShotVector.getY())-(scaleFactors[1]*velocityVector.getY())
      );
    
    requiredAngle = Math.atan(calculatedVector.getY()/calculatedVector.getX());


    drivetrain.drive(new Translation2d(velocityVector.getX(), velocityVector.getY()), requiredAngle, true);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
