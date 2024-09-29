package org.firstinspires.ftc.teamcode.AutoNoMoose;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.pedroPathing.localization.Pose;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Path;

@Autonomous(name="PedroAutoTest", group="Auto Tests")
public class PedroAutoTest extends AutoBase{
    private Path path1;
    private Path path2;
    private Path path3;

    public Pose pose1 = new Pose(20, 20, 0);

    private Pose startPose = new Pose(0, 0, 0);
    @Override
    public void runOpMode() throws InterruptedException {
        initializeAuto();

    }

}
