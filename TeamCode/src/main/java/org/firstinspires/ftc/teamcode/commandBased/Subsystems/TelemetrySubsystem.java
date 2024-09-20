package org.firstinspires.ftc.teamcode.commandBased.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TelemetrySubsystem extends SubsystemBase {
    private Telemetry telemetry;
    private ImuSubsystem imuSubsystem;
    private LimelightSubsystem limelightSubsystem;
    private DriveSubsystem driveSubsystem;

    public TelemetrySubsystem(Telemetry telemetry, ImuSubsystem imuSubsystem, LimelightSubsystem limelightSubsystem, DriveSubsystem driveSubsystem ){
        this.telemetry = telemetry;
        this.imuSubsystem = imuSubsystem;
        this.limelightSubsystem = limelightSubsystem;
        this.driveSubsystem = driveSubsystem;
        telemetry.setMsTransmissionInterval(12);
    }
    public void getTelemetry(){
        clearTelemetry();
        imuSubsystem.getImuTelemetry();
        limelightSubsystem.getLimelightTelemetry();
        driveSubsystem.getDriveTelemetry();
        updateTelemetry();
    }
    public void updateTelemetry(){
        telemetry.update();
    }
    public void clearTelemetry(){
        telemetry.clearAll();
    }
}
