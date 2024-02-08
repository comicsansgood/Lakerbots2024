package frc.robot.commands.swervedrive;

import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;

public class DriveToPositionRelativeToAprilTag extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  
  private final SwerveSubsystem m_drivetrain;
  private final LimelightSubsystem m_limelight;
  private final Pose2d desiredPose;

  private double[] data;
  private double[] speeds;

  private final double tolerence = 0.1;

  
  public DriveToPositionRelativeToAprilTag(SwerveSubsystem m_drivetrain, LimelightSubsystem m_limelight, Pose2d desiredPose) {

    this.m_drivetrain = m_drivetrain;
    this.m_limelight = m_limelight;
    this.desiredPose = desiredPose;


    addRequirements(m_drivetrain, m_limelight);

  }

  @Override
  public void initialize() {
    data = m_limelight.getLimelightData();
    m_drivetrain.resetOdometry(new Pose2d(new Translation2d(data[0], data[2]), m_drivetrain.getHeading()));
    m_drivetrain.setOdometryTarget(desiredPose);
    m_drivetrain.setOdometryAllowedError(0.1,3);
  }

  @Override
  public void execute() {
  speeds = m_drivetrain.getOdometrySpeeds(m_drivetrain.getPose());
  m_drivetrain.drive(new Translation2d(speeds[0], speeds[1]), speeds[3], true);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return m_drivetrain.isOdometryAtTargetPosition();
  }
}
