package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
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
import frc.robot.commands.IntakeCommands.SecondIntakeAndLaunch;
import frc.robot.commands.LauncherCommands.LauncherAim;
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
import frc.robot.commands.swervedrive.GyroBack;
import frc.robot.commands.swervedrive.drivebase.DriveToTarget;
import frc.robot.commands.swervedrive.drivebase.AbsoluteDriveAdv;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.TrampulatorSubsystem;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
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
  private final SwerveSubsystem m_drivetrain = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(),
                                                                         "swerve/falcon"));
  private final LauncherSubsystem m_launcher = new LauncherSubsystem();
  public final ElevatorSubsystem m_elevator = new ElevatorSubsystem();
  private final FeederSubsystem m_feeder = new FeederSubsystem();
  private final TrampulatorSubsystem m_trampulator = new TrampulatorSubsystem();
  private final IntakeSubsystem m_intake = new IntakeSubsystem();
  private final ClimberSubsystem m_climber = new ClimberSubsystem();
  
  XboxController driverXbox = new XboxController(0);
  XboxController operatorXbox = new XboxController(1);
  //TODO: janky fix
  XboxController tempController = new XboxController(3);

  SendableChooser<Command> m_chooser = new SendableChooser<>();



  public RobotContainer()
  {



    //------------------------------------Commands for Pathing-------------------------------------------
    NamedCommands.registerCommand("autoShootCenterSubwoofer", new VariableLaunch(m_drivetrain, m_launcher, m_feeder, m_elevator, m_intake, Constants.LauncherConstants.launcherAngleSubwoofer));
    NamedCommands.registerCommand("autoShootMiddlePiece", new VariableLaunch(m_drivetrain, m_launcher, m_feeder, m_elevator, m_intake, Constants.LauncherConstants.launcherAngleMiddlePiece));
    NamedCommands.registerCommand("AimFromMiddlePiece", new LauncherAim(m_launcher, Constants.LauncherConstants.launcherAngleMiddlePiece));
    NamedCommands.registerCommand("AimFromAmpSide", new LauncherAim(m_launcher, Constants.LauncherConstants.launcherAngleAmpSide));
    NamedCommands.registerCommand("collect", new SendItIntakeAuto(m_intake, m_feeder, driverXbox));
    NamedCommands.registerCommand("2ndcollect", new SecondIntakeAndLaunch(m_intake, m_feeder, m_launcher, driverXbox));
    NamedCommands.registerCommand("spinIntake", new IntakeSpin(m_intake, 0.75));
    NamedCommands.registerCommand("TurnEverythingOn", new TurnEverythingOn(m_intake, m_feeder, m_launcher));
        NamedCommands.registerCommand("TurnEverythingOnForever", new TurnEverythingOnForever(m_intake, m_feeder, m_launcher));
    NamedCommands.registerCommand("feederGo", new FeederGoForever(m_feeder, -1));
    //NamedCommands.registerCommand("autoShootCenterSubwoofer", new LaunchAtSubwoofer(m_drivetrain, m_launcher, m_feeder, m_elevator));
    //NamedCommands.registerCommand("autoShootMiddlePiece", new LaunchAtMiddlePiece(m_drivetrain, m_launcher, m_feeder, m_elevator));

    //-----------------------------------------Auto options for chooser------------------------------------
    m_chooser.addOption("centerPieceBLUE", m_drivetrain.getAutoNew("centerPieceBLUE"));
    m_chooser.addOption("3pieceautoAmpSideRed", m_drivetrain.getAutoNew("3pieceautoAmpSideRed"));
    m_chooser.addOption("3pieceautoAmpSideBLUE", m_drivetrain.getAutoNew("3pieceautoAmpSideBLUE"));
    m_chooser.addOption("2 piece", m_drivetrain.getAutonomousCommand("2 piece", true));
    m_chooser.addOption("onemmmmm",m_drivetrain.getAutonomousCommand( "1 Meter towards center Red", true));
    m_chooser.addOption("2pieceauto", m_drivetrain.getAutoNew("2pieceauto"));
    m_chooser.addOption("2pieceautoBLUE", m_drivetrain.getAutoNew("2pieceautoBLUE"));
    m_chooser.addOption("shootDaPiece", new LauncherAim(m_launcher, 47.5).andThen(new LaunchWithDelay(m_drivetrain, m_launcher, m_feeder, m_elevator)));
    SmartDashboard.putData("autochooser", m_chooser);
    
  //-----------------------------------------Smart Dashboard Buttons-----------------------------------------------------
  SmartDashboard.putData("drive to 0,0,0", new DriveToTarget(m_drivetrain, new Pose2d(new Translation2d(0,0), new Rotation2d(0)), 0.5, 1));
  SmartDashboard.putData("drive to 1,1,0", new DriveToTarget(m_drivetrain, new Pose2d(new Translation2d(1,1), new Rotation2d(0)), 0.5, 1));
  SmartDashboard.putData("180 gyro", new GyroBack(m_drivetrain));
  SmartDashboard.putData("intake reverse", new IntakeSpin(m_intake, -1));
  SmartDashboard.putData("intake stop", new IntakeSpin(m_intake, 0));
  SmartDashboard.putData("climber down", new ClimberSpin(m_climber, 0.25));
  SmartDashboard.putData("climber up", new ClimberSpin(m_climber, -0.25));
  SmartDashboard.putData("climber stop", new ClimberSpin(m_climber, 0));
  SmartDashboard.putData("trampwrist out", new TrampulatorWristSpin(m_trampulator, 0.25));
  SmartDashboard.putData("trampwrist in ", new TrampulatorWristSpin(m_trampulator, -0.25));
  SmartDashboard.putData("trampwrist stop", new TrampulatorWristSpin(m_trampulator, 0));
 
    configureBindings();
/* 

---------------------------------------------------Drive Modes----------------------------------------------------
    AbsoluteDrive closedAbsoluteDrive = new AbsoluteDrive(m_drivetrain,
                                                          // Applies deadbands and inverts controls because joysticks
                                                          // are back-right positive while robot
                                                            // controls are front-left positive
                                                          () -> MathUtil.applyDeadband(driverXbox.getLeftY(),
                                                                                       OperatorConstants.LEFT_Y_DEADBAND),
                                                          () -> MathUtil.applyDeadband(driverXbox.getLeftX(),
                                                                                       OperatorConstants.LEFT_X_DEADBAND),
                                                          () -> -driverXbox.getRightX(),
                                                          () -> -driverXbox.getRightY());

    AbsoluteFieldDrive closedFieldAbsoluteDrive = new AbsoluteFieldDrive(m_drivetrain,
                                                                         () ->
                                                                             MathUtil.applyDeadband(driverXbox.getLeftY(),
                                                                                                    OperatorConstants.LEFT_Y_DEADBAND),
                                                                         () -> MathUtil.applyDeadband(driverXbox.getLeftX(),
                                                                                                      OperatorConstants.LEFT_X_DEADBAND),
                                                                         () -> driverXbox.getRawAxis(5));//2
                                                                         */

    AbsoluteDriveAdv closedAbsoluteDriveAdv = new AbsoluteDriveAdv(m_drivetrain,
                                                                      () -> MathUtil.applyDeadband(driverXbox.getRawAxis(1),
                                                                                                OperatorConstants.LEFT_Y_DEADBAND),
                                                                      () -> MathUtil.applyDeadband(driverXbox.getLeftX(),
                                                                                                  OperatorConstants.LEFT_X_DEADBAND),
                                                                      () -> MathUtil.applyDeadband(driverXbox.getRightX(),
                                                                                                  OperatorConstants.RIGHT_X_DEADBAND), 
                                                                      tempController::getYButtonPressed, 
                                                                      tempController::getAButtonPressed, 
                                                                      tempController::getXButtonPressed, 
                                                                      tempController::getBButtonPressed);
    
 AbsoluteDriveAdv closedAbsoluteDriveAdvtele = new AbsoluteDriveAdv(m_drivetrain,
                                                                      () -> MathUtil.applyDeadband(-driverXbox.getRawAxis(1),
                                                                                                OperatorConstants.LEFT_Y_DEADBAND),
                                                                      () -> MathUtil.applyDeadband(-driverXbox.getLeftX(),
                                                                                                  OperatorConstants.LEFT_X_DEADBAND),
                                                                      () -> MathUtil.applyDeadband(-driverXbox.getRightX(),
                                                                                                  OperatorConstants.RIGHT_X_DEADBAND), 
                                                                      tempController::getYButtonPressed, 
                                                                      tempController::getAButtonPressed, 
                                                                      tempController::getXButtonPressed, 
                                                                      tempController::getBButtonPressed);
/* 
    TeleopDrive simClosedFieldRel = new TeleopDrive(m_drivetrain,
                                                    () -> MathUtil.applyDeadband(driverXbox.getLeftY(),
                                                                                 OperatorConstants.LEFT_Y_DEADBAND),
                                                    () -> MathUtil.applyDeadband(driverXbox.getLeftX(),
                                                                                 OperatorConstants.LEFT_X_DEADBAND),
                                                    () -> driverXbox.getRawAxis(2), () -> true);
    TeleopDrive closedFieldRel = new TeleopDrive(
        m_drivetrain,
        () -> MathUtil.applyDeadband(driverController.getRawAxis(1), OperatorConstants.LEFT_Y_DEADBAND),
        () -> MathUtil.applyDeadband(driverController.getRawAxis(0), OperatorConstants.LEFT_X_DEADBAND),
        () -> -driverController.getRawAxis(2), () -> true);

    //custom
    TeleopDrive DriveTest = new TeleopDrive(
        m_drivetrain,
        () -> MathUtil.applyDeadband(driverXbox.getRawAxis(1), OperatorConstants.LEFT_Y_DEADBAND),
        () -> MathUtil.applyDeadband(driverXbox.getRawAxis(0), OperatorConstants.LEFT_X_DEADBAND),
        () -> -driverXbox.getRawAxis(4), () -> true);
*/
    //m_drivetrain.setDefaultCommand(!RobotBase.isSimulation() ? closedAbsoluteDrive : closedFieldAbsoluteDrive);




// -----------------------------------------Default Commands-------------------------------------------   
    m_drivetrain.setDefaultCommand(closedAbsoluteDriveAdv);
    m_feeder.setDefaultCommand(new FeederJoystick(m_feeder, operatorXbox)); //feeder control on the operator controller
    //m_trampulator.setDefaultCommand(new TrampulatorManipulatorOrient(m_trampulator, operatorXbox).alongWith(new TrampulatorWristTriggerControl(m_trampulator, operatorXbox)));//manipulator controll on the operator controller
  
  }

  private void configureBindings(){


    
    //new JoystickButton(driverXbox, XboxController.Button.kA.value).onTrue(new IntakeSpin(m_intake, 0.8));
    //new JoystickButton(driverXbox, XboxController.Button.kB.value).onTrue(new IntakeSpin(m_intake, 0));
    /*new JoystickButton(driverXbox, XboxController.Button.kA.value).onTrue(new IntakeSetVelocity(m_intake, 3000));
    new JoystickButton(driverXbox, XboxController.Button.kB.value).onTrue(new IntakeSetVelocity(m_intake, 0));
    new JoystickButton(driverXbox, XboxController.Button.kX.value).onTrue(new IntakeWristIn(m_intake));
    new JoystickButton(driverXbox, XboxController.Button.kY.value).onTrue(new IntakeWristOut(m_intake));
    */
    new JoystickButton(driverXbox, XboxController. Button.kLeftBumper.value).onTrue(new SmartIntake(m_intake,m_feeder, driverXbox));
    //new JoystickButton(driverXbox, XboxController. Button.kLeftBumper.value).onTrue(new IntakeCollect(m_intake,m_feeder));
    //new JoystickButton(driverXbox, XboxController. Button.kRightBumper.value).onTrue(new IntakeStopCollect(m_intake,m_feeder));
    //new JoystickButton(driverXbox, XboxController.Button.kA.value).onTrue(new LauncherAim(m_launcher, 47.5));
   // new JoystickButton(driverXbox, XboxController.Button.kB.value).onTrue(new LauncherAim(m_launcher, 0));
   new JoystickButton(driverXbox, XboxController.Button.kRightBumper.value).onTrue(new LaunchWithDelay(m_drivetrain,m_launcher,m_feeder, m_elevator));
   new JoystickButton(driverXbox, XboxController.Button.kY.value).onTrue(new LauncherStop(m_launcher));
    //new JoystickButton(driverXbox, XboxController.Button.kStart.value).onTrue(new ClimberGoToPosition(m_climber, -72)); 
   //new JoystickButton(driverXbox, XboxController.Button.kX.value).onTrue(new ClimberGoToPosition(m_climber, -100));
    //new JoystickButton(driverXbox, XboxController.Button.kY.value).onTrue(new ClimberGoToPosition(m_climber, 0));
    //new JoystickButton(driverXbox, XboxController.Button.kA.value).onTrue(new ClimberGoToPosition(m_climber, 10));
   
   // new JoystickButton(driverXbox, XboxController.Button.kA.value).onTrue(new ElevatorGoToPosition(m_elevator, -100));
    //new JoystickButton(driverXbox, XboxController.Button.kB.value).onTrue(new ElevatorGoToPosition(m_elevator, -50));
    //new JoystickButton(driverXbox, XboxController.Button.kY.value).onTrue(new ElevatorGoToPosition(m_elevator, 0));






    //new JoystickButton(operatorXbox, XboxController.Button.kY.value).onTrue(new ElevatorGoToPosition(m_elevator, -20));
    //new JoystickButton(operatorXbox, XboxController.Button.kX.value).onTrue(new ElevatorGoToPosition(m_elevator, -120));

    //new JoystickButton(operatorXbox, XboxController.Button.kB.value).onTrue(new TrampulatorWristGoToPosition(m_trampulator,40));
    //new JoystickButton(operatorXbox, XboxController.Button.kA.value).onTrue(new IntakeWristSpin(m_intake, -0.3));
   // new JoystickButton(operatorXbox, XboxController.Button.kRightBumper.value).onTrue(new FeederGo(m_feeder, 0));
    //new JoystickButton(operatorXbox, XboxController.Button.kLeftBumper.value).onTrue(new FeederGo(m_feeder, -0.2));


    //current testing 3/2
    /* 
    new JoystickButton(operatorXbox, XboxController.Button.kX.value).onTrue(new TrampulatorWristGoToPosition(m_trampulator, 36));
    new JoystickButton(operatorXbox, XboxController.Button.kY.value).onTrue(new ClimberGoToPosition(m_climber, 0));
    new JoystickButton(operatorXbox, XboxController.Button.kStart.value).onTrue(new ClimberGoToPosition(m_climber, -100));
    new JoystickButton(operatorXbox, XboxController.Button.kA.value).onTrue(new SmartTrapScore(m_trampulator, m_elevator, m_feeder, m_launcher, m_climber));
    new JoystickButton(operatorXbox, XboxController.Button.kB.value).onTrue(new SmartAmpScore(m_trampulator, m_elevator, m_feeder, m_launcher));
    new JoystickButton(operatorXbox, XboxController.Button.kLeftBumper.value).onTrue(new TrampulatorHome(m_trampulator, m_elevator, m_launcher));
    new JoystickButton(operatorXbox, XboxController.Button.kRightBumper.value).onTrue(new TrampulatorSpit(m_trampulator));
    new JoystickButton(operatorXbox, XboxController.Button.kBack.value).onTrue(new ElevatorGoToPosition(m_elevator, -135).alongWith(new TrampulatorWristGoToPosition(m_trampulator, 36.5)));
*/

    //real operator
    //left joy - feeder
    //right joy - trampulator spin
    //triggers - trampulator wrist
    //a - subwoofer angle
    //x - podium angle
    //b - amp angle
    //y - amp score
    //start - climber up
    //back - climber down
    new JoystickButton(operatorXbox, XboxController.Button.kA.value).onTrue(new LauncherAim(m_launcher, Constants.LauncherConstants.launcherAngleSubwoofer));
    new JoystickButton(operatorXbox, XboxController.Button.kX.value).onTrue(new LauncherAim(m_launcher, Constants.LauncherConstants.launcherAnglePodium));
    new JoystickButton(operatorXbox, XboxController.Button.kB.value).onTrue(new LauncherAim(m_launcher, Constants.LauncherConstants.launcherAngleAmpSafetyZone));
    //new JoystickButton(operatorXbox, XboxController.Button.kY.value).onTrue(new SmartAmpScore(m_trampulator, m_elevator, m_feeder, m_launcher));
    new JoystickButton(operatorXbox, XboxController.Button.kStart.value).onTrue(new ClimberGoToPosition(m_climber, -100));
    new JoystickButton(operatorXbox, XboxController.Button.kBack.value).onTrue(new ClimberGoToPosition(m_climber, 0));
    
  }

  public Command getAutonomousCommand()
  {
    return m_chooser.getSelected();
  }

  public void setDriveMode()
  {
    //m_drivetrain.setDefaultCommand();
    
  }

  public void setMotorBrake(boolean brake)
  {
    m_drivetrain.setMotorBrake(brake);
  }
}
