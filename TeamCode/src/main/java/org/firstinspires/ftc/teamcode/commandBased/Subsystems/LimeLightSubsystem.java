package org.firstinspires.ftc.teamcode.commandBased.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

public class LimeLightSubsystem extends SubsystemBase {
    private Limelight3A limelight;
    private Telemetry telemetry;
    private LLResult result;
    public LimeLightSubsystem(Limelight3A limeLight, Telemetry telemetry, int pipeline){
        this.limelight = limeLight;
        limeLight.pipelineSwitch(pipeline);
        limeLight.start();
        this.telemetry = telemetry;
    }
    public void readAprilTagToTelemetry(){
        getResult();
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
}
