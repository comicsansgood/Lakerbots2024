package frc.robot.subsystems;
import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class AmpulatorSubsystem extends SubsystemBase{
    
    


    public CANSparkMax ampulatorWristMotor;
    public SparkPIDController ampulatorWristPidController;

    public double target;
    public final double ampThreshold = 30;
    public final double tolerence = 1;


    public AmpulatorSubsystem(){ 

        ampulatorWristMotor = new CANSparkMax(17, MotorType.kBrushless);
        ampulatorWristPidController = ampulatorWristMotor.getPIDController();
        ampulatorWristPidController.setFF(0.000167);
        ampulatorWristPidController.setP(0.00001);
        ampulatorWristPidController.setSmartMotionMaxVelocity(2000, 0);
        ampulatorWristPidController.setSmartMotionMaxAccel(2000, 0);
    }

    public void ampulatorGoToPosition(double reference){
        target = reference;
        ampulatorWristPidController.setReference(target, ControlType.kSmartMotion);
    }
    
    public void ampulatorOut(){
        target = Constants.AmpulatorConstants.ampulatorOut;
        ampulatorWristPidController.setReference(target, ControlType.kSmartMotion);
    }
    
    public void ampulatorIn(){
        target = Constants.AmpulatorConstants.ampulatorIn;
        ampulatorWristPidController.setReference(target, ControlType.kSmartMotion);
    }

    public void ampulatorIncrement(double increment){
        target += increment;
        ampulatorWristPidController.setReference(target, ControlType.kSmartMotion);
    }

    public double ampulatorGetPosition(){
        return ampulatorWristMotor.getEncoder().getPosition();
    }

    public boolean ampulatorAtTargetPosition(){
        return (Math.abs(ampulatorGetPosition()-target) < tolerence);
    }

    public void ampulatorGo(double x){
        ampulatorWristMotor.set(x);
    }


    @Override
    public void periodic() {
        SmartDashboard.putNumber("ampulator position", ampulatorGetPosition());
    }
}
