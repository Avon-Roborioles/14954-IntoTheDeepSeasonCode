package org.firstinspires.ftc.teamcode.commandBased.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TelemetrySubsystem extends SubsystemBase {
    private Telemetry telemetry;
    private ImuSubsystem imuSubsystem;
    private LimelightSubsystem limelightSubsystem;
    private DriveSubsystem driveSubsystem;
    private LocalizerSubsystem localizerSubsystem;

    public TelemetrySubsystem(Telemetry telemetry, ImuSubsystem imuSubsystem, LimelightSubsystem limelightSubsystem, DriveSubsystem driveSubsystem, LocalizerSubsystem localizerSubsystem){
        this.telemetry = telemetry;
        this.imuSubsystem = imuSubsystem;
        this.limelightSubsystem = limelightSubsystem;
        this.driveSubsystem = driveSubsystem;
        this.localizerSubsystem = localizerSubsystem;
        telemetry.setMsTransmissionInterval(12);

    }
    public void getTelemetry(){
        clearTelemetry();
        imuSubsystem.getImuTelemetry();
        localizerSubsystem.getLocalizerTelemetry();
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
