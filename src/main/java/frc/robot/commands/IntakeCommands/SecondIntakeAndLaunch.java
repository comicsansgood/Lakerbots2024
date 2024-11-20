package frc.robot.commands.IntakeCommands;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.FeederCommands.FeederGo;
import frc.robot.commands.LauncherCommands.LauncherGo;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LauncherSubsystem;

public class SecondIntakeAndLaunch extends SequentialCommandGroup {

  public SecondIntakeAndLaunch(IntakeSubsystem m_intake, FeederSubsystem m_feeder, LauncherSubsystem launcher, XboxController controller) {
    addCommands(
      new FeederGo(m_feeder, -.6),
      new LauncherGo(launcher)
    );  
  }
}