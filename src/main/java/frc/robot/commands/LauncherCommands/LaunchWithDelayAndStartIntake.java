package frc.robot.commands.LauncherCommands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.FeederCommands.FeederGo;
import frc.robot.commands.FeederCommands.FeederStop;
import frc.robot.commands.IntakeCommands.IntakeSpin;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LauncherSubsystem;

public class LaunchWithDelayAndStartIntake extends SequentialCommandGroup {

    public LaunchWithDelayAndStartIntake(LauncherSubsystem launcher, FeederSubsystem feeder, IntakeSubsystem intake){
        addCommands(
            new WaitCommand(0.4),
            new LauncherGo(launcher),
            new WaitCommand(0.8),
            new FeederGo(feeder, -0.8),
            new IntakeSpin(intake, 0.75),
            new WaitCommand(1),
            new LauncherStop(launcher),
            new FeederStop(feeder),
            new LauncherAimHome(launcher)
        );
    }
    
    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
