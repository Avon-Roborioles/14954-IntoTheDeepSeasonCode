package org.firstinspires.ftc.teamcode.AutoNoMoose;



import static java.lang.Math.PI;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.pedroPathing.follower.Follower;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Pose;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierCurve;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Path;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Point;

@Autonomous(name="PedroAutoTest", group="Auto Tests")
public class PedroAutoTest extends AutoBase{
    @Override
    public void runOpMode() throws InterruptedException {

        initializeAuto(telemetry, hardwareMap);

        follower = new Follower(hardwareMap);
        path1 = new Path(new BezierCurve(new Point(startPose), new Point(pose1), new Point(new Pose(40,-20, PI))));
        path1.setConstantHeadingInterpolation(0);
        path1.setPathEndTimeoutConstraint(6);
        follower.setPose(startPose);
        waitForStart();
        follower.followPath(path1);
        while (follower.isBusy() && !isStopRequested()){
            follower.telemetryDebug(mTelemetry);
            follower.update();
        }


    }

}
