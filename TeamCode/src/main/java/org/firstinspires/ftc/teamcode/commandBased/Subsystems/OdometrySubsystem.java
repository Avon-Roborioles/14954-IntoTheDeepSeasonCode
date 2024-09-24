package org.firstinspires.ftc.teamcode.commandBased.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.GobuildaSample.GoBildaPinpointDriver;
import org.firstinspires.ftc.teamcode.GobuildaSample.Pose2D;

import java.util.Locale;

public class OdometrySubsystem extends SubsystemBase {
    private GoBildaPinpointDriver odometry;
    private Telemetry telemetry;

    public OdometrySubsystem(GoBildaPinpointDriver odometry, Telemetry telemetry) {
        this.odometry = odometry;
        this.telemetry = telemetry;
        odometry.setOffsets(-84.0, -168.0);
        odometry.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_SWINGARM_POD);
        odometry.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.REVERSED);
        odometry.resetPosAndIMU();
        telemetry.addData("Status", odometry.getDeviceStatus());
        telemetry.update();
    }
    public void updateOdometry() {
        odometry.bulkUpdate();
    }
    public Pose2D getOdometryPose(){
        odometry.bulkUpdate();
        return odometry.getPosition();
    }
    public double getHeadingOdo(){
        getOdometryPose();
        return odometry.getHeading();
    }
    public void resetOdometry(){
        odometry.resetPosAndIMU();
    }
    public void resetOdoImu(){
        odometry.recalibrateIMU();
    }
    public void getTelemetryOdo(){
        Pose2D pos = odometry.getPosition();
        telemetry.addData("X", pos.getX(DistanceUnit.INCH));
        telemetry.addData("Y", pos.getY(DistanceUnit.INCH));
        telemetry.addData("Heading", pos.getHeading(AngleUnit.DEGREES));
        Pose2D vel = odometry.getVelocity();
        String velocity = String.format(Locale.US,"{XVel: %.3f, YVel: %.3f, HVel: %.3f}", vel.getX(DistanceUnit.MM), vel.getY(DistanceUnit.MM), vel.getHeading(AngleUnit.DEGREES));
        telemetry.addData("Velocity", velocity);
        telemetry.addData("X Encoder:", odometry.getEncoderX()); //gets the raw data from the X encoder
        telemetry.addData("Y Encoder:",odometry.getEncoderY()); //gets the raw data from the Y encoder
        telemetry.addData("Pinpoint Frequency", odometry.getFrequency());
        telemetry.addData("Status", odometry.getDeviceStatus());
    }

}