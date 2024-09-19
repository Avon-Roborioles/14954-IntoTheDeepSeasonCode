package org.firstinspires.ftc.teamcode.commandBased.Commands.ImuCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.ImuSubsystem;

public class ImuResetCommand extends CommandBase {
    private final ImuSubsystem imuSubsystem;
    private final IMU imu;
    private Telemetry telemetry;
    public ImuResetCommand(ImuSubsystem imuSubsystem, IMU imu, Telemetry telemetry){
        this.telemetry = telemetry;
        this.imuSubsystem = imuSubsystem;
        this.imu = imu;
        addRequirements(imuSubsystem);
    }
    @Override
    public void initialize(){
        imuSubsystem.resetYaw();
    }
    @Override
    public boolean isFinished() {
        return true;
    }
}
