package frc.robot.subsystems;
import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TrampulatorSubsystem extends SubsystemBase{
    
    public CANSparkMax manipulatorTopMotor;
    public CANSparkMax manipulatorBottomMotor;

    public TrampulatorSubsystem(){ 
        manipulatorTopMotor = new CANSparkMax(16, MotorType.kBrushless);//TODO: Can id
        manipulatorBottomMotor = new CANSparkMax(17, MotorType.kBrushless);//TODO: Can id
        
        manipulatorTopMotor.setInverted(true);
        manipulatorBottomMotor.setInverted(true);
    }

    public void trampulatorManipulatorSpin(double x){
        manipulatorTopMotor.set(x);
        manipulatorBottomMotor.set(x);
    }
    public void trampulatorManipulatorStop(){
        manipulatorTopMotor.set(0);
        manipulatorBottomMotor.set(0);
    }

    

    @Override
    public void periodic() {
    }
}
