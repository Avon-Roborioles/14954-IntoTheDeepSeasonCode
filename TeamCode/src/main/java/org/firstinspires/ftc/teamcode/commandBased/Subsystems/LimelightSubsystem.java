package org.firstinspires.ftc.teamcode.commandBased.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

public class LimelightSubsystem extends SubsystemBase {
    private Limelight3A limelight;
    private Telemetry telemetry;
    private LLResult result;
    private final int startingPipeline = 0;
    public LimelightSubsystem(Limelight3A limelight, Telemetry telemetry){
        this.limelight = limelight;
        limelight.pipelineSwitch(startingPipeline);
        limelight.start();
        this.telemetry = telemetry;
    }
    public LLResult readAprilTag(){
        getResult();
        if (result != null) {
            if (result.isValid()) {
                Pose3D botpose = result.getBotpose();
                telemetry.addData("tx", result.getTx());
                telemetry.addData("ty", result.getTy());
                telemetry.addData("Botpose", botpose.toString());
                telemetry.addData("tags", result.getBotposeTagCount());
                return result;
            }
        }else {
            telemetry.addLine("null result");
            return null;
        }
        return result;
    }
    public void getLimelightTelemetry(){
        readAprilTag();
        if (result != null) {
            if (result.isValid()) {
                Pose3D botpose = result.getBotpose();
                telemetry.addData("tx", result.getTx());
                telemetry.addData("ty", result.getTy());
                telemetry.addData("Botpose", botpose.toString());
                telemetry.addData("tags", result.getBotposeTagCount());
            }
        }
    }
    public void getResult(){
        result = limelight.getLatestResult();
    }
    public void setPipeline(int pipeline){
        limelight.stop();
        limelight.pipelineSwitch(pipeline);
        limelight.start();
    }
}