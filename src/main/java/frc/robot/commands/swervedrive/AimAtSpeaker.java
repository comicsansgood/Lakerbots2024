package frc.robot.commands.swervedrive;

import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;

public class AimAtSpeaker extends Command {
  
  private final SwerveSubsystem m_drivetrain;
  private final LimelightSubsystem m_limelight;

  private double[] data;
  private double tolerence = 0.1;
  private double speed = 0.5;
  
  public AimAtSpeaker(SwerveSubsystem drivetrain, LimelightSubsystem limelight) {
    m_drivetrain = drivetrain;
    m_limelight = limelight;
    addRequirements(m_drivetrain, m_limelight);
  }

  @Override
  public void initialize() {
    }


  @Override
  public void execute() {

    data = m_limelight.getLimelightData();
    if(data[0] > tolerence){
      m_drivetrain.drive(new Translation2d(0,0), speed, true);
    }
    else if(data[0] < -tolerence){
      m_drivetrain.drive(new Translation2d(0,0), -speed, true);
    }

  }

  @Override
  public void end(boolean interrupted) {
    m_drivetrain.drive(new Translation2d(0,0), 0, true);
  }

  @Override
  public boolean isFinished() {
  return (Math.abs(data[0]) < tolerence);
  }
}
