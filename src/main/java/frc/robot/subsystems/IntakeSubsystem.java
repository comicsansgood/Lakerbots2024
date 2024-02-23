package frc.robot.subsystems;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase{

    public CANSparkMax intakeWristMotor;
    public CANSparkFlex intakeSpinMotor;
    public SparkPIDController intakeWristPidController;
    private DigitalInput digitalSensor;

    public double tolerence;
    public double target;


    public IntakeSubsystem(){

        intakeWristMotor = new CANSparkMax(12, MotorType.kBrushless);
        intakeSpinMotor = new CANSparkFlex(11, MotorType.kBrushless);

        //intake Wrist Pid
        intakeWristPidController = intakeWristMotor.getPIDController();
            //slot 0
                intakeWristPidController.setP(0.0002500000118743628, 0);
                intakeWristPidController.setFF(0.00017499999376013875, 0);
                intakeWristPidController.setSmartMotionMaxVelocity(1000, 0);
                intakeWristPidController.setSmartMotionMaxAccel(500, 0);
            //slot 1
                intakeWristPidController.setP(0.001,1);
                intakeWristPidController.setFF(0.0017241379246115685,1);
                intakeWristPidController.setSmartMotionMaxVelocity(200, 1); 
                intakeWristPidController.setSmartMotionMaxAccel(150, 1);

        //intakeWristPidController.setSmartMotionMaxVelocity(target, 0)
        digitalSensor = new DigitalInput(1);//TODO: specify channel

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
        intakeWristPidController.setReference(target, ControlType.kSmartMotion, 0);
    }
    
    public void intakeIn(){
        target = Constants.IntakeConstants.intakeIn;
        intakeWristPidController.setReference(target, ControlType.kSmartMotion, 1);
    }
    public void intakeHome(){
        target = Constants.IntakeConstants.intakeHome;
        intakeWristPidController.setReference(target, ControlType.kSmartMotion, 1);
    }
    public void intakeWristSpin(double speed){
        intakeWristMotor.set(speed);
    }

    public double intakeWristGetPosition(){
        return intakeWristMotor.getEncoder().getPosition();
    }
    
    public boolean intakeAtTargetPosition(){
        return Math.abs(target - intakeWristGetPosition()) < tolerence;
    }

    public boolean isNoteIntaked(){
        return digitalSensor.get();
    }


    @Override
    public void periodic(){

    }

}
