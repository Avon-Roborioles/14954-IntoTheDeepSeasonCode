package org.firstinspires.ftc.teamcode.pedroPathing.localization.localizers;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Examples.GobuildaSample.GoBildaPinpointDriver;
import org.firstinspires.ftc.teamcode.Examples.GobuildaSample.Pose2D;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.LimelightSubsystem;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.LocalizerSubsystem;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.OdometrySubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Localizer;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Pose;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.MathFunctions;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Vector;

public class GoBuildaOdometryLocalizer extends Localizer {
    private HardwareMap hardwareMap;
    private Pose startPose;
    private GoBildaPinpointDriver odometry;
    private OdometrySubsystem odometrySubsystem;
    private Limelight3A limelight3A;
    private LimelightSubsystem limelightSubsystem;
    private LocalizerSubsystem localizerSubsystem;
    private double previousHeading;
    private double totalHeading;
    private Telemetry telemetry;

    /**
     *
     * @param map
     */


    public GoBuildaOdometryLocalizer(Telemetry telemetry, HardwareMap map) {this(telemetry, map, new Pose());}

    /**
     *
     * @param map
     * @param setStartPose
     * @param telemetry
     */
    public GoBuildaOdometryLocalizer( Telemetry telemetry, HardwareMap map, Pose setStartPose) {
        hardwareMap = map;
        this.telemetry = telemetry;
        odometry = hardwareMap.get(GoBildaPinpointDriver.class, "odometry");
        odometrySubsystem = new OdometrySubsystem(odometry, this.telemetry);
        limelight3A = hardwareMap.get(Limelight3A.class, "limelight");
        limelightSubsystem = new LimelightSubsystem(limelight3A,this.telemetry);
        localizerSubsystem = new LocalizerSubsystem(this.telemetry, limelightSubsystem, odometrySubsystem);
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
        return new Pose(odometrySubsystem.getOdometryPose().getX(DistanceUnit.INCH), odometrySubsystem.getOdometryPose().getY(DistanceUnit.INCH), odometrySubsystem.getOdometryPose().getHeading(AngleUnit.RADIANS));
    }

    /**
     * This returns the current velocity estimate.
     *
     * @return returns the current velocity estimate as a Pose
     */
    @Override
    public Pose getVelocity() {
        return new Pose(odometrySubsystem.getOdometryVelocity().getX(DistanceUnit.INCH), odometrySubsystem.getOdometryVelocity().getY(DistanceUnit.INCH), odometrySubsystem.getOdometryVelocity().getHeading(AngleUnit.RADIANS));
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
        odometrySubsystem.resetOdometry();
        Pose setOdoPose = setPose.copy();// had start pos added to set pos may need to add it back
        odometrySubsystem.setOdoPos(new Pose2D(DistanceUnit.INCH, setOdoPose.getX(), setOdoPose.getY(), AngleUnit.RADIANS,setOdoPose.getHeading()));
    }
    /**
     * This updates the total heading of the robot.
     */
    @Override
    public void update() {
       totalHeading += MathFunctions.getSmallestAngleDifference(odometry.getPosition().getHeading(AngleUnit.RADIANS), previousHeading);
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
