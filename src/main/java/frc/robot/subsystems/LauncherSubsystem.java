package frc.robot.subsystems;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LauncherSubsystem extends SubsystemBase{
    
    public CANSparkFlex launcherLeftMotor;
    public CANSparkFlex launcherRightMotor;
    public CANSparkMax launcherPivotMotor;

    public SparkPIDController launcherPivotPidController;
    public SparkPIDController launcherLeftVelocityController;
    public SparkPIDController launcherRightVelocityController;



    public double tolerence;
    public double target;

    public LauncherSubsystem(){
        launcherLeftMotor = new CANSparkFlex(9, MotorType.kBrushless);
        launcherRightMotor = new CANSparkFlex(10, MotorType.kBrushless);
        launcherLeftMotor.setInverted(false);
        launcherRightMotor.setInverted(true);


        //launcher pivot
        launcherPivotMotor = new CANSparkMax(14, MotorType.kBrushless);
            launcherPivotPidController = launcherPivotMotor.getPIDController();
            launcherPivotPidController.setFF(0.0005);//TODO PID tuning
            launcherPivotPidController.setSmartMotionMaxVelocity(2000,0);
            launcherPivotPidController.setSmartMotionMaxAccel(2000, 0);

        launcherLeftVelocityController = launcherLeftMotor.getPIDController();
        launcherLeftVelocityController.setP(1);//TODO: tune pid

        launcherRightVelocityController = launcherRightMotor.getPIDController();
        launcherRightVelocityController.setP(0.1);//TODO: tune pid

        tolerence = Constants.TrampulatorConstants.trampulatorTolerance;
     }

    public void launcherGo(){
        launcherLeftMotor.set(-.95);
        launcherRightMotor.set(-.65);
    }


    public void launcherStop() {
        launcherLeftMotor.set(0);
        launcherRightMotor.set(0);
    }

    public void launcherSet(double val1, double val2){
        launcherLeftMotor.set(val1);
        launcherRightMotor.set(val2);
    }


    public void launcherSetVelocity(double leftVelocity, double rightVelocity){
        launcherLeftVelocityController.setReference(leftVelocity, ControlType.kSmartVelocity);
        launcherRightVelocityController.setReference(rightVelocity, ControlType.kSmartVelocity);
    }

    public void launcherAim(double theta){
        if(theta >= Constants.LauncherConstants.launcherMax){
            System.out.println("ERROR: cannot extend pass max");
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
