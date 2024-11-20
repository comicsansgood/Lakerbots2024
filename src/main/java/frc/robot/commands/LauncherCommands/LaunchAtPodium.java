package frc.robot.commands.LauncherCommands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.LauncherSubsystem;

public class LaunchAtPodium extends SequentialCommandGroup {

    public LaunchAtPodium( LauncherSubsystem launcher){
        addCommands(
            new LauncherAim(launcher, Constants.LauncherConstants.launcherAngleAUTOPodium),
            new LauncherGo(launcher)
        );
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
