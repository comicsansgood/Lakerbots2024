package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.DriveCommands;
import frc.robot.commands.TurnEverythingOn;
import frc.robot.commands.TurnEverythingOnForever;
import frc.robot.commands.ClimberCommands.ClimberGoToPosition;
import frc.robot.commands.ClimberCommands.ClimberSpin;
import frc.robot.commands.ElevatorCommands.ElevatorGoToPosition;
import frc.robot.commands.FeederCommands.FeederGo;
import frc.robot.commands.FeederCommands.FeederGoForever;
import frc.robot.commands.FeederCommands.FeederJoystick;
import frc.robot.commands.IntakeCommands.SmartIntake;
import frc.robot.commands.IntakeCommands.SendItIntakeAuto;
import frc.robot.commands.IntakeCommands.IntakeSpin;
import frc.robot.commands.IntakeCommands.IntakeWristOut;
import frc.robot.commands.IntakeCommands.SecondIntakeAndLaunch;
import frc.robot.commands.LauncherCommands.LauncherAim;
import frc.robot.commands.LauncherCommands.LauncherAimWithWarmup;
import frc.robot.commands.LauncherCommands.LauncherAmpScore;
import frc.robot.commands.LauncherCommands.LauncherStop;
import frc.robot.commands.LauncherCommands.LaunchWithDelay;
import frc.robot.commands.TrampulatorCommands.SmartAmpScore;
import frc.robot.commands.TrampulatorCommands.SmartTrapScore;
import frc.robot.commands.TrampulatorCommands.TrampulatorHome;
import frc.robot.commands.TrampulatorCommands.TrampulatorSpit;
import frc.robot.commands.TrampulatorCommands.TrampulatorManipulatorCommands.TrampulatorManipulatorOrient;
import frc.robot.commands.TrampulatorCommands.TrampulatorWristCommands.TrampulatorWristGoToPosition;
import frc.robot.commands.TrampulatorCommands.TrampulatorWristCommands.TrampulatorWristSpin;
import frc.robot.commands.TrampulatorCommands.TrampulatorWristCommands.TrampulatorWristTriggerControl;

import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.TrampulatorSubsystem;
import frc.robot.subsystems.drive.Drive;
import frc.robot.subsystems.drive.GyroIOPigeon2;
import frc.robot.subsystems.drive.ModuleIOTalonFX;
import frc.robot.commands.LauncherCommands.VariableLaunch;
import java.io.File;
import com.pathplanner.lib.auto.NamedCommands;

