package frc.robot.subsystems.drive;

import org.littletonrobotics.junction.AutoLog;

public interface TankIO {
    @AutoLog
    public static class TankIOInputs {
        public double leftVelocityRadPerSec = 0.0;
        public double rightVelocityRadPerSec = 0.0;
        public double leftAppliedVolts = 0.0;
        public double rightAppliedVolts = 0.0;
        public double[] leftCurrentAmps = new double[] {};
        public double[] rightCurrentAmps = new double[] {};
    }

    public default void updateInputs(TankIOInputs inputs) {}

    public default void drive(double move, double rotate) {}

    public default void stop() {}
}