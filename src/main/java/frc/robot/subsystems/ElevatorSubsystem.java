package frc.robot.subsystems;
import com.revrobotics.CANSparkFlex;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ElevatorSubsystem extends SubsystemBase{

    public CANSparkFlex elevatorMotor;
    public SparkPIDController elevatorPidController;
    public double reference;
    public double tolerence;

    public ElevatorSubsystem(){
        elevatorMotor = new CANSparkFlex(16, MotorType.kBrushless);
        elevatorPidController = elevatorMotor.getPIDController();
        elevatorPidController.setFF(0.00026);
        elevatorPidController.setSmartMotionMaxVelocity(6000, 0);
        elevatorPidController.setSmartMotionMaxAccel(6000, 0);

        tolerence = Constants.ElevatorConstants.elevatorTolerence;

        elevatorMotor.getEncoder().setPosition(0);
    }

    public void elevatorSetTarget(double target){
        reference = target;
        elevatorPidController.setReference(reference, ControlType.kSmartMotion);
    }

    public void elevatorHome(){
         elevatorPidController.setReference(0, ControlType.kSmartMotion);
    }

    public double elevatorGetPosition(){
        return elevatorMotor.getEncoder().getPosition();
    }

    public boolean elevatorIsAtTarget(){
        return Math.abs(reference - elevatorGetPosition()) < tolerence;
    }
    
    @Override
    public void periodic() {
    }
}