import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class RobotContainer
{

  //Subsystem Declaration

  private final LauncherSubsystem m_launcher = new LauncherSubsystem();
  public final ElevatorSubsystem m_elevator = new ElevatorSubsystem();
  private final FeederSubsystem m_feeder = new FeederSubsystem();
  //private final TrampulatorSubsystem m_trampulator = new TrampulatorSubsystem();
  private final IntakeSubsystem m_intake = new IntakeSubsystem();
  private final ClimberSubsystem m_climber = new ClimberSubsystem();

  private final Drive m_drive = new Drive(new GyroIOPigeon2(),
            new ModuleIOTalonFX(0),
            new ModuleIOTalonFX(1),
            new ModuleIOTalonFX(2),
            new ModuleIOTalonFX(3));
  
  XboxController driverXbox = new XboxController(0);
  XboxController operatorXbox = new XboxController(1);
  //TODO: janky fix
  XboxController tempController = new XboxController(3);

  SendableChooser<Command> m_chooser = new SendableChooser<>();



  public RobotContainer()
  {



    //------------------------------------Commands for Pathing-------------------------------------------
    NamedCommands.registerCommand("autoShootCenterSubwoofer", new VariableLaunch(m_launcher, m_feeder, m_elevator, m_intake, Constants.LauncherConstants.launcherAngleSubwoofer));
    NamedCommands.registerCommand("autoShootOffsetSubwoofer", new VariableLaunch(m_launcher, m_feeder, m_elevator, m_intake, Constants.LauncherConstants.launcherAngleAUTOOffsetSubwoofer));
    NamedCommands.registerCommand("autoShootCenterPieceShot", new VariableLaunch(m_launcher, m_feeder, m_elevator, m_intake, Constants.LauncherConstants.launcherAngleAUTOCenterPieceShot));
    NamedCommands.registerCommand("autoShootMiddlePiece", new VariableLaunch(m_launcher, m_feeder, m_elevator, m_intake, Constants.LauncherConstants.launcherAngleAUTOMiddlePiece));
    NamedCommands.registerCommand("AimFromMiddlePiece", new LauncherAim(m_launcher, Constants.LauncherConstants.launcherAngleAUTOMiddlePiece));
    NamedCommands.registerCommand("AimFromAmpSide", new LauncherAim(m_launcher, Constants.LauncherConstants.launcherAngleAUTOAmpSide));
    NamedCommands.registerCommand("collect", new SendItIntakeAuto(m_intake, m_feeder, driverXbox));
    NamedCommands.registerCommand("2ndcollect", new SecondIntakeAndLaunch(m_intake, m_feeder, m_launcher, driverXbox));
    NamedCommands.registerCommand("spinIntake", new IntakeSpin(m_intake, 0.75));
    NamedCommands.registerCommand("TurnEverythingOn", new TurnEverythingOn(m_intake, m_feeder, m_launcher));
    NamedCommands.registerCommand("TurnEverythingOnForever", new TurnEverythingOnForever(m_intake, m_feeder, m_launcher));
    NamedCommands.registerCommand("feederGo", new FeederGoForever(m_feeder, -1));
    //NamedCommands.registerCommand("autoShootCenterSubwoofer", new LaunchAtSubwoofer(m_drivetrain, m_launcher, m_feeder, m_elevator));
    //NamedCommands.registerCommand("autoShootMiddlePiece", new LaunchAtMiddlePiece(m_drivetrain, m_launcher, m_feeder, m_elevator));

    //-----------------------------------------Auto options for chooser------------------------------------
    SmartDashboard.putData("autochooser", m_chooser);
    
  //-----------------------------------------Smart Dashboard Buttons-----------------------------------------------------
  SmartDashboard.putData("intake reverse", new IntakeSpin(m_intake, -1));
  SmartDashboard.putData("intake spin 100", new IntakeSpin(m_intake, 1)); 
  SmartDashboard.putData("intake stop", new IntakeSpin(m_intake, 0));
  SmartDashboard.putData("climber down", new ClimberSpin(m_climber, 0.25));
  SmartDashboard.putData("climber up", new ClimberSpin(m_climber, -0.25));
  SmartDashboard.putData("climber stop", new ClimberSpin(m_climber, 0));
  /*SmartDashboard.putData("trampwrist out", new TrampulatorWristSpin(m_trampulator, 0.25));
  SmartDashboard.putData("trampwrist in ", new TrampulatorWristSpin(m_trampulator, -0.25));
  SmartDashboard.putData("trampwrist stop", new TrampulatorWristSpin(m_trampulator, 0));*/
 
    configureBindings();


// -----------------------------------------Default Commands-------------------------------------------   
    m_drive.setDefaultCommand(
          DriveCommands.joystickDrive(
              m_drive,
              () -> -driverXbox.getLeftY(),
              () -> -driverXbox.getLeftX(),
              () -> -driverXbox.getRightX(),
              () -> driverXbox.getLeftTriggerAxis()));

              
    m_feeder.setDefaultCommand(new FeederJoystick(m_feeder, operatorXbox)); //feeder control on the operator controller
    //m_trampulator.setDefaultCommand(new TrampulatorManipulatorOrient(m_trampulator, operatorXbox).alongWith(new TrampulatorWristTriggerControl(m_trampulator, operatorXbox)));//manipulator controll on the operator controller
  
  }

  private void configureBindings(){


    
    
    new JoystickButton(driverXbox, XboxController. Button.kLeftBumper.value).onTrue(new SmartIntake(m_intake,m_feeder, driverXbox));
    //new JoystickButton(driverXbox, XboxController.Button.kRightBumper.value).onTrue(new LaunchWithDelay(m_drivetrain,m_launcher,m_feeder, m_elevator));
    new JoystickButton(driverXbox, XboxController.Button.kRightBumper.value).onTrue(new FeederGo(m_feeder, -1).andThen(new WaitCommand(1)).andThen(new LauncherStop(m_launcher)));
    new JoystickButton(driverXbox, XboxController.Button.kY.value).onTrue(new LauncherStop(m_launcher));

    //real operator
    //left joy - feeder`Q
    //right joy - trampulator spin
    //triggers - trampulator wrist
    //a - subwoofer angle
    //x - podium angle
    //b - amp angle
    //y - amp score
    //start - climber up
    //back - climber down
    //new JoystickButton(operatorXbox, XboxController.Button.kLeftBumper.value).onTrue(new LauncherStop(m_launcher));
    //new JoystickButton(operatorXbox, XboxController.Button.kRightBumper.value).onTrue(new IntakeWristOut(m_intake));
    new JoystickButton(operatorXbox, XboxController.Button.kA.value).onTrue(new LauncherAimWithWarmup(m_launcher, Constants.LauncherConstants.launcherAngleSubwoofer));
    new JoystickButton(operatorXbox, XboxController.Button.kX.value).onTrue(new LauncherAimWithWarmup(m_launcher, Constants.LauncherConstants.launcherAnglePodium));
    new JoystickButton(operatorXbox, XboxController.Button.kB.value).onTrue(new LauncherAimWithWarmup(m_launcher, Constants.LauncherConstants.launcherAngleAmpSafetyZone));
    new JoystickButton(operatorXbox, XboxController.Button.kLeftBumper.value).onTrue(new LauncherAim(m_launcher, 0));
    new JoystickButton(operatorXbox, XboxController.Button.kY.value).onTrue(new LauncherAmpScore(m_launcher, m_feeder, m_elevator));
    //disabled for now until we get it working new JoystickButton(operatorXbox, XboxController.Button.kY.value).onTrue(new SmartAmpScore(m_trampulator, m_elevator, m_feeder, m_launcher));
    new JoystickButton(operatorXbox, XboxController.Button.kStart.value).onTrue(new ClimberGoToPosition(m_climber, -100));
    new JoystickButton(operatorXbox, XboxController.Button.kBack.value).onTrue(new ClimberGoToPosition(m_climber, 0));
    
  }
 
  public Command getAutonomousCommand()
  {
    return m_chooser.getSelected();
  }


}
