package org.firstinspires.ftc.teamcode.commandBased.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.pedroPathing.follower.Follower;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Pose;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Path;

public class AutoDriveSubsystem extends SubsystemBase {
    private Follower follower;
    private Path path;
    private Telemetry telemetry;

    public AutoDriveSubsystem(Follower follower, Path path, Telemetry telemetry, Pose startPose){
        this.follower = follower;
        this.path = path;
        this.telemetry = telemetry;
        follower.setStartingPose(startPose);
    }

    public void followPath(){
        follower.followPath(path);
        follower.update();
    }

    public void setPath(Path path){
        this.path = path;
    }

    public void getFollowerTelemetry(){
        follower.telemetryDebug(telemetry);
    }
    public void setPosition(Pose pose){
        follower.setPose(pose);
    }


}
