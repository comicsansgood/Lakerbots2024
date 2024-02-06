// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

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
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.FeederCommands.FeederGo;
import frc.robot.commands.FeederCommands.FeederStop;
import frc.robot.commands.IntakeCommands.IntakeHome;
import frc.robot.commands.IntakeCommands.IntakeIn;
import frc.robot.commands.IntakeCommands.IntakeOut;
import frc.robot.commands.IntakeCommands.IntakeSpin;
import frc.robot.commands.LauncherCommands.LauncherGo;
import frc.robot.commands.LauncherCommands.LauncherStop;
import frc.robot.commands.TrampulatorCommands.TrampulatorManipulatorCommands.TrampulatorManipulatorOrient;
import frc.robot.commands.swervedrive.ZeroGyro;
import frc.robot.commands.swervedrive.drivebase.AbsoluteDrive;
import frc.robot.commands.swervedrive.drivebase.AbsoluteFieldDrive;
import frc.robot.commands.swervedrive.drivebase.DriveToTarget;
import frc.robot.commands.swervedrive.drivebase.AbsoluteDriveAdv;
import frc.robot.commands.swervedrive.drivebase.TeleopDrive;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.TrampulatorSubsystem;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import java.io.File;
import frc.robot.subsystems.FeederSubsystem;
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a "declarative" paradigm, very
 * little robot logic should actually be handled in the {@link Robot} periodic methods (other than the scheduler calls).
 * Instead, the structure of the robot (including subsystems, commands, and trigger mappings) should be declared here.
 */
import frc.robot.subsystems.IntakeSubsystem;
public class RobotContainer
{

