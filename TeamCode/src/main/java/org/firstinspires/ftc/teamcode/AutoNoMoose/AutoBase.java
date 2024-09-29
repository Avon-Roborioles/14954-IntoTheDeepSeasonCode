package org.firstinspires.ftc.teamcode.AutoNoMoose;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.pedroPathing.follower.Follower;

public abstract class AutoBase extends LinearOpMode {
    protected Follower follower;
    protected Telemetry telemetry;

    public void initializeAuto() {
        follower = new Follower(hardwareMap);
        telemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());

    }
    public void runOpMode() throws InterruptedException {
        //
    }
}
