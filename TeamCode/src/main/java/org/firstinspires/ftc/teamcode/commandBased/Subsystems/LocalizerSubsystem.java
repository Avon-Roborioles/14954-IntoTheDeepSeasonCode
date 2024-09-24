package org.firstinspires.ftc.teamcode.commandBased.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class LocalizerSubsystem extends SubsystemBase {
    private Telemetry telemetry;
    private ImuSubsystem imuSubsystem;
    private LimelightSubsystem limelightSubsystem;
    private OdometrySubsystem odometrySubsystem;
    private double imuYawCorrection = 0;
    private double yaw = 0;
    private boolean yawCorrectionSet = false;
    private double driveEaseCorrection = 0;

    public LocalizerSubsystem(Telemetry telemetry, ImuSubsystem imuSubsystem, LimelightSubsystem limelightSubsystem, OdometrySubsystem odometrySubsystem){
        this.telemetry = telemetry;
        this.imuSubsystem = imuSubsystem;
        this.limelightSubsystem = limelightSubsystem;
        this.odometrySubsystem = odometrySubsystem;
    }
    public double getImuHeading(){
        return imuSubsystem.getImuYawDeg() - imuYawCorrection;
    }
    public void getLocalizerTelemetry(){
        telemetry.addData("Limelight Localizer Heading", getLimelightHeading());
        telemetry.addData("Localizer Yaw", getLocalizerHeadingTele());
    }
    public double getLimelightHeading(){
        return limelightSubsystem.getYawAprilTag();
    }
    public double getLocalizerHeadingTele(){
        odometrySubsystem.updateOdometry();
        return odometrySubsystem.getOdometryPose().getHeading(AngleUnit.DEGREES);
    }
}
