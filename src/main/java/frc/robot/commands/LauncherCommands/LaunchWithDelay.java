package frc.robot.commands.LauncherCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.FeederCommands.FeederGo;
import frc.robot.commands.FeederCommands.FeederStop;
import frc.robot.commands.swervedrive.AimAtSpeaker;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class LaunchWithDelay extends SequentialCommandGroup {

    public LaunchWithDelay(
        SwerveSubsystem drivetrain, 
        LauncherSubsystem launcher, 
        FeederSubsystem feeder)
    {
        addCommands(
            new LauncherGo(launcher),
            new WaitCommand(0.8),
            new FeederGo(feeder, -0.8),
            new WaitCommand(0.75),
            new LauncherStop(launcher),
            new FeederStop(feeder)
           
        );
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
