package frc.robot.commands.LauncherCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.ElevatorCommands.ElevatorGoToPosition;
import frc.robot.commands.FeederCommands.FeederGo;
import frc.robot.commands.FeederCommands.FeederStop;
import frc.robot.commands.IntakeCommands.IntakeSpin;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.LimelightSubsystem;

public class LaunchWithDelayAndStartIntake extends SequentialCommandGroup {

    public LaunchWithDelayAndStartIntake(
        LauncherSubsystem launcher, 
        FeederSubsystem feeder,
        /*ElevatorSubsystem elevator,*/
        IntakeSubsystem intake)
    {
        addCommands(
            //new ElevatorGoToPosition(elevator, -20),
            new WaitCommand(0.4),
            new LauncherGo(launcher),
            new WaitCommand(0.8),
            new FeederGo(feeder, -0.8),
            new IntakeSpin(intake, 0.75),
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
