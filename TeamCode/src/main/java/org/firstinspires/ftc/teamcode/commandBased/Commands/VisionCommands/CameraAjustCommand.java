package org.firstinspires.ftc.teamcode.commandBased.Commands.VisionCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.hardware.limelightvision.LLResult;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.AutoDriveSubsystem;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.LimelightSubsystem;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.LocalizerSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Pose;

public class CameraAjustCommand extends CommandBase {
    private AutoDriveSubsystem autoDriveSubsystem;
    private LimelightSubsystem limelightSubsystem;
    private LLResult result;
    public CameraAjustCommand(AutoDriveSubsystem autoDriveSubsystem, LimelightSubsystem limelightSubsystem){
        this.autoDriveSubsystem = autoDriveSubsystem;
        this.limelightSubsystem = limelightSubsystem;
        addRequirements(autoDriveSubsystem);
    }
    @Override
    public void execute(){
        result = limelightSubsystem.readAprilTag();
        if (result == null) {
            autoDriveSubsystem.setPose(new Pose(result.getTx(), result.getTy(), result.getBotpose().getOrientation().getYaw(AngleUnit.RADIANS)));
        }
    }
    @Override
    public boolean isFinished(){
        return true;
    }
}
