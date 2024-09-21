package org.firstinspires.ftc.teamcode.commandBased.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LocalizerSubsystem extends SubsystemBase {
    private Telemetry telemetry;
    private ImuSubsystem imuSubsystem;
    private LimelightSubsystem limelightSubsystem;

    public LocalizerSubsystem(Telemetry telemetry, ImuSubsystem imuSubsystem, LimelightSubsystem limelightSubsystem){
        this.telemetry = telemetry;
        this.imuSubsystem = imuSubsystem;
        this.limelightSubsystem = limelightSubsystem;

    }
    public double getImuHeading(){
        return imuSubsystem.getImuYawDeg();
    }
    public void getLocalizerTelemetry(){
        telemetry.addData("Imu Localizer Heading", getImuHeading());
        telemetry.addData("Limelight Localizer Heading", getLimelightHeading());
    }
    public double getLimelightHeading(){
        return limelightSubsystem.getYawAprilTag();
    }
    public double getLocalizerHeadingTele(Boolean redAlliance){
        return 0;
    }
}
