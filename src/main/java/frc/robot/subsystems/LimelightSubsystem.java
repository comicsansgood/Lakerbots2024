package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class LimelightSubsystem extends SubsystemBase{


    public NetworkTable table;
    public NetworkTableEntry tx;
    public NetworkTableEntry ty;
    public NetworkTableEntry ta;
    public double x;
    public double y;
    public double a;
    public double[] tablelist = new double[3];
    

    public LimelightSubsystem(){

        table = NetworkTableInstance.getDefault().getTable("limelight");
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
        ta = table.getEntry("ta");
        
    }

    public double[] getLimelightData(){
        tablelist[0] = x;
        tablelist[1] = y;
        tablelist[3] = a;
        return tablelist;
    }


    @Override
    public void periodic(){
        //read values periodically
        x = tx.getDouble(0.0);
        y = ty.getDouble(0.0);
        a = ta.getDouble(0.0);
        }

}
