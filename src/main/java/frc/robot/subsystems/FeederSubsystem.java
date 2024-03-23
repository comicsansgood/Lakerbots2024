package frc.robot.subsystems;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FeederSubsystem extends SubsystemBase{

    public CANSparkFlex feederMotor;

    public FeederSubsystem(){

        feederMotor = new CANSparkFlex(13, MotorType.kBrushless);
        feederMotor.setIdleMode(IdleMode.kBrake);

    }

    public void feederStop(){
        feederMotor.set(0);
    }

    public void feederGo(double x){
        feederMotor.set(x);
    }
    @Override
    public void periodic(){

    }

}
