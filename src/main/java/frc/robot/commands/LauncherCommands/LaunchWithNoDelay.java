package frc.robot.commands.LauncherCommands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.FeederCommands.FeederGo;
import frc.robot.commands.FeederCommands.FeederStop;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.LauncherSubsystem;

public class LaunchWithNoDelay extends SequentialCommandGroup {

    public LaunchWithNoDelay(LauncherSubsystem launcher, FeederSubsystem feeder){
        addCommands(
            new LauncherGo(launcher),
            new FeederGo(feeder, -1),
            new WaitCommand(1),
            new LauncherStop(launcher),
            new FeederStop(feeder),
            new LauncherAimHome(launcher)
        );
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
