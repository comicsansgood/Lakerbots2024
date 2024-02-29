package frc.robot.subsystems;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class ClimberSubsystem extends SubsystemBase{

    public CANSparkFlex climberMotor;
    public SparkPIDController climberPidController;

    public double tolerence;
    public double target;


    public ClimberSubsystem(){
        climberMotor = new CANSparkFlex(15, MotorType.kBrushless);
        climberPidController = climberMotor.getPIDController();
        climberPidController.setFF(0.00025);//TODO tune PID
        climberPidController.setSmartMotionMaxVelocity(4000, 0);
        climberPidController.setSmartMotionMaxAccel(3000, 0);

        tolerence = Constants.ClimberConstants.climberTolerence;
        climberMotor.getEncoder().setPosition(0);
    }

    public void climberSetTarget(double reference){
            target = reference;
            climberPidController.setReference(target, ControlType.kSmartMotion);
    }

    public double climberGetPosition(){
        return climberMotor.getEncoder().getPosition();
    }

    public boolean climberAtTargetPosition(){
        return Math.abs(target - climberGetPosition()) < tolerence;
    }

    public void climberSpin(double speed){
        climberMotor.set(speed);
    }


    
    @Override
    public void periodic() {
    }

}