  // The robot's subsystems and commands are defined here...
  private final SwerveSubsystem drivebase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(),
                                                                         "swerve/falcon"));
  //private final TrampulatorSubsystem m_trampulator = new TrampulatorSubsystem();
  //private final LauncherSubsystem m_launcher = new LauncherSubsystem();
  //private final FeederSubsystem m_feeder = new FeederSubsystem();  
  private final TrampulatorSubsystem m_trampulator = new TrampulatorSubsystem();
  private final IntakeSubsystem m_intake = new IntakeSubsystem();
  // CommandJoystick rotationController = new CommandJoystick(1);
  // Replace with CommandPS4Controller or CommandJoystick if needed
  CommandJoystick driverController = new CommandJoystick(1);

  // CommandJoystick driverController   = new CommandJoystick(3);//(OperatorConstants.DRIVER_CONTROLLER_PORT);
  XboxController driverXbox = new XboxController(0);
  XboxController tempController = new XboxController(1);

  SendableChooser<Command> m_chooser = new SendableChooser<>();


  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer()
  {

    m_chooser.addOption("redReal", drivebase.getAutonomousCommand("redreal", true));
    m_chooser.addOption("tramp_auto_test", drivebase.getAutoNew("tramp_auto_test"));
    SmartDashboard.putData("autochooser", m_chooser);
    
    // Configure the trigger bindings
  SmartDashboard.putData("drive to 0,0,0", new DriveToTarget(drivebase, new Pose2d(new Translation2d(0,0), new Rotation2d(0)), 0.5, 1));
  SmartDashboard.putData("drive to 1,1,0", new DriveToTarget(drivebase, new Pose2d(new Translation2d(1,1), new Rotation2d(0)), 0.5, 1));
  SmartDashboard.putData("intake spin", new IntakeSpin(m_intake, 0.6));
    SmartDashboard.putData("intake stop", new IntakeSpin(m_intake, 0));
  SmartDashboard.putData("intake out", new IntakeOut(m_intake));
  SmartDashboard.putData("intake home", new IntakeHome(m_intake));


  //SmartDashboard.putData("trampspin", new TrampulatorManipulatorSpin(m_trampulator, 0.25));
  //SmartDashboard.putData("trampstop", new TrampulatorManipulatorSpin(m_trampulator, 0));
  //SmartDashboard.putData("trampreverse", new TrampulatorManipulatorSpin(m_trampulator, -0.25));


    configureBindings();

    AbsoluteDrive closedAbsoluteDrive = new AbsoluteDrive(drivebase,
                                                          // Applies deadbands and inverts controls because joysticks
                                                          // are back-right positive while robot
                                                          // controls are front-left positive
                                                          () -> MathUtil.applyDeadband(driverXbox.getLeftY(),
                                                                                       OperatorConstants.LEFT_Y_DEADBAND),
                                                          () -> MathUtil.applyDeadband(driverXbox.getLeftX(),
                                                                                       OperatorConstants.LEFT_X_DEADBAND),
                                                          () -> -driverXbox.getRightX(),
                                                          () -> -driverXbox.getRightY());

    AbsoluteFieldDrive closedFieldAbsoluteDrive = new AbsoluteFieldDrive(drivebase,
                                                                         () ->
                                                                             MathUtil.applyDeadband(driverXbox.getLeftY(),
                                                                                                    OperatorConstants.LEFT_Y_DEADBAND),
                                                                         () -> MathUtil.applyDeadband(driverXbox.getLeftX(),
                                                                                                      OperatorConstants.LEFT_X_DEADBAND),
                                                                         () -> driverXbox.getRawAxis(5));//2

    AbsoluteDriveAdv closedAbsoluteDriveAdv = new AbsoluteDriveAdv(drivebase,
                                                                      () -> MathUtil.applyDeadband(driverXbox.getLeftY(),
                                                                                                OperatorConstants.LEFT_Y_DEADBAND),
                                                                      () -> MathUtil.applyDeadband(driverXbox.getLeftX(),
                                                                                                  OperatorConstants.LEFT_X_DEADBAND),
                                                                      () -> MathUtil.applyDeadband(driverXbox.getRightX(),
                                                                                                  OperatorConstants.RIGHT_X_DEADBAND), 
                                                                      tempController::getYButtonPressed, 
                                                                      tempController::getAButtonPressed, 
                                                                      tempController::getXButtonPressed, 
                                                                      tempController::getBButtonPressed);

    TeleopDrive simClosedFieldRel = new TeleopDrive(drivebase,
                                                    () -> MathUtil.applyDeadband(driverXbox.getLeftY(),
                                                                                 OperatorConstants.LEFT_Y_DEADBAND),
                                                    () -> MathUtil.applyDeadband(driverXbox.getLeftX(),
                                                                                 OperatorConstants.LEFT_X_DEADBAND),
                                                    () -> driverXbox.getRawAxis(2), () -> true);
    TeleopDrive closedFieldRel = new TeleopDrive(
        drivebase,
        () -> MathUtil.applyDeadband(driverController.getRawAxis(1), OperatorConstants.LEFT_Y_DEADBAND),
        () -> MathUtil.applyDeadband(driverController.getRawAxis(0), OperatorConstants.LEFT_X_DEADBAND),
        () -> -driverController.getRawAxis(2), () -> true);

    //drivebase.setDefaultCommand(!RobotBase.isSimulation() ? closedAbsoluteDrive : closedFieldAbsoluteDrive);

    //We use this one
    //drivebase.setDefaultCommand(closedAbsoluteDriveAdv);

    m_trampulator.setDefaultCommand(new TrampulatorManipulatorOrient(m_trampulator, driverController.getRawAxis(1), 0.25, driverXbox));

    //NamedCommands.registerCommand("trampSpin", new TrampulatorManipulatorSpin(m_trampulator, 0.25));
    //NamedCommands.registerCommand("trampStop", new TrampulatorManipulatorSpin(m_trampulator, 0));

    

  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary predicate, or via the
   * named factories in {@link edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller PS4}
   * controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight joysticks}.
   */
  private void configureBindings()
  {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`

    //new JoystickButton(driverXbox, 1).onTrue((new InstantCommand(drivebase::zeroGyro)));
    //new JoystickButton(driverXbox, 3).onTrue(new InstantCommand(drivebase::addFakeVisionReading));
    
    
    
    /*new JoystickButton(driverXbox, XboxController.Button.kA.value).onTrue(new LauncherGo(m_launcher));
    new JoystickButton(driverXbox, XboxController.Button.kB.value).onTrue(new LauncherStop(m_launcher));
    new JoystickButton(driverXbox, XboxController.Button.kX.value).onTrue(new FeederGo(m_feeder, .2));
    new JoystickButton(driverXbox, XboxController.Button.kLeftBumper.value).onTrue(new FeederGo(m_feeder, -.1));
    new JoystickButton(driverXbox, XboxController.Button.kY.value).onTrue(new FeederStop(m_feeder));
    new JoystickButton(driverXbox, XboxController.Button.kStart.value).onTrue(new ZeroGyro(drivebase));
*/



    new JoystickButton(driverXbox, XboxController.Button.kA.value).onTrue(new IntakeSpin(m_intake, 0.1));
    new JoystickButton(driverXbox, XboxController.Button.kB.value).onTrue(new IntakeSpin(m_intake, 0));
    new JoystickButton(driverXbox, XboxController.Button.kX.value).onTrue(new IntakeIn(m_intake));
    new JoystickButton(driverXbox, XboxController.Button.kY.value).onTrue(new IntakeOut(m_intake));
    

//    new JoystickButton(driverXbox, 3).whileTrue(new RepeatCommand(new InstantCommand(drivebase::lock, drivebase)));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand()
  {
    // An example command will be run in autonomous
    //return drivebase.getAutonomousCommand("backandforth", true);

    return m_chooser.getSelected();
    
  }

  public void setDriveMode()
  {
    //drivebase.setDefaultCommand();
  }

  public void setMotorBrake(boolean brake)
  {
    drivebase.setMotorBrake(brake);
  }
}
