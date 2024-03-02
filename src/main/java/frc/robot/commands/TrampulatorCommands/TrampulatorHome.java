package frc.robot.commands.TrampulatorCommands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.commands.ElevatorCommands.ElevatorGoToPosition;
import frc.robot.commands.ElevatorCommands.ElevatorHome;
import frc.robot.commands.LauncherCommands.LauncherAim;
import frc.robot.commands.TrampulatorCommands.TrampulatorManipulatorCommands.TrampulatorManipulatorSpin;
import frc.robot.commands.TrampulatorCommands.TrampulatorWristCommands.TrampulatorWristGoToPosition;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.TrampulatorSubsystem;


public class TrampulatorHome extends SequentialCommandGroup {

  public TrampulatorHome(TrampulatorSubsystem m_trampulator, ElevatorSubsystem m_elevator, LauncherSubsystem m_launcher) {
    addCommands(
      new ElevatorGoToPosition(m_elevator, -20),
      new TrampulatorWristGoToPosition(m_trampulator, 0),
      new LauncherAim(m_launcher, 0)
      
    );  
  }

}