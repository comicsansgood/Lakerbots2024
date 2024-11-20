package frc.robot.commands.LauncherCommands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.LauncherSubsystem;

public class LaunchAtMiddlePiece extends SequentialCommandGroup {

    public LaunchAtMiddlePiece(LauncherSubsystem launcher, FeederSubsystem feeder, ElevatorSubsystem elevator)
    {
        addCommands(
            new LauncherAim(launcher, Constants.LauncherConstants.launcherAngleMiddlePiece),
            new LaunchWithDelay(launcher, feeder)
        );
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
