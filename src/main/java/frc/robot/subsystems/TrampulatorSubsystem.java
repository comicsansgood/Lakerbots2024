package frc.robot.subsystems;
import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TrampulatorSubsystem extends SubsystemBase{
    
    public CANSparkMax manipulatorTopMotor;
    public CANSparkMax manipulatorBottomMotor;
    public SparkPIDController manipulatorTopPidController;
    public SparkPIDController manipulatorBottomPidController;


    public CANSparkMax trampulatorWristMotor;
    public SparkPIDController trampulatorWristPidController;

    public double tolerence;
    public double target;
    public final double ampThreshold = 30;

    public double manipulatorTarget;

    public TrampulatorSubsystem(){ 
        manipulatorTopMotor = new CANSparkMax(18, MotorType.kBrushless);
        manipulatorBottomMotor = new CANSparkMax(19, MotorType.kBrushless);
        manipulatorTopMotor.setInverted(true);
        manipulatorBottomMotor.setInverted(true);

        manipulatorTopMotor.setIdleMode(IdleMode.kBrake);
        manipulatorBottomMotor.setIdleMode(IdleMode.kBrake);


        manipulatorTopPidController = manipulatorTopMotor.getPIDController();
        manipulatorBottomPidController = manipulatorBottomMotor.getPIDController();

        trampulatorWristMotor = new CANSparkMax(17, MotorType.kBrushless);
        trampulatorWristPidController = trampulatorWristMotor.getPIDController();
        trampulatorWristPidController.setFF(0.000167);
        trampulatorWristPidController.setP(0.0001);//TODO: pid tuning
        trampulatorWristPidController.setSmartMotionMaxVelocity(1000, 0);
        trampulatorWristPidController.setSmartMotionMaxAccel(1000, 0);

        tolerence = Constants.TrampulatorConstants.trampulatorTolerance;
        
        

    }

    
    public void trampulatorManipulatorGoToPosition(double position){
        target = position;
        manipulatorTopPidController.setReference(target, ControlType.kSmartMotion);
        manipulatorBottomPidController.setReference(target, ControlType.kSmartMotion);
    }

    public boolean trampulatorIsNoteIntaked(){
        return ((manipulatorTopMotor.getOutputCurrent() + manipulatorBottomMotor.getOutputCurrent())/2 > ampThreshold);
    }

    public double[] trampulatorManipulatorGetPosition(){
        double[] positions = {manipulatorTopMotor.getEncoder().getPosition(), manipulatorBottomMotor.getEncoder().getPosition()};
        return(positions);
    }

    public boolean trampulatorManipulatorAtTarget(){
        return(manipulatorTopMotor.getEncoder().getPosition()-target < tolerence && 
        manipulatorTopMotor.getEncoder().getPosition()-target < tolerence);
    }


    public void trampulatorManipulatorSpin(double speed1, double speed2){
        manipulatorTopMotor.set(speed1);
        manipulatorBottomMotor.set(speed2);
    }
    public void trampulatorManipulatorStop(){
        manipulatorTopMotor.set(0);
        manipulatorBottomMotor.set(0);
    }

    public void trampulatorWristHome(){
        trampulatorWristPidController.setReference(Constants.TrampulatorConstants.trampulatorWristMin, ControlType.kSmartMotion);
    }
    
    public void trampulatorWristSetTarget(double reference){
       
            target = reference;
            trampulatorWristPidController.setReference(target, ControlType.kSmartMotion);
        
    }

    public double trampulatorWristGetPosition(){
        return trampulatorWristMotor.getEncoder().getPosition();  
    }

    public boolean TrampulatorAtTargetPosition(){
        return Math.abs(target - trampulatorWristMotor.getEncoder().getPosition()) < Constants.TrampulatorConstants.trampulatorTolerance;
    }

    public void trampulatorWristSpin(double speed){
        trampulatorWristMotor.set(speed);
    }
    
    

    @Override
    public void periodic() {
    }
}
