package org.firstinspires.ftc.teamcode.OpModesAndTests;

import static com.sun.tools.doclint.Entity.Pi;
import static java.lang.Math.PI;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commandBased.Commands.AutoDriveCommand;
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
        Path path1;
        Path path2;
        Path path3;
        Pose pose1;
        Pose startPose = new Pose(-48, -64.5, PI);
        path1 = new Path((new BezierCurve(new Point(startPose), new Point(new Pose(-48,-26, PI/2)))));
        path1.setLinearHeadingInterpolation(PI, PI/2);
        path1.setPathEndTimeoutConstraint(3000);
        follower = new Follower(hardwareMap, telemetry);
        follower.setStartingPose(startPose);

        autoDriveSubsystem = new AutoDriveSubsystem(follower, mTelemetry, startPose);
        autoDriveSubsystem.setMaxPower(0.25);
        autoDriveCommand = new AutoDriveCommand(autoDriveSubsystem, mTelemetry);

        register(autoDriveSubsystem);
//        autoDriveCommand.setPathChain(pathChain, true);
        autoDriveCommand.setPath(path1, false);
//        Command setPath2 = new InstantCommand(() -> {
//            autoDriveSubsystem.followPath(path2, true);
//        });
//        Command setPath1 = new InstantCommand(() -> {
//            autoDriveSubsystem.followPath(path1, true);
//        });
//        schedule(autoDriveCommand,
//                new InstantCommand(() -> {
//                    autoDriveSubsystem.followPath(path2, true);
//                }),
//                autoDriveCommand
//        );
//        schedule(new SequentialCommandGroup( autoDriveCommand,
//                new InstantCommand(() -> {
//                    autoDriveSubsystem.followPath(path2, false);
//                }),
//                autoDriveCommand)
//
//        );
        schedule(autoDriveCommand);

    }
}
