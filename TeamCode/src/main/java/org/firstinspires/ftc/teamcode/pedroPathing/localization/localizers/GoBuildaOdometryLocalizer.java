package org.firstinspires.ftc.teamcode.pedroPathing.localization.localizers;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.GobuildaSample.GoBildaPinpointDriver;
import org.firstinspires.ftc.teamcode.GobuildaSample.Pose2D;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Localizer;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Pose;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.MathFunctions;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Vector;

public class GoBuildaOdometryLocalizer extends Localizer {
    private HardwareMap hardwareMap;
    private Pose startPose;
    private GoBildaPinpointDriver odometry;
    private double previousHeading;
    private double totalHeading;

    /**
     *
     * @param map
     */


    public GoBuildaOdometryLocalizer(HardwareMap map) {this(map, new Pose());}

    /**
     *
     * @param map
     * @param setStartPose
     */
    public GoBuildaOdometryLocalizer(HardwareMap map, Pose setStartPose) {
        hardwareMap = map;
        odometry = hardwareMap.get(GoBildaPinpointDriver.class, "odometry");
        odometry.setOffsets(-84.0, -168.0);
        odometry.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        odometry.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.REVERSED, GoBildaPinpointDriver.EncoderDirection.FORWARD);
        odometry.resetPosAndIMU();
        setStartPose(setStartPose);
        totalHeading = 0;
        previousHeading = startPose.getHeading();
    }
    /**
     * This returns the current pose estimate.
     *
     * @return returns the current pose estimate as a Pose
     */
    @Override
    public Pose getPose() {
        odometry.bulkUpdate();
        return new Pose(odometry.getPosition().getX(DistanceUnit.INCH), odometry.getPosition().getY(DistanceUnit.INCH), odometry.getPosition().getHeading(AngleUnit.RADIANS));
    }

    /**
     * This returns the current velocity estimate.
     *
     * @return returns the current velocity estimate as a Pose
     */
    @Override
    public Pose getVelocity() {
        odometry.bulkUpdate();
        return new Pose(odometry.getVelocity().getX(DistanceUnit.INCH), odometry.getVelocity().getY(DistanceUnit.INCH), odometry.getVelocity().getHeading(AngleUnit.RADIANS));
    }

    /**
     * This returns the current velocity estimate.
     *
     * @return returns the current velocity estimate as a Vector
     */
    @Override
    public Vector getVelocityVector() {
        return getVelocity().getVector();
    }

    /**
     * This sets the start pose. Changing the start pose should move the robot as if all its
     * previous movements were displacing it from its new start pose.
     *
     * @param setStart the new start pose
     */
    @Override
    public void setStartPose(Pose setStart) {

        startPose = setStart;
    }

    /**
     * This sets the current pose estimate. Changing this should just change the robot's current
     * pose estimate, not anything to do with the start pose.
     *
     * @param setPose the new current pose estimate
     */
    @Override
    public void setPose(Pose setPose) {
        odometry.resetPosAndIMU();
        Pose setOdoPose = setPose.copy();// had start pos added to set pos may need to add it back
        odometry.setPosition(new Pose2D(DistanceUnit.INCH, setOdoPose.getX(), setOdoPose.getY(), AngleUnit.RADIANS,setOdoPose.getHeading()));
    }
    /**
     * This updates the total heading of the robot.
     */
    @Override
    public void update() {
       totalHeading += MathFunctions.getSmallestAngleDifference(odometry.getHeading(), previousHeading);
       previousHeading = odometry.getPosition().getHeading(AngleUnit.RADIANS);
    }
    /**
     * This returns how far the robot has turned in radians, in a number not clamped between 0 and
     * 2 * pi radians. This is used for some tuning things and nothing actually within the following.
     *
     * @return returns how far the robot has turned in total, in radians.
     */

    public double getTotalHeading() {return totalHeading;}


    /** does nothing but needed methods
     *
     */
    @Override
    public double getForwardMultiplier() {
        return 1;
    }
    @Override
    public double getLateralMultiplier() {
        return 1;
    }
    @Override
    public double getTurningMultiplier() {
        return 1;
    }
    public void resetIMU() {
    }
}
