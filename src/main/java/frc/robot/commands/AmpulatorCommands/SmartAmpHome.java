package frc.robot.commands.AmpulatorCommands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.LauncherCommands.LauncherAim;
import frc.robot.commands.LauncherCommands.LauncherStop;
import frc.robot.commands.ElevatorCommands.ElevatorGoToPosition;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.AmpulatorSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;



public class SmartAmpHome extends SequentialCommandGroup {

  public SmartAmpHome(LauncherSubsystem launcher,ElevatorSubsystem m_Elevator, AmpulatorSubsystem ampulator) {
    addCommands(
      new ElevatorGoToPosition(m_Elevator, 0),
      new WaitCommand(.2),
      new LauncherStop(launcher),
      new LauncherAim(launcher, 0),
      new AmpulatorIn(ampulator)
    );  
  }

}