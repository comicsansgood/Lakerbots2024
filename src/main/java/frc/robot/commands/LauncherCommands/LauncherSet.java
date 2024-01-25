// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.LauncherCommands;

import frc.robot.subsystems.LauncherSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class LauncherSet extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final LauncherSubsystem m_launcer;
  public double val1;
  public double val2;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public LauncherSet(LauncherSubsystem launcher, double va1, double val2) {
    m_launcer = launcher;
    this.val1 = val1;
    this.val2 = val2;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_launcer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_launcer.launcherSet(val1, val2);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
