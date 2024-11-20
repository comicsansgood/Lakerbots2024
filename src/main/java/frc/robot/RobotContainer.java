package frc.robot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.DriveCommands;
import frc.robot.commands.AmpulatorCommands.AmpulatorGo;
import frc.robot.commands.AmpulatorCommands.AmpulatorIn;
import frc.robot.commands.AmpulatorCommands.AmpulatorOut;
import frc.robot.commands.AmpulatorCommands.SmartAmpHome;
import frc.robot.commands.AmpulatorCommands.SmartAmpScoreFast;
import frc.robot.commands.ClimberCommands.ClimberGoToPosition;
import frc.robot.commands.ClimberCommands.ClimberSpin;
import frc.robot.commands.FeederCommands.FeederCenter;
import frc.robot.commands.FeederCommands.FeederGo;
import frc.robot.commands.FeederCommands.FeederJoystick;
import frc.robot.commands.FeederCommands.SpinFeederUntilNote;
import frc.robot.commands.IntakeCommands.SmartIntake;
import frc.robot.commands.IntakeCommands.IntakeSpin;
import frc.robot.commands.IntakeCommands.IntakeWristHome;
import frc.robot.commands.IntakeCommands.IntakeWristIn;
import frc.robot.commands.IntakeCommands.IntakeWristOut;
import frc.robot.commands.LauncherCommands.LauncherAim;
import frc.robot.commands.LauncherCommands.LauncherAimNEChamps;
import frc.robot.commands.LauncherCommands.LauncherAimWithWarmupAndFeederReverse;
import frc.robot.commands.LauncherCommands.LauncherStop;
import frc.robot.commands.LedsCommands.Black;
import frc.robot.commands.LedsCommands.FlashGreen;
import frc.robot.commands.LedsCommands.Green;
import frc.robot.commands.LedsCommands.RedAndGreen;
import frc.robot.commands.LedsCommands.SpiritColors;
import frc.robot.commands.LedsCommands.UpdateLeds;
import frc.robot.commands.LauncherCommands.AutoLaunch;
import frc.robot.commands.LauncherCommands.AutoLaunchWithWarmup;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.LedsSubsystem;
import frc.robot.subsystems.drive.Drive;
import frc.robot.subsystems.drive.GyroIOPigeon2;
import frc.robot.subsystems.drive.ModuleIOTalonFX;
import org.littletonrobotics.junction.networktables.LoggedDashboardChooser;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import frc.robot.subsystems.AmpulatorSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.commands.IntakeCommands.SmartIntakeAuto;
import frc.robot.commands.IntakeCommands.SmartIntakeAutoLong;
import frc.robot.commands.IntakeCommands.WaitForNoteOrButtonPress;

public class RobotContainer
{
  //Subsystem Object Declaration
  private final LauncherSubsystem m_launcher = new LauncherSubsystem();
  public final ElevatorSubsystem m_elevator = new ElevatorSubsystem();
  private final FeederSubsystem m_feeder = new FeederSubsystem();
  public final AmpulatorSubsystem m_ampulator = new AmpulatorSubsystem();
  private final IntakeSubsystem m_intake = new IntakeSubsystem();
  private final ClimberSubsystem m_climber = new ClimberSubsystem();
  private final LedsSubsystem m_leds = new LedsSubsystem();

  public static final LimelightSubsystem m_limelight = new LimelightSubsystem();

//CHANGED VISABLITY and STATIC-NESS /\  \/
  public static final Drive m_drive = new Drive(new GyroIOPigeon2(),
            new ModuleIOTalonFX(0),
            new ModuleIOTalonFX(1),
            new ModuleIOTalonFX(2),
            new ModuleIOTalonFX(3));
  
  //controller 
  XboxController driverXbox = new XboxController(0);
  XboxController operatorXbox = new XboxController(1);

private final LoggedDashboardChooser<Command> m_chooser;

