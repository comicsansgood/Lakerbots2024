package frc.robot;

public final class Constants
{
  public static final Mode currentMode = Mode.REAL;
  public static final boolean tuningMode = true;

  public static enum Mode {
    REAL,

    BUSTER,

    TANK,

    SIM,

    REPLAY
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
    public static final double launcherMax = 10000;
    public static final double launcherTolerence = 0.1;

    //angles
    public static final double launcherAngleSubwoofer = 50;
    public static final double launcherAnglePodium = 14;
    public static final double launcherAngleWingIt = 32;
    public static final double launcherAngleAmpSide = 22;
    public static final double launcherAngleMiddlePiece = 23.5;
    public static final double launcherAngleAmpScore = 29.5;

    public static final double launcherAngleAUTOSubwoofer = 50;
    public static final double launcherAngleAUTOAmpSide = 22;
    public static final double launcherAngleAUTOOffsetSubwoofer = 22;
    public static final double launcherAngleAUTOCenterPiece = 25;
    public static final double launcherAngleAUTOArbPoint = 33;
    public static final double launcherAngleAUTOSecondArbPoint = 30;
    public static final double launcherAngleAUTOTopPiece = 21;
    public static final double launcherAngleAUTOPodium = 14;
    public static final double launcherAngleAUTOPodiumArbPoint = 32.5;

  } 

  public static class IntakeConstants{
    public static final double intakeHome = 0;
    public static final double intakeIn = -0.5 * 4;
    public static final double intakeOut = -19.2;
    public static final double intakeLaunch = -3;
    public static final double intakeTolerence = 0.2;
  } 
  
  public static class ClimberConstants{
    public static final double climberHome = 0;
    public static final double cimberOut = 5000;
    public static final double climberLimit = 10000;
    public static final double climberTolerence = 0.1;
  }

  public static class AmpulatorConstants{
    public static final double ampulatorIn = -5;
    public static final double ampulatorHome = 0; 
    public static final double ampulatorOut = 2;
  }

  public static class ElevatorConstants{
    public static final double elevatorMax = 1000;
    public static final double elevatorAmpScore = -55.5;
    public static final double elevatorHome = 0;
    public static final double elevatorTolerence = 5;
  }
}

