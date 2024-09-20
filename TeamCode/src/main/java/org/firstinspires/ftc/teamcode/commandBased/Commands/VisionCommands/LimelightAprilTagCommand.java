package org.firstinspires.ftc.teamcode.commandBased.Commands.VisionCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.hardware.limelightvision.LLResult;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.LimelightSubsystem;

public class LimelightAprilTagCommand extends CommandBase {
    private LimelightSubsystem limelightSubsystem;
    private Telemetry telemetry;
    private LLResult lastResult;

    public LimelightAprilTagCommand(LimelightSubsystem limelightSubsystem, Telemetry telemetry){
        this.limelightSubsystem = limelightSubsystem;
        this.telemetry = telemetry;
        addRequirements(limelightSubsystem);
    }
    @Override
    public void execute(){
        lastResult = limelightSubsystem.readAprilTag();
        limelightSubsystem.setPipeline(0);
        telemetry.addData("lastResult", lastResult);
        telemetry.update();
    }

}
