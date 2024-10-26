package org.firstinspires.ftc.teamcode.commandBased.Subsystems;

import static java.lang.Math.PI;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.limelightvision.LLResult;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public class LocalizerSubsystem extends SubsystemBase {
    private Telemetry telemetry;
    private LimelightSubsystem limelightSubsystem;
    private OdometrySubsystem odometrySubsystem;
    private double yaw = 0;
    private boolean yawCorrectionSet = false;
    public boolean posCorrectionSet = false;
    Pose2D localizerPose;
    double xOffset = 0;
    double yOffset = 0;
    double headingOffset = 0;


    public LocalizerSubsystem(Telemetry telemetry, LimelightSubsystem limelightSubsystem, OdometrySubsystem odometrySubsystem){
        this.telemetry = telemetry;
        this.limelightSubsystem = limelightSubsystem;
        this.odometrySubsystem = odometrySubsystem;
        localizerPose = odometrySubsystem.getOdometryPose();
    }
    public void getLocalizerTelemetry(){
        telemetry.addData("Limelight Localizer Heading", getLimelightHeading());
        telemetry.addData("Localizer Yaw", getLocalizerHeadingTele());
        telemetry.addData("Pos x", getLocalizerPose().getX(DistanceUnit.INCH));
        telemetry.addData("Pos y", getLocalizerPose().getY(DistanceUnit.INCH));
        telemetry.addData("Pos heading", getLocalizerPose().getHeading(AngleUnit.DEGREES));
    }
    public double getLimelightHeading(){
        return limelightSubsystem.getYawAprilTag();
    }
    public double getOdoHeading(){
        return odometrySubsystem.getHeadingOdo();
    }
    public Pose2D getLocalizerPose(){
        localizerPose = new Pose2D(DistanceUnit.INCH, odometrySubsystem.getOdometryPose().getX(DistanceUnit.INCH) - xOffset, odometrySubsystem.getOdometryPose().getY(DistanceUnit.INCH) + yOffset, AngleUnit.RADIANS, odometrySubsystem.getOdometryPose().getHeading(AngleUnit.RADIANS) - headingOffset);
//        localizerPose = odometrySubsystem.getOdometryPose();
        return localizerPose;
    }
    public Pose2D getLocalizerVelocity(){
        return odometrySubsystem.getOdometryVelocity();
    }
    public void update(){
        odometrySubsystem.updateOdometry();
    }
    public void setLocalizerPose(Pose2D pose){
        odometrySubsystem.setOdoPos(pose);
    }
    public void cameraAjust(){
        LLResult limeLightResult = limelightSubsystem.readAprilTag();
        odometrySubsystem.updateOdometry();
        Pose2D odometryPose = odometrySubsystem.getOdometryPose();
        Pose2D limeLightPose = new Pose2D(DistanceUnit.METER, limeLightResult.getBotpose().getPosition().y, limeLightResult.getBotpose().getPosition().x, AngleUnit.RADIANS, limeLightResult.getBotpose().getOrientation().getYaw(AngleUnit.RADIANS));
        telemetry.addData("LL Result x", limeLightPose.getX(DistanceUnit.INCH));
        telemetry.addData("LL Result y", limeLightPose.getY(DistanceUnit.INCH) );
        telemetry.addData("LL Result yaw", limeLightPose.getHeading(AngleUnit.RADIANS));
        telemetry.update();
        // Lime light x and y are swapped compared to odometry x and y

        if (limeLightResult != null){
            xOffset = odometryPose.getX(DistanceUnit.INCH) - limeLightPose.getX(DistanceUnit.INCH);
            yOffset = odometryPose.getY(DistanceUnit.INCH) - limeLightPose.getY(DistanceUnit.INCH);
            headingOffset = odometryPose.getHeading(AngleUnit.DEGREES) - (limeLightPose.getHeading(AngleUnit.RADIANS) + PI);
        }
    }

    public double getLocalizerHeadingTele(){
        LLResult llResult = limelightSubsystem.readAprilTag();
        Pose2D pos = odometrySubsystem.getOdometryPose();
        yaw = pos.getHeading(AngleUnit.DEGREES);
        if (llResult == null) {
            yaw = pos.getHeading(AngleUnit.DEGREES);
        }else if (llResult.getBotposeTagCount() == 2) {
            yawCorrectionSet = true;
            odometrySubsystem.setOdoImuYaw(llResult.getBotpose().getOrientation().getYaw(AngleUnit.DEGREES));
            yaw = llResult.getBotpose().getOrientation().getYaw(AngleUnit.DEGREES);
        }else if (llResult.getBotposeTagCount() == 1 && !yawCorrectionSet) {
            odometrySubsystem.setOdoImuYaw(llResult.getBotpose().getOrientation().getYaw(AngleUnit.DEGREES));
            yaw = pos.getHeading(AngleUnit.DEGREES);
        }else {
            yaw = pos.getHeading(AngleUnit.DEGREES);
        }
        return yaw;
    }
}
