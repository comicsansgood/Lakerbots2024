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
        climberMotor = new CANSparkFlex(0, MotorType.kBrushless);//TODO Can id
        climberPidController = climberMotor.getPIDController();
        climberPidController.setP(0.1);//TODO tune PID

        tolerence = Constants.ClimberConstants.climberTolerence;
    }

    public void ClimberSetTarget(double reference) throws Exception{
        if(reference > Constants.ClimberConstants.climberLimit || reference < Constants.ClimberConstants.climberHome){
            throw new Exception("cannot set reference outside climber limits");
        }
        else{
            target = reference;
            climberPidController.setReference(target, ControlType.kSmartMotion);
        }
    }

    public double ClimberGetPosition(){
        return climberMotor.getEncoder().getPosition();
    }

    public boolean ClimberAtTargetPosition(){
        return Math.abs(target - ClimberGetPosition()) < tolerence;
    }


    
    @Override
    public void periodic() {
    }
}
