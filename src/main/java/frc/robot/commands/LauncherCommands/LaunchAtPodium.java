package frc.robot.commands.LauncherCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.FeederCommands.FeederGo;
import frc.robot.commands.FeederCommands.FeederStop;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.LimelightSubsystem;

public class LaunchAtPodium extends SequentialCommandGroup {

    public LaunchAtPodium( 
        LauncherSubsystem launcher
        )
    {
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
