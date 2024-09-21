package org.firstinspires.ftc.teamcode.commandBased.Commands;


import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.commandBased.Subsystems.ImuSubsystem;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.LocalizerSubsystem;

public class LocalizerCommand extends CommandBase {
    private ImuSubsystem imuSubsystem;
    private LocalizerSubsystem localizerSubsystem;

    public LocalizerCommand(ImuSubsystem imuSubsystem, LocalizerSubsystem localizerSubsystem){
        this.imuSubsystem = imuSubsystem;
        this.localizerSubsystem = localizerSubsystem;
        addRequirements(imuSubsystem, localizerSubsystem);
    }
    @Override
    public void execute(){
        localizerSubsystem.getImuHeading();
    }
}
