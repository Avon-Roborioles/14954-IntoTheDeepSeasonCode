package org.firstinspires.ftc.teamcode.commandBased.Commands.ImuCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.commandBased.Subsystems.ImuSubsystem;


public class GetYawCommand extends CommandBase {
    private final ImuSubsystem imuSubsystem;
    private final IMU imu;

    public GetYawCommand(ImuSubsystem imuSubsystem, IMU imu){
        this.imuSubsystem = imuSubsystem;
        this.imu = imu;
        addRequirements(imuSubsystem);
    }
    public void initialize(){
        imuSubsystem.getImuValues();
    }

    @Override
    public void execute(){
        imuSubsystem.getImuYawDeg();
    }

}
