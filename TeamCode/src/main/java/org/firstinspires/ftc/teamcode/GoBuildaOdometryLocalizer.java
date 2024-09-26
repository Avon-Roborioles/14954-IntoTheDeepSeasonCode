package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.DualNum;
import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.Time;
import com.acmerobotics.roadrunner.Twist2dDual;
import com.acmerobotics.roadrunner.Vector2dDual;
import com.acmerobotics.roadrunner.ftc.Encoder;
import com.acmerobotics.roadrunner.ftc.FlightRecorder;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.GobuildaSample.GoBildaPinpointDriver;

@Config
public final class GoBuildaOdometryLocalizer implements Localizer{
    public static class Params {
        public static double X_OFFSET = -84.0;
        public static double Y_OFFSET = -168.0;
    }
    public static Params PARAMS = new Params();
    private GoBildaPinpointDriver odometry;

    private int lastParPos, lastPerpPos;
    private Rotation2d lastHeading;

    private double lastRawHeadingVel, headingVelOffset;
    private boolean initialized;

    public GoBuildaOdometryLocalizer(GoBildaPinpointDriver odometry, HardwareMap hardwareMap) {
        this.odometry = odometry;
        this.odometry = hardwareMap.get(GoBildaPinpointDriver.class, "odometry");
        this.odometry.setOffsets(-84.0, -168.0);
        this.odometry.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        this.odometry.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.REVERSED, GoBildaPinpointDriver.EncoderDirection.FORWARD);
        this.odometry.resetPosAndIMU();
    }

    @Override
    public Twist2dDual<Time> update() {
        Rotation2d heading = Rotation2d.exp(odometry.getPosition().getHeading(AngleUnit.RADIANS));
        // might need to add for the flip at 180 that way heading goes from 0 to 2pi and not -pi to pi
        if(!initialized) {
            initialized = true;
           lastHeading = heading;
        }
        double headingDelta = heading.minus(lastHeading);
        Twist2dDual<Time> twist = new Twist2dDual<>(
                new Vector2dDual<>(
                        new DualNum<Time>(new double[] {
                                //parPosDelta - PARAMS.parYTicks * headingDelta,    // try x pos and see
                                odometry.getPosition().getX(DistanceUnit.INCH),
                                //parPosVel.velocity - PARAMS.parYTicks * headingVel, // same for v
                                odometry.getVelocity().getX(DistanceUnit.INCH),
                        }),
                        new DualNum<Time>(new double[] {
                                //perpPosDelta - PARAMS.perpXTicks * headingDelta,
                                odometry.getPosition().getY(DistanceUnit.INCH),
                                //perpPosVel.velocity - PARAMS.perpXTicks * headingVel,
                                odometry.getVelocity().getY(DistanceUnit.INCH),
                        })
                ),
                new DualNum<>(new double[] {
                        //headingDelta,  change in heading,
                        headingDelta,
                        //headingVel, velocity
                        odometry.getHeadingVelocity()
                })
        );
        lastHeading = heading;
        return twist;

    }
}
