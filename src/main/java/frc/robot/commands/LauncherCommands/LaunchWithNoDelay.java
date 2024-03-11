package frc.robot.commands.LauncherCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.ElevatorCommands.ElevatorGoToPosition;
import frc.robot.commands.FeederCommands.FeederGo;
import frc.robot.commands.FeederCommands.FeederStop;
import frc.robot.commands.swervedrive.AimAtSpeaker;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class LaunchWithNoDelay extends SequentialCommandGroup {

    public LaunchWithNoDelay(
        SwerveSubsystem drivetrain, 
        LauncherSubsystem launcher, 
        FeederSubsystem feeder/*,
        ElevatorSubsystem elevator*/)
    {
        addCommands(
            //new ElevatorGoToPosition(elevator, -20),
            new LauncherGo(launcher),
            new FeederGo(feeder, -1),
            new WaitCommand(1),
            new LauncherStop(launcher),
            new FeederStop(feeder),
            new LauncherAim(launcher, 0)
           
        );
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
