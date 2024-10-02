package org.firstinspires.ftc.teamcode.OpModesAndTests;

import static java.lang.Math.PI;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.pedroPathing.follower.Follower;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Pose;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierCurve;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Path;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.PathChain;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Point;

@Autonomous(name = "Auto1 *NotYetDone")
public class FtcLibAuto1 extends FtcLibAutoBase {
    @Override
    public void initialize() {
        follower = new Follower(hardwareMap, telemetry);
        follower.initialize();
        follower.setMaxPower(1);
        follower.setStartingPose(startPose);
        path1 = new Path((new BezierCurve(new Point(startPose), new Point(pose1), new Point(new Pose(40,-20, 0)))));
        path1.setConstantHeadingInterpolation(0);
        path1.setPathEndTimeoutConstraint(3000);
        path2 = new Path((new BezierCurve(new Point(new Pose(40,-20, 0)), new Point(pose1), new Point(new Pose(0,0, 0)))));
        path2.setLinearHeadingInterpolation(0, PI/2);
        path2.setPathEndTranslationalConstraint(0.125);
        path2.setPathEndTimeoutConstraint(6000);
        pathChain = new PathChain(path1, path2);


        follower.followPath(pathChain);
        while (follower.isBusy() && !isStopRequested()){
            follower.update();
            follower.telemetryDebug(mTelemetry);
        }
    }
}