  public RobotContainer()
  {
    NamedCommands.registerCommand("spinFeederUntilNote", new SpinFeederUntilNote(m_intake, m_feeder));
    NamedCommands.registerCommand("feederCenter", new FeederCenter(m_feeder));
    NamedCommands.registerCommand("intake", new SmartIntakeAuto(m_intake, m_feeder));
    NamedCommands.registerCommand("intakeLong", new SmartIntakeAutoLong(m_intake, m_feeder));
    NamedCommands.registerCommand("aimCenterPiece", new LauncherAim(m_launcher, Constants.LauncherConstants.launcherAngleAUTOCenterPiece));
    NamedCommands.registerCommand("aimTopPiece", new LauncherAim(m_launcher, Constants.LauncherConstants.launcherAngleAUTOTopPiece));
    NamedCommands.registerCommand("aimHome", new LauncherAim(m_launcher, 0));
    NamedCommands.registerCommand("aimSubwoofer", new LauncherAim(m_launcher, Constants.LauncherConstants.launcherAngleAUTOSubwoofer));
    NamedCommands.registerCommand("aimArbPoint", new LauncherAim(m_launcher, Constants.LauncherConstants.launcherAngleAUTOArbPoint));
    NamedCommands.registerCommand("aimSecondArbPoint", new LauncherAim(m_launcher, Constants.LauncherConstants.launcherAngleAUTOSecondArbPoint));
    NamedCommands.registerCommand("shootPodium", new AutoLaunch(m_launcher, m_feeder, Constants.LauncherConstants.launcherAngleAUTOPodium));
    NamedCommands.registerCommand("shootArbPoint", new AutoLaunch(m_launcher, m_feeder, Constants.LauncherConstants.launcherAngleAUTOArbPoint));
    NamedCommands.registerCommand("shootPodiumArbPoint", new AutoLaunch(m_launcher, m_feeder, Constants.LauncherConstants.launcherAngleAUTOPodiumArbPoint));
    NamedCommands.registerCommand("shootArbPointWithWarmup", new AutoLaunchWithWarmup(m_launcher, m_feeder, Constants.LauncherConstants.launcherAngleAUTOArbPoint));
    NamedCommands.registerCommand("shootSecondArbPoint", new AutoLaunch(m_launcher, m_feeder, Constants.LauncherConstants.launcherAngleAUTOSecondArbPoint));
    NamedCommands.registerCommand("shootTopPiece", new AutoLaunch(m_launcher, m_feeder, Constants.LauncherConstants.launcherAngleAUTOTopPiece));
    NamedCommands.registerCommand("shootSubwoofer", new AutoLaunch(m_launcher, m_feeder, Constants.LauncherConstants.launcherAngleAUTOSubwoofer));
    NamedCommands.registerCommand("shootCenterPiece", new AutoLaunch(m_launcher, m_feeder, Constants.LauncherConstants.launcherAngleAUTOCenterPiece));

    m_chooser = new LoggedDashboardChooser<>("auto choices", AutoBuilder.buildAutoChooser());



    
  //-----------------------------------------Smart Dashboard Buttons-----------------------------------------------------
  SmartDashboard.putData("intake reverse", new IntakeSpin(m_intake, -1));
  SmartDashboard.putData("intake spin 100", new IntakeSpin(m_intake, 1)); 
  SmartDashboard.putData("intake stop", new IntakeSpin(m_intake, 0));
  SmartDashboard.putData("climber down", new ClimberSpin(m_climber, 0.25));
  SmartDashboard.putData("climber up", new ClimberSpin(m_climber, -0.25));
  SmartDashboard.putData("climber stop", new ClimberSpin(m_climber, 0));
  SmartDashboard.putData("intake wrist out", new IntakeWristOut(m_intake));
  SmartDashboard.putData("intake wrist in", new IntakeWristIn(m_intake));
  SmartDashboard.putData("ampulator In", new AmpulatorIn(m_ampulator));
  SmartDashboard.putData("ampulator Out", new AmpulatorOut(m_ampulator));
  SmartDashboard.putData("ampulator Go", new AmpulatorGo(m_ampulator, 0.5));
  SmartDashboard.putData("ampulator Backwards", new AmpulatorGo(m_ampulator, -0.5));
  SmartDashboard.putData("ampulator Stop", new AmpulatorGo(m_ampulator, 0));
  SmartDashboard.putData("green", new Green(m_leds));
  SmartDashboard.putData("black", new Black(m_leds));
  SmartDashboard.putData("red and green", new RedAndGreen(m_leds));
  SmartDashboard.putData("sprit colors", new SpiritColors(m_leds));
  SmartDashboard.putData("Flash green", new FlashGreen(m_leds));
  SmartDashboard.putData("wait for note", new WaitForNoteOrButtonPress(m_intake, driverXbox));
  SmartDashboard.putData("center note", new FeederCenter(m_feeder));
  SmartDashboard.putData("launch mid", new LauncherAimWithWarmupAndFeederReverse(m_launcher, m_feeder, Constants.LauncherConstants.launcherAnglePodium));


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
    m_leds.setDefaultCommand(new UpdateLeds(m_leds, m_intake, m_feeder));
  
  }

