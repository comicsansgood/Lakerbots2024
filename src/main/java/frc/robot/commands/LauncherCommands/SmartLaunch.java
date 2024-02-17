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

public class SmartLaunch extends SequentialCommandGroup {

    public SmartLaunch(
        SwerveSubsystem drivetrain, 
        LauncherSubsystem launcher, 
        LimelightSubsystem limelight, 
        FeederSubsystem feeder)
    {
        addCommands(
            new AimAtSpeaker(drivetrain, limelight),
            new LauncherSmartAim(launcher, limelight),
            new LauncherSmartShoot(launcher, limelight),
            new WaitCommand(0.2),
            new FeederGo(feeder, 0.3),
            new WaitCommand(0.75),
            new LauncherStop(launcher),
            new FeederStop(feeder),
            new LauncherAim(launcher, Constants.LauncherConstants.launcherHome)
        );
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
