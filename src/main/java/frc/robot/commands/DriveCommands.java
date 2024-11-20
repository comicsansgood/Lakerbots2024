// Copyright 2021-2023 FRC 6328
// http://github.com/Mechanical-Advantage
//
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// version 3 as published by the Free Software Foundation or
// available in the root directory of this project.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.

package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Constants;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.drive.Drive;
import java.util.function.DoubleSupplier;

public class DriveCommands {
  private static final double DEADBAND = 0.2;
  private static final double JOYSTICK_GOVERNOR = 0.3; // this value must not exceed 1.0
  //private static final double THROTTLE_GOVERNOR = 1.0 - JOYSTICK_GOVERNOR;

  private DriveCommands() {}


  public static Command zeroGyro(Drive drive){
    return Commands.run(() -> {drive.resetHeading();}, drive).withTimeout(0.1);
  }

  /**
   * Field relative drive command using two joysticks (controlling linear and angular velocities).
   */
  public static Command joystickDrive(
      Drive drive,
      DoubleSupplier xSupplier,
      DoubleSupplier ySupplier,
      DoubleSupplier omegaSupplier,
      DoubleSupplier throttleSupplier) {

    switch(Constants.currentMode){
      case TANK:
        return Commands.run(
        () -> {  drive.tankDrive( ySupplier.getAsDouble(), omegaSupplier.getAsDouble()); },
         drive);

      case REAL:
      case SIM:
      case REPLAY:
      return Commands.run(
          () -> {
            // Apply deadband
            double linearMagnitude =
                MathUtil.applyDeadband(
                    Math.hypot(xSupplier.getAsDouble(), ySupplier.getAsDouble()),
                    DEADBAND); // get the magnitude of the joystick

            Rotation2d linearDirection =
                new Rotation2d(xSupplier.getAsDouble(), ySupplier.getAsDouble());

            double omega = MathUtil.applyDeadband(omegaSupplier.getAsDouble(), DEADBAND);

            double throttle = MathUtil.applyDeadband(throttleSupplier.getAsDouble(), DEADBAND);

            //  Old implementation : just square values
            // linearMagnitude = linearMagnitude * linearMagnitude;
            // omega = Math.copySign(omega * omega, omega);

            // New implementation : square values and apply relative throttle
            // Note : we don't care about the magnitude of the throttle, as we have the
            // linearDirection to apply later
            // Note : only apply throttle if we have provided a linear magnitude

            /* // Original implementation @ BSU
            linearMagnitude = (linearMagnitude * linearMagnitude * JOYSTICK_GOVERNOR);
            if (linearMagnitude > 0.0 && throttle > 0.0) {
              linearMagnitude +=
                  Math.copySign(throttleSupplier.getAsDouble() * THROTTLE_GOVERNOR, linearMagnitude);
            }
            */

            // Slow mode if Trigger is pulled
            if(throttle > 0.25){
              linearMagnitude = linearMagnitude * linearMagnitude * JOYSTICK_GOVERNOR;
            }
            else{
              linearMagnitude = linearMagnitude * linearMagnitude;
            }


            // Note : we need to consider the sign as we don't have a linearDirection for the right
            // joystick axis
            

             if ( throttle > 0.25) {
              //omega += Math.copySign(throttleSupplier.getAsDouble() * THROTTLE_GOVERNOR, omega);
              omega = Math.copySign((omega * omega * JOYSTICK_GOVERNOR), omega); // full pow'ah baby
            }else
            {
              omega = Math.copySign((omega * omega ), omega);
            }

            // Calcaulate new linear velocity
            Translation2d linearVelocity =
                new Pose2d(new Translation2d(), linearDirection)
                    .transformBy(new Transform2d(linearMagnitude, 0.0, new Rotation2d()))
                    .getTranslation();
            if(DriverStation.getAlliance().get() == Alliance.Red){
              linearVelocity = linearVelocity.rotateBy(Rotation2d.fromRadians(Math.PI));
            }

            // Convert to field relative speeds & send command
            drive.runVelocity(
                ChassisSpeeds.fromFieldRelativeSpeeds(
                    linearVelocity.getX() * drive.getMaxLinearSpeedMetersPerSec(),
                    linearVelocity.getY() * drive.getMaxLinearSpeedMetersPerSec(),
                    omega * drive.getMaxAngularSpeedRadPerSec(),
                    drive.getRotation()));
          },
          drive);

      default:
        return Commands.run(
        () -> {} /* do nothing */,
        drive);
    }
  }
  public Command maintainHeading(Drive drive, double heading){
    switch(Constants.currentMode){  
    default:
        return Commands.run(
        () -> {} /* do nothing */,
        drive);
    }
  }






  public static Command limelightDrive(
      Drive drive, LimelightSubsystem limelight,
      DoubleSupplier xSupplier,
      DoubleSupplier ySupplier,
      DoubleSupplier throttleSupplier) {

    switch(Constants.currentMode){
      case TANK:
        return Commands.run(
        () -> {  drive.tankDrive( ySupplier.getAsDouble(), /*omegaSupplier.getAsDouble()*/0.0); },
         drive);
      case REAL:
      case SIM:
      case REPLAY:



      return Commands.run(
          () -> {

            PIDController rotationController = new PIDController(0.001, 0, 0);
            rotationController.setSetpoint(0);

            // Apply deadband
            double linearMagnitude =
                MathUtil.applyDeadband(
                    Math.hypot(xSupplier.getAsDouble(), ySupplier.getAsDouble()),
                    DEADBAND); // get the magnitude of the joystick

            Rotation2d linearDirection =
                new Rotation2d(xSupplier.getAsDouble(), ySupplier.getAsDouble());


            double tagX = limelight.getTagX();
           
            
            double omega = MathUtil.applyDeadband(rotationController.calculate(tagX), DEADBAND);

            double throttle = MathUtil.applyDeadband(throttleSupplier.getAsDouble(), DEADBAND);

            
            if(throttle > 0.25){
              linearMagnitude = linearMagnitude * linearMagnitude * JOYSTICK_GOVERNOR;
            }
            else{
              linearMagnitude = linearMagnitude * linearMagnitude;
            }


             if ( throttle > 0.25) {
              omega = Math.copySign((omega * omega * JOYSTICK_GOVERNOR), omega); // full pow'ah baby
            }else
            {
              omega = Math.copySign((omega * omega ), omega);
            }

            // Calcaulate new linear velocity
            Translation2d linearVelocity =
                new Pose2d(new Translation2d(), linearDirection)
                    .transformBy(new Transform2d(linearMagnitude, 0.0, new Rotation2d()))
                    .getTranslation();
            if(DriverStation.getAlliance().get() == Alliance.Red){
              linearVelocity = linearVelocity.rotateBy(Rotation2d.fromRadians(Math.PI));
            }

            // Convert to field relative speeds & send command
            drive.runVelocity(
                ChassisSpeeds.fromFieldRelativeSpeeds(
                    linearVelocity.getX() * drive.getMaxLinearSpeedMetersPerSec(),
                    linearVelocity.getY() * drive.getMaxLinearSpeedMetersPerSec(),
                    omega * drive.getMaxAngularSpeedRadPerSec(),
                    drive.getRotation()));
          },
          drive);

      default:
        return Commands.run(
        () -> {} /* do nothing */,
        drive);
    }
  }

}