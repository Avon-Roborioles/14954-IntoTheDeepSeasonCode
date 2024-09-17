package org.firstinspires.ftc.teamcode.commandBased.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class ImuSubsystem extends SubsystemBase {
    private IMU imu;
    private Telemetry telemetry;
    public ImuSubsystem(IMU imu1, HardwareMap hardwareMap, Telemetry telemetry){
        imu = imu1;
        imu = hardwareMap.get(IMU.class, "imu");
        this.telemetry = telemetry;
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.UP;
        RevHubOrientationOnRobot.UsbFacingDirection  usbDirection  = RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;
        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);
        imu.initialize(new IMU.Parameters(orientationOnRobot));
        telemetry.addLine("ImuInit");
        telemetry.update();
    }
    public YawPitchRollAngles getImuValues(){
        return imu.getRobotYawPitchRollAngles();
    }
    public double getImuYawDeg(){
        telemetry.addData("yaw" ,getImuValues().getYaw(AngleUnit.DEGREES));
        telemetry.update();
        return getImuValues().getYaw(AngleUnit.DEGREES);
    }

}
