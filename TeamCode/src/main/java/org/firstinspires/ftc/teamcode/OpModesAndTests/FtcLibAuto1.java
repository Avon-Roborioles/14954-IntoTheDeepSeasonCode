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
        follower = new Follower(hardwareMap, telemetry);
        autoDriveSubsystem = new AutoDriveSubsystem(follower, mTelemetry);
        autoDriveCommand = new AutoDriveCommand(autoDriveSubsystem, mTelemetry);
        autoDriveSubsystem.setStartingPose(new Pose(0, 0, PI/2));
        autoDriveSubsystem.setMaxPower(1);

        path1 = new Path((new BezierCurve(new Point(new Pose(0,0, PI/2)), new Point(new Pose(20,0, PI/2)))));
        path1.setLinearHeadingInterpolation(0, PI/2);
        path1.setPathEndTimeoutConstraint(3000);

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
