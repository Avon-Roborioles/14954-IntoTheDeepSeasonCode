package org.firstinspires.ftc.teamcode.commandBased.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.limelightvision.LLResult;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Examples.GobuildaSample.Pose2D;

public class LocalizerSubsystem extends SubsystemBase {
    private Telemetry telemetry;
    private LimelightSubsystem limelightSubsystem;
    private OdometrySubsystem odometrySubsystem;
    private double yaw = 0;
    private boolean yawCorrectionSet = false;
    public boolean posCorrectionSet = false;

    public LocalizerSubsystem(Telemetry telemetry, LimelightSubsystem limelightSubsystem, OdometrySubsystem odometrySubsystem){
        this.telemetry = telemetry;
        this.limelightSubsystem = limelightSubsystem;
        this.odometrySubsystem = odometrySubsystem;
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
        Pose2D odoPos = odometrySubsystem.getOdometryPose();
        LLResult llResult = limelightSubsystem.readAprilTag();
        Pose2D localPos = null;
        if (llResult == null) {
        }else if (llResult.getBotposeTagCount() == 2) {
            posCorrectionSet = true;
            localPos = new Pose2D(DistanceUnit.INCH, llResult.getBotpose().getPosition().x, llResult.getBotpose().getPosition().y, AngleUnit.DEGREES, llResult.getBotpose().getOrientation().getYaw(AngleUnit.DEGREES));
            odometrySubsystem.setOdoPos(localPos);
        }else if (llResult.getBotposeTagCount() == 1 && !yawCorrectionSet) {
            localPos = new Pose2D(DistanceUnit.INCH, llResult.getBotpose().getPosition().x, llResult.getBotpose().getPosition().y, AngleUnit.DEGREES, llResult.getBotpose().getOrientation().getYaw(AngleUnit.DEGREES));
            odometrySubsystem.setOdoPos(localPos);
        }else {
            localPos = odometrySubsystem.getOdometryPose();
        }
        return localPos;
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