  private void configureBindings(){



    
    
    new JoystickButton(driverXbox, XboxController.Button.kLeftBumper.value).onTrue(new SmartIntake(m_intake, m_feeder, driverXbox));
    new JoystickButton(driverXbox, XboxController.Button.kStart.value).onTrue(DriveCommands.zeroGyro(m_drive));
    new JoystickButton(driverXbox, XboxController.Button.kRightBumper.value).onTrue(new FeederGo(m_feeder, -1).andThen(new WaitCommand(1)).andThen(new LauncherStop(m_launcher)).andThen(new IntakeWristHome(m_intake)));
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

    new JoystickButton(operatorXbox, XboxController.Button.kA.value).onTrue(new LauncherAimNEChamps(m_launcher, m_feeder, m_intake, Constants.LauncherConstants.launcherAngleSubwoofer));
    new JoystickButton(operatorXbox, XboxController.Button.kX.value).onTrue(new LauncherAimWithWarmupAndFeederReverse(m_launcher, m_feeder, Constants.LauncherConstants.launcherAnglePodium));
    new JoystickButton(operatorXbox, XboxController.Button.kB.value).onTrue(new LauncherAimWithWarmupAndFeederReverse(m_launcher, m_feeder, Constants.LauncherConstants.launcherAngleWingIt));
    new JoystickButton(operatorXbox, XboxController.Button.kLeftBumper.value).onTrue(new SmartAmpHome(m_launcher, m_elevator, m_ampulator));
    new JoystickButton(operatorXbox, XboxController.Button.kY.value).onTrue(new SmartAmpScoreFast(m_launcher, m_elevator, m_ampulator));
    //disabled for now until we get it working new JoystickButton(operatorXbox, XboxController.Button.kY.value).onTrue(new SmartAmpScore(m_trampulator, m_elevator, m_feeder, m_launcher));
    new JoystickButton(operatorXbox, XboxController.Button.kStart.value).onTrue(new ClimberGoToPosition(m_climber, -100));//-80
    new JoystickButton(operatorXbox, XboxController.Button.kBack.value).onTrue(new ClimberGoToPosition(m_climber, 0));//20
    new JoystickButton(operatorXbox, XboxController.Button.kRightBumper.value).onTrue(new IntakeWristIn(m_intake));

   new POVButton(operatorXbox, 0).onTrue(new ClimberSpin(m_climber, -0.6));
   new POVButton(operatorXbox, 0).onFalse(new ClimberSpin(m_climber, 0));
   new POVButton(operatorXbox, 180).onTrue(new ClimberSpin(m_climber, 1)); 
   new POVButton(operatorXbox, 180).onFalse(new ClimberSpin(m_climber, 0)); 

  }
 
  public Command getAutonomousCommand()
  {
    return m_chooser.get();  
  }
}
