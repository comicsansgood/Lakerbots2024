package frc.robot.subsystems;

import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import com.revrobotics.CANSparkFlex;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase{

    public CANSparkFlex intakeWristMotor;
    public CANSparkFlex intakeSpinMotor;
    public SparkPIDController intakeWristPidController;

    public final double OutPosition = 1000;
    public final double InPosition = 0;

    public IntakeSubsystem(){

        intakeWristMotor = new CANSparkFlex(0, MotorType.kBrushless);//TODO: can id
        intakeSpinMotor = new CANSparkFlex(0, MotorType.kBrushless);//TODO: can id
        
        intakeWristPidController = intakeWristMotor.getPIDController();
        intakeWristPidController.setP(1);
        intakeWristPidController.setSmartMotionAllowedClosedLoopError(0.2, 0);
    }

    public void intakeSpin(double x){
        intakeSpinMotor.set(x);
    }

    public void intakeStop(){
        intakeSpinMotor.set(0);
    }

    public void intakeOut(){
        intakeWristPidController.setReference(OutPosition, ControlType.kSmartMotion);
    }
      public void intakeIn(){
        intakeWristPidController.setReference(InPosition, ControlType.kSmartMotion);
    }



    @Override
    public void periodic(){

    }

}
