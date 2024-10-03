package org.firstinspires.ftc.teamcode.OpModesAndTests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commandBased.Commands.AutoDriveCommand;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.AutoDriveSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.follower.Follower;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Pose;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Path;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.PathChain;

public abstract class FtcLibAutoBase extends CommandOpMode {
    protected Follower follower;
    protected Path path1;
    protected Path path2;
    protected Path path3;
    protected Pose pose1 = new Pose(20, -20, 0);
    protected Pose startPose = new Pose(0, 0, 0);
    protected PathChain pathChain;
    protected Telemetry mTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    protected AutoDriveSubsystem autoDriveSubsystem;
    protected AutoDriveCommand autoDriveCommand;

}
