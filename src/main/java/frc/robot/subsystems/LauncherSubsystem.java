package frc.robot.subsystems;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LauncherSubsystem extends SubsystemBase{
    
    public CANSparkFlex launcherLeftMotor;
    public CANSparkFlex launcherRightMotor;
    public CANSparkFlex launcherPivotMotor;
    public SparkPIDController launcherPivotPidController;

    public double tolerence;
    public double target;

    public LauncherSubsystem(){
        launcherLeftMotor = new CANSparkFlex(13, MotorType.kBrushless);
        launcherRightMotor = new CANSparkFlex(14, MotorType.kBrushless);
        launcherLeftMotor.setInverted(false);
        launcherRightMotor.setInverted(true);

        launcherPivotMotor = new CANSparkFlex(0, MotorType.kBrushless);//TODO CAN ID
        launcherPivotPidController = launcherPivotMotor.getPIDController();
        launcherPivotPidController.setP(0.1);//TODO PID tuning

        tolerence = Constants.TrampulatorConstants.trampulatorTolerance;

    }
    public void launcherGo(){
        launcherLeftMotor.set(.95);
        launcherRightMotor.set(.65);
    }


    public void launcherStop() {
        launcherLeftMotor.set(0);
        launcherRightMotor.set(0);
    }

    public void launcherSet(double val1, double val2){
        launcherLeftMotor.set(val1);
        launcherRightMotor.set(val2);
    }

    public void launcherAim(double theta) throws Exception{
        if(theta >= Constants.LauncherConstants.launcherMax){
            throw new Exception("Cannot extend pass maximum");
        }else{
            target = theta;
            launcherPivotPidController.setReference(target, ControlType.kSmartMotion);
        }
    }

    public double launcherGetPosition(){
        return launcherPivotMotor.getEncoder().getPosition();
    }

    public boolean LauncherAtTargetPosition(){
        return Math.abs(target - launcherGetPosition()) < tolerence;
    }



    @Override
    public void periodic() {
    }
}
