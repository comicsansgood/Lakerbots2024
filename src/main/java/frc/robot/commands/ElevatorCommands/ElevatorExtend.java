package frc.robot.commands.ElevatorCommands;

import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class ElevatorExtend extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  
  private final ElevatorSubsystem m_elevator;
  public double position;

  
  public ElevatorExtend(ElevatorSubsystem elevator, double position) {
    m_elevator = elevator;
    this.position = position;
    addRequirements(m_elevator);
  }

  @Override
  public void initialize() {
    m_elevator.elevatorSetTarget(position);
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
