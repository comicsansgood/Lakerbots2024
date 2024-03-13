package frc.robot.commands.LauncherCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.FeederCommands.FeederGo;
import frc.robot.commands.FeederCommands.FeederStop;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.LimelightSubsystem;

public class VariableLaunch extends SequentialCommandGroup {

    public VariableLaunch(
        LauncherSubsystem launcher, 
        FeederSubsystem feeder,
        ElevatorSubsystem elevator,
        IntakeSubsystem intake,
        double theta)
    {
        addCommands(
            new LauncherAim(launcher, theta),
            new LaunchWithDelayAndStartIntake(launcher, feeder, intake)
        );
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
