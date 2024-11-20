package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.LimelightHelpers;
import frc.robot.RobotContainer;

public class LimelightSubsystem extends SubsystemBase{

    public NetworkTable table;
    public double x;
    public double y;
    public double a;
    public int[] validIds = {7, 8};
    public LimelightHelpers.PoseEstimate mt2;

    public LimelightSubsystem(){
        table = NetworkTableInstance.getDefault().getTable("limelight");
        LimelightHelpers.SetFiducialIDFiltersOverride("limelight", validIds);
    }

    public Pose2d getEstimatedPose(){
        if(!(mt2.tagCount == 0 || (180/Math.PI)*RobotContainer.m_drive.gyroInputs.yawVelocityRadPerSec >= 720)){
            return mt2.pose;
        }
        else{
            return null;
        }
    }

    public double getTimeStamp(){
        return mt2.timestampSeconds;
    }

    public double getTagX(){
        return table.getEntry("tx").getDouble(0);
    }

    @Override
    public void periodic(){
        LimelightHelpers.SetRobotOrientation("limelight", RobotContainer.m_drive.gyroInputs.yawPosition.getDegrees(), 0, 0, 0, 0, 0);
        mt2 = LimelightHelpers.getBotPoseEstimate_wpiBlue_MegaTag2("limelight");
        
        x = table.getEntry("tx").getDouble(0);
        y = table.getEntry("ty").getDouble(0);
        a = table.getEntry("ta").getDouble(0);
    
        SmartDashboard.putNumber("limelight x", x);
        SmartDashboard.putNumber("linelight y ", y);
        SmartDashboard.putNumber("limelight a", a);
        }
}
