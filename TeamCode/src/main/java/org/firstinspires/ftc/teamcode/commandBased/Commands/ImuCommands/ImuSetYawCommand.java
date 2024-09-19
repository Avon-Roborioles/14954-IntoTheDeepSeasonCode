package org.firstinspires.ftc.teamcode.commandBased.Commands.ImuCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.ImuSubsystem;

public class ImuSetYawCommand extends CommandBase {
    private ImuSubsystem imuSubsystem;
    private ImuResetCommand imuResetCommand;
    private IMU imu;
    private double currentYaw;
    private Telemetry telemetry;
    public ImuSetYawCommand(ImuSubsystem imuSubsystem,IMU imu, ImuResetCommand imuResetCommand, double currentYaw, Telemetry telemetry){
        this.imuSubsystem = imuSubsystem;
        this.imu = imu;
        this.imuResetCommand = imuResetCommand;
        this.currentYaw = currentYaw;
        this.telemetry = telemetry;
    }
    @Override
    public void execute(){
        telemetry.addData("current Yaw", currentYaw);
        telemetry.addLine("test");
        telemetry.update();
    }
    @Override
    public boolean isFinished() {
        return true;
    }
}
