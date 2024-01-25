package frc.robot.commands.swervedrive.drivebase;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class DriveToTarget extends Command
{

  private final SwerveSubsystem m_drivetrain;
  public Pose2d targetPose;
  public Pose2d currentPose;
  public double[] calculatedSpeeds;
  public double allowedPositionalTolerance, allowedRotationalTolerance;

  public DriveToTarget(SwerveSubsystem m_drivetrain, 
    Pose2d targetPose, 
    double allowedPositionalTolerance, 
    double allowedRotationalTolerance
    ){
    this.m_drivetrain = m_drivetrain;
    this.targetPose = targetPose;
    this.allowedPositionalTolerance = allowedPositionalTolerance;
    this.allowedRotationalTolerance = allowedRotationalTolerance;
 
    addRequirements(m_drivetrain);
  }

  @Override
  public void initialize()
  {
    m_drivetrain.setOdometryAllowedError(allowedPositionalTolerance, allowedRotationalTolerance);
    currentPose = m_drivetrain.getPose();
    m_drivetrain.setOdometryTarget(targetPose);
  }

  @Override
  public void execute()
  {
    currentPose = m_drivetrain.getPose();
    calculatedSpeeds = m_drivetrain.getOdometrySpeeds(currentPose);

    SmartDashboard.putNumber("odomXspeed", calculatedSpeeds[0]);
    SmartDashboard.putNumber("odomYspeed", calculatedSpeeds[1]);
    SmartDashboard.putString("targetpose", targetPose.toString());
    
    m_drivetrain.driveFieldOriented(new ChassisSpeeds(
      calculatedSpeeds[0], 
      calculatedSpeeds[1], 
      calculatedSpeeds[2]
      ));

  }

  @Override
  public boolean isFinished()
  {
    /*if(m_drivetrain.XPid.getSetpoint() == targetPose.getX() &&
    m_drivetrain.YPid.getSetpoint() == targetPose.getY()){
    return m_drivetrain.isOdometryAtTargetPosition();
    }
    else{
      return false;
    }
    */
    return false;
  }

  @Override
  public void end(boolean interrupted)
  {
    m_drivetrain.drive(new ChassisSpeeds(0,0,0));
  }
}
