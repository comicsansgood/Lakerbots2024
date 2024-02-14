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
        climberPidController.setP(0.1);//TODO tune PID

        tolerence = Constants.ClimberConstants.climberTolerence;
    }

    public void climberSetTarget(double reference){
        if(reference > Constants.ClimberConstants.climberLimit || reference < Constants.ClimberConstants.climberHome){
            System.out.println("cannot set reference outside climber limits");
        }
        else{
            target = reference;
            climberPidController.setReference(target, ControlType.kSmartMotion);
        }
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
