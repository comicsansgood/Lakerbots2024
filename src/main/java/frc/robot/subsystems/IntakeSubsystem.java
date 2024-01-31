package frc.robot.subsystems;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase{

    public CANSparkFlex intakeWristMotor;
    public CANSparkFlex intakeSpinMotor;
    public SparkPIDController intakeWristPidController;

    public double tolerence;
    public double target;


    public IntakeSubsystem(){

        intakeWristMotor = new CANSparkFlex(0, MotorType.kBrushless);//TODO: can id
        intakeSpinMotor = new CANSparkFlex(0, MotorType.kBrushless);//TODO: can id
        
        intakeWristPidController = intakeWristMotor.getPIDController();
        intakeWristPidController.setP(1);
        intakeWristPidController.setSmartMotionAllowedClosedLoopError(0.2, 0);

        tolerence = Constants.IntakeConstants.intakeTolerence;
    }

    public void intakeSpin(double x){
        intakeSpinMotor.set(x);
    }

    public void intakeStop(){
        intakeSpinMotor.set(0);
    }

    public void intakeOut(){
        target = Constants.IntakeConstants.intakeOut;
        intakeWristPidController.setReference(target, ControlType.kSmartMotion);
    }
    
    public void intakeIn(){
        target = Constants.IntakeConstants.intakeIn;
        intakeWristPidController.setReference(target, ControlType.kSmartMotion);
    }

    public double intakeWristGetPosition(){
        return intakeWristMotor.getEncoder().getPosition();
    }
    
    public boolean intakeAtTargetPosition(){
        return Math.abs(target - intakeWristGetPosition()) < tolerence;
    }


    @Override
    public void periodic(){

    }

}
