package org.firstinspires.ftc.teamcode.commandBased.Commands.VisionCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.commandBased.Subsystems.AutoDriveSubsystem;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.LocalizerSubsystem;

public class CameraAjustCommand extends CommandBase {
    private AutoDriveSubsystem autoDriveSubsystem;
    public CameraAjustCommand(AutoDriveSubsystem autoDriveSubsystem){
        this.autoDriveSubsystem = autoDriveSubsystem;
        addRequirements(autoDriveSubsystem);
    }
    @Override
    public void execute(){
//        autoDriveSubsystem.cameraAjust();
    }
    @Override
    public boolean isFinished(){
        return true;
    }
}
