package frc.robot.subsystems;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import au.grapplerobotics.ConfigurationFailedException;
import au.grapplerobotics.LaserCan;


import frc.robot.Constants;


public class IntakeSubsystem extends SubsystemBase{

    public CANSparkMax intakeWristMotor;
    public CANSparkFlex intakeSpinMotor;
    public CANSparkFlex intakeWristFollowMotor;
    public SparkPIDController intakeWristPidController;
    public SparkPIDController intakeSpinPidController;

    public SparkPIDController intakeWristFollowPidController;

    public LaserCan laserCan;
    public final int pieceDistanceThreshold = 100;

    public double tolerence;
    public double target;


    public IntakeSubsystem(){

        intakeWristMotor = new CANSparkMax(12, MotorType.kBrushless);
        intakeWristMotor.restoreFactoryDefaults();
        intakeSpinMotor = new CANSparkFlex(11, MotorType.kBrushless);

        intakeWristFollowMotor = new CANSparkFlex(33, MotorType.kBrushless);//TODO:(IMPORTANT) replace with a good can id number
        //intakeWristFollowMotor.follow(intakeWristFollowMotor, true);
        intakeWristFollowMotor.restoreFactoryDefaults();
        intakeWristMotor.setIdleMode(IdleMode.kBrake);
        intakeWristFollowMotor.setIdleMode(IdleMode.kBrake);


        intakeSpinMotor.setSmartCurrentLimit(80);

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
        
        intakeWristFollowPidController = intakeWristFollowMotor.getPIDController();
            //slot 0
                intakeWristFollowPidController.setP(0.0002500000118743628, 0);
                intakeWristFollowPidController.setFF(0.00017499999376013875, 0);
                intakeWristFollowPidController.setSmartMotionMaxVelocity(1000, 0);
                intakeWristFollowPidController.setSmartMotionMaxAccel(500, 0);
            //slot 1
                intakeWristFollowPidController.setP(0.001,1);
                intakeWristFollowPidController.setFF(0.0017241379246115685,1);
                intakeWristFollowPidController.setSmartMotionMaxVelocity(2*200, 1); 
                intakeWristFollowPidController.setSmartMotionMaxAccel(2*150, 1);
        



        intakeSpinPidController = intakeSpinMotor.getPIDController();
            intakeSpinPidController.setP(0.0010000000474974513);
            intakeSpinPidController.setFF(0.0003100000030826777);
        //intakeSpinMotor.setClosedLoopRampRate(0.03);

        //intakeWristPidController.setSmartMotionMaxVelocity(target, 0)

        laserCan = new LaserCan(0);
        try {
            laserCan.setRangingMode(LaserCan.RangingMode.SHORT);
            laserCan.setRegionOfInterest(new LaserCan.RegionOfInterest(8, 8, 4, 4));
            laserCan.setTimingBudget(LaserCan.TimingBudget.TIMING_BUDGET_33MS);
        } catch (ConfigurationFailedException e) {
            System.out.println("Configuration failed! " + e);
        }

        intakeWristMotor.getEncoder().setPosition(0);
        intakeWristFollowMotor.getEncoder().setPosition(0);

        tolerence = Constants.IntakeConstants.intakeTolerence;
    }

    public void intakeSpin(double x){
        intakeSpinMotor.set(x);
    }



    public void intakeStop(){
        intakeSpinMotor.set(0);
    }


    public void intakeSetVelocity(double reference){
        intakeSpinPidController.setReference(reference, ControlType.kVelocity);
    }


    public void intakeOut(){
        target = Constants.IntakeConstants.intakeOut;
        intakeWristPidController.setReference(target, ControlType.kSmartMotion, 0);
        intakeWristFollowPidController.setReference(-target, ControlType.kSmartMotion, 0);
    }
    
    public void intakeIn(){
        target = Constants.IntakeConstants.intakeIn;
        intakeWristPidController.setReference(target, ControlType.kSmartMotion, 1);
        intakeWristFollowPidController.setReference(-target, ControlType.kSmartMotion, 1);
    }
    public void intakeHome(){
        target = Constants.IntakeConstants.intakeHome;
        intakeWristPidController.setReference(target, ControlType.kSmartMotion, 1);
        intakeWristFollowPidController.setReference(-target, ControlType.kSmartMotion, 1);
    }
    public void intakeWristSpin(double speed){
        intakeWristMotor.set(speed);
        intakeWristFollowMotor.set(speed);
    }

    public double intakeWristGetPosition(){
        return intakeWristMotor.getEncoder().getPosition();
    }
    
    public boolean intakeAtTargetPosition(){
        return Math.abs(target - intakeWristGetPosition()) < tolerence;
    }

    public double getIntakeTargetPosition(){
        return target;
    }

    public int getLaserMeasurment(){
        return laserCan.getMeasurement().distance_mm;
    }

    public boolean isNoteIntaked(){
        return (getLaserMeasurment() - pieceDistanceThreshold <= 0);
    }

    public double getIntakeTemperature(){
        return intakeSpinMotor.getMotorTemperature();
    }



    @Override
    public void periodic(){
        SmartDashboard.putBoolean("isNoteIntaked", isNoteIntaked());
        SmartDashboard.putNumber("lasercan reading", getLaserMeasurment());
        SmartDashboard.putNumber("Intake Motor Temperature",getIntakeTemperature());
        SmartDashboard.putNumber("intake wrist pos", intakeWristGetPosition());
        SmartDashboard.putNumber("intake spin velocity", intakeSpinMotor.getEncoder().getVelocity());
        //SmartDashboard.putBoolean("isIntakeAboveVelocityRequired", );
    }

}
