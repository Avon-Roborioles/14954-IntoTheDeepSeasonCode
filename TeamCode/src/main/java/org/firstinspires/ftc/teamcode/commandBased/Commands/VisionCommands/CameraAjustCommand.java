package org.firstinspires.ftc.teamcode.commandBased.Commands.VisionCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.hardware.limelightvision.LLResult;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.AutoDriveSubsystem;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.LimelightSubsystem;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.LocalizerSubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Pose;

public class CameraAjustCommand extends CommandBase {
    private AutoDriveSubsystem autoDriveSubsystem;
    private LimelightSubsystem limelightSubsystem;
    private Telemetry telemetry;
    private LLResult result;
    public CameraAjustCommand(AutoDriveSubsystem autoDriveSubsystem, LimelightSubsystem limelightSubsystem, Telemetry telemetry){
        this.autoDriveSubsystem = autoDriveSubsystem;
        this.limelightSubsystem = limelightSubsystem;
        this.telemetry = telemetry;
        addRequirements(autoDriveSubsystem);
    }
    @Override
    public void execute(){
        limelightSubsystem.setPipeline(1);
        result = limelightSubsystem.readAprilTag();
        telemetry.addData("x", result.getBotpose().getPosition().x);
        telemetry.addData("y", result.getBotpose().getPosition().y);
        telemetry.addData("heading", result.getBotpose().getOrientation().getYaw(AngleUnit.DEGREES));
        telemetry.addData("valid", result.isValid());
        telemetry.update();
        if (result.isValid()) {
            autoDriveSubsystem.setPose(new Pose(result.getBotpose().getPosition().x, result.getBotpose().getPosition().y, result.getBotpose().getOrientation().getYaw(AngleUnit.RADIANS)));
        }
    }
    @Override
    public boolean isFinished(){
        return true;
    }
}
