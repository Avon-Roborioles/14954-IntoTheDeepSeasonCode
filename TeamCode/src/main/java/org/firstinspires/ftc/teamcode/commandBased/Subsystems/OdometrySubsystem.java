package org.firstinspires.ftc.teamcode.commandBased.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Examples.GobuildaSample.GoBildaPinpointDriver;
import org.firstinspires.ftc.teamcode.Examples.GobuildaSample.Pose2D;

import java.util.Locale;

public class OdometrySubsystem extends SubsystemBase {
    private GoBildaPinpointDriver odometry;
    private Telemetry telemetry;

    public OdometrySubsystem(GoBildaPinpointDriver odometry, Telemetry telemetry) {
        this.odometry = odometry;
        this.telemetry = telemetry;
        odometry.setOffsets(-120, -132);
        odometry.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        odometry.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.REVERSED, GoBildaPinpointDriver.EncoderDirection.FORWARD);
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
    public Pose2D getOdometryVelocity(){
        odometry.bulkUpdate();
        return odometry.getVelocity();
    }
    public double getHeadingOdo(){
        return getOdometryPose().getHeading(AngleUnit.DEGREES);
    }
    public void resetOdometry(){
        odometry.resetPosAndIMU();
    }
    public void resetOdoImu(){
        odometry.recalibrateIMU();
    }
    public void setOdoImuYaw(double yaw){
        odometry.setPosition(new Pose2D( DistanceUnit.INCH, odometry.getPosition().getX(DistanceUnit.INCH) ,(odometry.getPosition().getY(DistanceUnit.INCH)), AngleUnit.DEGREES, yaw));
    }
    public void setOdoPos(Pose2D pos){
        odometry.setPosition(pos);
    }

    public void getTelemetryOdo(){
        Pose2D pos = odometry.getPosition();
        telemetry.addData("X", pos.getX(DistanceUnit.INCH));
        telemetry.addData("Y", pos.getY(DistanceUnit.INCH));
        telemetry.addData("Heading", pos.getHeading(AngleUnit.DEGREES));
        Pose2D odometryPosition = odometry.getPosition();
        String data = String.format(Locale.US, "{X: %.3f, Y: %.3f, H: %.3f}", odometryPosition.getX(DistanceUnit.INCH), odometryPosition.getY(DistanceUnit.INCH), odometryPosition.getHeading(AngleUnit.DEGREES));
        telemetry.addData("Position", data);
        Pose2D vel = odometry.getVelocity();
        String velocity = String.format(Locale.US,"{XVel: %.3f, YVel: %.3f, HVel: %.3f}", vel.getX(DistanceUnit.INCH), vel.getY(DistanceUnit.INCH), vel.getHeading(AngleUnit.DEGREES));
        telemetry.addData("Velocity", velocity);
        telemetry.addData("X Encoder:", odometry.getEncoderX()); //gets the raw data from the X encoder
        telemetry.addData("Y Encoder:",odometry.getEncoderY()); //gets the raw data from the Y encoder
        telemetry.addData("Pinpoint Frequency", odometry.getFrequency());
        telemetry.addData("Status", odometry.getDeviceStatus());
    }

}
