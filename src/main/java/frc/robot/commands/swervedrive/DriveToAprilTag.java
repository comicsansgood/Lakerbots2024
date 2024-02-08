package frc.robot.commands.swervedrive;

import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;

public class DriveToAprilTag extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  
  private final SwerveSubsystem m_drivetrain;
  private final LimelightSubsystem m_limelight;

  private Pose2d currentPose;
  private double[] data;

  private double tolerence = 0.1;
  private double speed = 0.5;
    private Translation2d translation = new Translation2d(speed,0);

  
  public DriveToAprilTag(SwerveSubsystem drivetrain, LimelightSubsystem limelight) {
    m_drivetrain = drivetrain;
    m_limelight = limelight;
    addRequirements(m_drivetrain, m_limelight);
  }

  @Override
  public void initialize() {
    data = m_limelight.getLimelightData();
  }


  @Override
  public void execute() {

    currentPose = m_drivetrain.getPose();
    
    if(data[0] - currentPose.getX() > tolerence || data[0] - currentPose.getX() < -tolerence){
      translation = new Translation2d(-translation.getX(), translation.getY());
    }
    m_drivetrain.drive(translation, 0, true);


  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
  return false;
  }
}
