package frc.robot.subsystems;
import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TrampulatorSubsystem extends SubsystemBase{
    
    public CANSparkMax manipulatorTopMotor;
    public CANSparkMax manipulatorBottomMotor;

    public CANSparkFlex trampulatorWristMotor; //TODO: make this neo 550
    public SparkPIDController trampulatorWristPidController;

    public double tolerence;
    public double target;

    public TrampulatorSubsystem(){ 
        manipulatorTopMotor = new CANSparkMax(16, MotorType.kBrushless);//TODO: Can id
        manipulatorBottomMotor = new CANSparkMax(17, MotorType.kBrushless);//TODO: Can id
        manipulatorTopMotor.setInverted(true);
        manipulatorBottomMotor.setInverted(true);
        
        trampulatorWristMotor = new CANSparkFlex(0, MotorType.kBrushless);//TODO: CAN ID
        trampulatorWristPidController = trampulatorWristMotor.getPIDController();
        trampulatorWristPidController.setP(0.1);//TODO: pid tuning

        tolerence = Constants.TrampulatorConstants.trampulatorTolerance;
        
        

    }

    public void trampulatorManipulatorSpin(double x){
        manipulatorTopMotor.set(x);
        manipulatorBottomMotor.set(x);
    }
    public void trampulatorManipulatorStop(){
        manipulatorTopMotor.set(0);
        manipulatorBottomMotor.set(0);
    }

    public void trampulatorWristHome(){
        trampulatorWristPidController.setReference(Constants.TrampulatorConstants.trampulatorWristMin, ControlType.kSmartMotion);
    }
    
    public void trampulatorWristSetTarget(double reference) throws Exception{
        if(!(reference < Constants.TrampulatorConstants.trampulatorWristMin && reference > Constants.TrampulatorConstants.trampulatorWristMax)){
            throw new Exception("cannot set reference outside limits");
        }else{
            target = reference;
            trampulatorWristPidController.setReference(target, ControlType.kSmartMotion);
        }
    }

    public double trampulatorWristGetPosition(){
        return trampulatorWristMotor.getEncoder().getPosition();  
    }

    public boolean TrampulatorAtTargetPosition(){
        return Math.abs(target - trampulatorWristMotor.getEncoder().getPosition()) < Constants.TrampulatorConstants.trampulatorTolerance;
    }

    
    

    @Override
    public void periodic() {
    }
}
