package frc.robot.commands.LauncherCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.FeederCommands.FeederGo;
import frc.robot.commands.FeederCommands.FeederStop;
import frc.robot.commands.swervedrive.AimAtSpeaker;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class LaunchAtMiddlePiece extends SequentialCommandGroup {

    public LaunchAtMiddlePiece(
        SwerveSubsystem drivetrain, 
        LauncherSubsystem launcher, 
        FeederSubsystem feeder,
        ElevatorSubsystem elevator)
    {
        addCommands(
            new LauncherAim(launcher, Constants.LauncherConstants.launcherAngleMiddlePiece),
            new LaunchWithDelay(drivetrain, launcher, feeder, elevator)
        );
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
