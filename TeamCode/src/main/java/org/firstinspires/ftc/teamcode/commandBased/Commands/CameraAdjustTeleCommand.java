package org.firstinspires.ftc.teamcode.commandBased.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.commandBased.Subsystems.PedroDriveSubsystem;

public class CameraAdjustTeleCommand extends CommandBase {
    private PedroDriveSubsystem pedroDriveSubsystem;

    public CameraAdjustTeleCommand(PedroDriveSubsystem pedroDriveSubsystem) {
        this.pedroDriveSubsystem = pedroDriveSubsystem;
        addRequirements(pedroDriveSubsystem);
    }

    @Override
    public void execute() {
        pedroDriveSubsystem.cameraAdjust();
    }
    @Override
    public boolean isFinished(){
        return true;
    }

}
