package org.firstinspires.ftc.teamcode.commandBased.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.commandBased.Subsystems.ImuSubsystem;


public class ImuCommand extends CommandBase {
    private final ImuSubsystem imuSubsystem;
    private final IMU imu;
    private final HardwareMap hardwareMap;

    public ImuCommand(ImuSubsystem imuSubsystem, IMU imu, HardwareMap hardwareMap ){
        this.imuSubsystem = imuSubsystem;
        this.imu = imu;
        this.hardwareMap = hardwareMap;
        addRequirements(imuSubsystem);
    }
    public void initialize(HardwareMap hardwareMap){
        imuSubsystem.getImuValues();
    }
}
