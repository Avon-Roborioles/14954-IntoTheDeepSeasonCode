package org.firstinspires.ftc.teamcode.OpModesAndTests;

import static com.sun.tools.doclint.Entity.Pi;
import static java.lang.Math.PI;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.ParallelRaceGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commandBased.Commands.AutoDriveCommand;
import org.firstinspires.ftc.teamcode.commandBased.Commands.AutoSetStartCommand;
import org.firstinspires.ftc.teamcode.commandBased.Commands.VisionCommands.CameraAjustCommand;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.AutoDriveSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.follower.Follower;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Pose;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierCurve;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Path;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.PathChain;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Point;

@Autonomous(name = "Auto1")
public class FtcLibAuto1 extends FtcLibAutoBase {
    @Override
    public void initialize() {

        Pose scanPose = new Pose(-48, -50, 0);
        Pose startPose = new Pose(-48, -64.5, 0);
        Pose scorePose = new Pose(-55, -55, PI/4);
        Pose firstPose = new Pose(-48, -32, PI/2);
        Pose secondPose = new Pose(-55, -26, PI/2);
        Pose thirdPose = new Pose(-55, -32, PI);

        Path toScan;
        Path toFirst;
        Path fromFirst;
        Path toSecond;
        Path fromSecond;
        Path toThird;
        Path fromThird;


        toScan = new Path((new BezierCurve(new Point(startPose), new Point(scanPose))));
        toScan.setLinearHeadingInterpolation(startPose.getHeading(), scanPose.getHeading());
        toFirst = new Path((new BezierCurve(new Point(startPose), new Point(firstPose))));
        toFirst.setLinearHeadingInterpolation(startPose.getHeading(), firstPose.getHeading());
        toFirst.setPathEndTimeoutConstraint(6000);
        fromFirst = new Path((new BezierCurve(new Point(firstPose), new Point(scorePose))));
        fromFirst.setLinearHeadingInterpolation(firstPose.getHeading() , scorePose.getHeading());
        fromFirst.setPathEndTimeoutConstraint(3000);
        toSecond = new Path((new BezierCurve(new Point(scorePose), new Point(secondPose))));
        toSecond.setLinearHeadingInterpolation(scorePose.getHeading(), secondPose.getHeading());
        toSecond.setPathEndTimeoutConstraint(3000);
        fromSecond = new Path((new BezierCurve(new Point(secondPose), new Point(scorePose))));
        fromSecond.setLinearHeadingInterpolation(secondPose.getHeading(), scorePose.getHeading());
        fromSecond.setPathEndTimeoutConstraint(3000);
        toThird = new Path((new BezierCurve(new Point(scorePose), new Point(thirdPose))));
        toThird.setLinearHeadingInterpolation(scorePose.getHeading(), thirdPose.getHeading());
        toThird.setPathEndTimeoutConstraint(3000);
        fromThird = new Path((new BezierCurve(new Point(thirdPose), new Point(scorePose))));
        fromThird.setLinearHeadingInterpolation(thirdPose.getHeading(), scorePose.getHeading());
        fromThird.setPathEndTimeoutConstraint(3000);


        follower = new Follower(hardwareMap, telemetry);

        AutoSetStartCommand autoSetStartCommand = new AutoSetStartCommand(startPose, follower);

        autoDriveSubsystem = new AutoDriveSubsystem(follower, mTelemetry, startPose);
        autoDriveSubsystem.setMaxPower(1);

        autoDriveCommand = new AutoDriveCommand(autoDriveSubsystem, mTelemetry);
        cameraAjustCommand = new CameraAjustCommand(autoDriveSubsystem);

        register(autoDriveSubsystem);
        Command setPathToScan = new InstantCommand(() -> {
            autoDriveSubsystem.followPath(toScan, true);
            autoDriveSubsystem.setMaxPower(0.25);
        });
        Command setPathToFirst = new InstantCommand(() -> {
            autoDriveSubsystem.followPath(toFirst, true);
//            autoDriveSubsystem.setMaxPower(0.5);
        });
        Command setPathFromFirst = new InstantCommand(() -> {
            autoDriveSubsystem.followPath(fromFirst, true);
            autoDriveSubsystem.setMaxPower(1);
        });
        Command setPathToSecond = new InstantCommand(() -> {
            autoDriveSubsystem.followPath(toSecond, true);
        });
        Command setPathFromSecond = new InstantCommand(() -> {
            autoDriveSubsystem.followPath(fromSecond, true);
        });
        Command setPathToThird = new InstantCommand(() -> {
            autoDriveSubsystem.followPath(toThird, true);
        });
        Command setPathFromThird = new InstantCommand(() -> {
            autoDriveSubsystem.followPath(fromThird, true);
        });

        Command camera = new ParallelCommandGroup(new WaitCommand(5000), cameraAjustCommand);


        schedule(new SequentialCommandGroup(
                autoSetStartCommand,
//                setPathToScan, autoDriveCommand,
//                camera,
                setPathToFirst, autoDriveCommand,
                setPathFromFirst, autoDriveCommand,
                setPathToSecond, autoDriveCommand,
                setPathFromSecond, autoDriveCommand,
                setPathToThird, autoDriveCommand,
                setPathFromThird, autoDriveCommand
        ));
//        schedule(autoDriveCommand);

    }
}
