package frc.robot.commands.ElevatorCommands;

import frc.robot.Constants;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class ElevatorHome extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  
  private final ElevatorSubsystem m_elevator;

  
  public ElevatorHome(ElevatorSubsystem elevator) {
    m_elevator = elevator;
    addRequirements(m_elevator);
  }

  @Override
  public void initialize() {
    m_elevator.elevatorSetTarget(Constants.ElevatorConstants.elevatorHome);
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return m_elevator.elevatorIsAtTarget();
  }
}
