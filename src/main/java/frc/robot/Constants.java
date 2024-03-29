// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;
import swervelib.math.Matter;
import swervelib.parser.PIDFConfig;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean constants. This
 * class should not be used for any other purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants
{

  public static final double ROBOT_MASS = (148 - 20.3) * 0.453592; // 32lbs * kg per pound
  public static final Matter CHASSIS    = new Matter(new Translation3d(0, 0, Units.inchesToMeters(8)), ROBOT_MASS);
  public static final double LOOP_TIME  = 0.13; //s, 20ms + 110ms sprk max velocity lag

  public static final class Auton
  {

    public static final PIDFConfig TranslationPID     = new PIDFConfig(0.7, 0, 0);
    public static final PIDFConfig angleAutoPID = new PIDFConfig(0.4, 0, 0.01);

    public static final double MAX_ACCELERATION = 4;
  }

  public static final class Drivebase
  {

    // Hold time on motor brakes when disabled
    public static final double WHEEL_LOCK_TIME = 10; // seconds
  }

  public static class OperatorConstants
  {

    // Joystick Deadband
    public static final double LEFT_X_DEADBAND = 0.1; //used to be .01
    public static final double LEFT_Y_DEADBAND = 0.1; //used to be .01
    public static final double RIGHT_X_DEADBAND = 0.1; //used to be .01
    public static final double TURN_CONSTANT = 24;//used to be 6  - 3/1/24
  }

  public static class LauncherConstants{
    public static final double launcherHome = 0;
    public static final double launcherCollect = 0;
    public static final double launcherSubwooferScore = 30;
    public static final double launcherMax = 10000;//TODO: still programmer math
    public static final double launcherTolerence = 0.1; //TODO: programmer math

    //angles
    public static final double launcherAngleSubwoofer = 50;
    public static final double launcherAnglePodium = 14;
    public static final double launcherAngleAmpSafetyZone = 14;
    public static final double launcherAngleAmpSide = 22;
    public static final double launcherAngleMiddlePiece = 23.5;

    public static final double launcherAngleAUTOSubwoofer = 50;
    public static final double launcherAngleAUTOAmpSide = 22;
    public static final double launcherAngleAUTOMiddlePiece = 23.5;
    public static final double launcherAngleAUTOOffsetSubwoofer = 22;
    public static final double launcherAngleAUTOCenterPieceShot = 19.5;

  }

  public static class IntakeConstants{
    public static final double intakeHome = 0;
    public static final double intakeIn = -0.5 * 4; //TODO:programmer math
    public static final double intakeOut = -4.8 * 4; //TODO: still programmer math
    public static final double intakeTolerence = 0.2; //TODO : programmer math
  } 
  
  public static class ClimberConstants{
    public static final double climberHome = 0;
    public static final double cimberOut = 5000;//TODO: programmer math
    public static final double climberLimit = 10000; //TODO: still programmer math
    public static final double climberTolerence = 0.1;//TODO: programmer math
  }
  public static class TrampulatorConstants{
    public static final double trampulatorWristMin = 0;
    public static final double trampulatorWristMax = 10000;//TODO: still programmer math
    public static final double trampulatorWristAmp = 1000;//TODO:programmer math
    public static final double trampulatorWristTrap = 1000;//TODO:programmer math


    public static final double trampulatorOrientAmp = 10000;//TODO: still programmer math
    public static final double trampulatorOrientTrap = 10000;//TODO: still programmer math
    public static final double trampulatorOrientHome = 0;//TODO: still programmer math



    public static final double trampulatorTolerance = 0.1;//TODO: still programmer math
  }

  public static class ElevatorConstants{
    public static final double elevatorMax = 10000;//TODO: still programmer math
    public static final double elevatorHome = 0; //TODO:still programmer math\
    public static final double elevatorTolerence = 0.1;//TODO: programmer math
  }
}

