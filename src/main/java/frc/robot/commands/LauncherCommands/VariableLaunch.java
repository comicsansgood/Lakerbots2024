package frc.robot.commands.LauncherCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.FeederCommands.FeederGo;
import frc.robot.commands.FeederCommands.FeederStop;
import frc.robot.commands.swervedrive.AimAtSpeaker;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class VariableLaunch extends SequentialCommandGroup {

    public VariableLaunch(
        SwerveSubsystem drivetrain, 
        LauncherSubsystem launcher, 
        FeederSubsystem feeder,
        ElevatorSubsystem elevator,
        IntakeSubsystem intake,
        double theta)
    {
        addCommands(
            new LauncherAim(launcher, theta),
            new LaunchWithDelayAndStartIntake(drivetrain, launcher, feeder, elevator, intake)
        );
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
