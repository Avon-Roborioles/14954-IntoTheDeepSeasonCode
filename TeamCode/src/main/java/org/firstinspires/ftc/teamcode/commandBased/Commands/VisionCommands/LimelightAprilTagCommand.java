package org.firstinspires.ftc.teamcode.commandBased.Commands.VisionCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.hardware.limelightvision.LLResult;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.LimelightSubsystem;

public class LimelightAprilTagCommand extends CommandBase {
    private LimelightSubsystem limelightSubsystem;
    private LLResult lastResult;

    public LimelightAprilTagCommand(LimelightSubsystem limelightSubsystem){
        this.limelightSubsystem = limelightSubsystem;
        addRequirements(limelightSubsystem);
    }
    @Override
    public void execute(){
        lastResult = limelightSubsystem.readAprilTag();
        limelightSubsystem.setPipeline(0);
    }

}
