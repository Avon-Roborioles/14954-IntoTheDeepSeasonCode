package org.firstinspires.ftc.teamcode.commandBased.Commands.VisionCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.LimeLightSubsystem;

public class LimelightCommand extends CommandBase {
    private Limelight3A limelight;
    private LimeLightSubsystem limeLightSubsystem;
    private double pipeline;
    private Telemetry telemetry;
    private LLResult lastResult;

    public LimelightCommand(Limelight3A limelight, LimeLightSubsystem limeLightSubsystem, Telemetry telemetry, double pipeline){
        this.limelight = limelight;
        this.limeLightSubsystem = limeLightSubsystem;
        this.telemetry = telemetry;
        this.pipeline = pipeline;
        addRequirements(limeLightSubsystem);
    }
    @Override
    public void execute(){
        lastResult = limeLightSubsystem.readAprilTag();
        telemetry.addData("lastResult", lastResult);
        telemetry.update();
    }

}
