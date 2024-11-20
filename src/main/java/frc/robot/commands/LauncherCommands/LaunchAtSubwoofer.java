package frc.robot.commands.LauncherCommands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.LauncherSubsystem;

public class LaunchAtSubwoofer extends SequentialCommandGroup {

    public LaunchAtSubwoofer( LauncherSubsystem launcher){
        addCommands(
            new LauncherAim(launcher, 47.5),
            new LauncherGo(launcher)
        );
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
