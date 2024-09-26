package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.DualNum;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.Time;
import com.acmerobotics.roadrunner.Twist2dDual;
import com.acmerobotics.roadrunner.Vector2dDual;
import com.acmerobotics.roadrunner.ftc.FlightRecorder;
import com.acmerobotics.roadrunner.ftc.PositionVelocityPair;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.GobuildaSample.GoBildaPinpointDriver;
import org.firstinspires.ftc.teamcode.GobuildaSample.Pose2D;

@Config
public class goBuildaOdometryLocalizer implements Localizer{
    public static class Params {
        public static double X_OFFSET = -84.0;
        public static double Y_OFFSET = -168.0;
    }
    public static Params PARAMS = new Params();
    private GoBildaPinpointDriver odometry;
    private Telemetry telemetry;

    public goBuildaOdometryLocalizer(GoBildaPinpointDriver odometry, HardwareMap hardwareMap, Telemetry telemetry ) {
        this.odometry = odometry;
        this.telemetry = telemetry;
        this.odometry = hardwareMap.get(GoBildaPinpointDriver.class, "odometry");
        this.odometry.setOffsets(-84.0, -168.0);
        this.odometry.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        this.odometry.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.REVERSED, GoBildaPinpointDriver.EncoderDirection.FORWARD);
        this.odometry.resetPosAndIMU();
        telemetry.addData("Status", odometry.getDeviceStatus());
        telemetry.update();
        FlightRecorder.write("TWO_DEAD_WHEEL_PARAMS", PARAMS);
    }

    @Override
    public Twist2dDual<Time> update() {



        return null;
    }
}
