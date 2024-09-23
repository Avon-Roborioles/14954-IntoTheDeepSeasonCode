package org.firstinspires.ftc.teamcode.commandBased.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LocalizerSubsystem extends SubsystemBase {
    private Telemetry telemetry;
    private ImuSubsystem imuSubsystem;
    private LimelightSubsystem limelightSubsystem;
    private double imuYawCorrection = 0;
    private double yaw = 0;
    private boolean yawCorrectionSet = false;
    private double driveEaseCorrection = 0;

    public LocalizerSubsystem(Telemetry telemetry, ImuSubsystem imuSubsystem, LimelightSubsystem limelightSubsystem){
        this.telemetry = telemetry;
        this.imuSubsystem = imuSubsystem;
        this.limelightSubsystem = limelightSubsystem;

    }
    public double getImuHeading(){
        return imuSubsystem.getImuYawDeg() - imuYawCorrection;
    }
    public void getLocalizerTelemetry(){
        telemetry.addData("Imu Localizer Heading", getImuHeading());
        telemetry.addData("Limelight Localizer Heading", getLimelightHeading());
        telemetry.addData("Local Yaw", yaw);
    }
    public double getLimelightHeading(){
        return limelightSubsystem.getYawAprilTag();
    }
    public double getLocalizerHeadingTele(){
//        if(redAlliance){
//            driveEaseCorrection = -90;
//        }else {
//            driveEaseCorrection = 90;
//        }
        if(limelightSubsystem.readAprilTag().getBotposeTagCount() >= 2){
            imuSubsystem.resetYaw();
            imuYawCorrection = limelightSubsystem.getYawAprilTag() - getImuHeading();
            yawCorrectionSet =true;
            yaw = limelightSubsystem.getYawAprilTag();
        }else if(limelightSubsystem.readAprilTag().getBotposeTagCount() == 1 && yawCorrectionSet){
            yaw = (((imuSubsystem.getImuYawDeg() + imuYawCorrection)*20 + 80* limelightSubsystem.getYawAprilTag())/2)/100;
        }else if(limelightSubsystem.readAprilTag().getBotposeTagCount() == 1) {
            yaw = limelightSubsystem.getYawAprilTag();
        }else{
            yaw = imuSubsystem.getImuYawDeg();
        }
        return yaw;
    }
}
