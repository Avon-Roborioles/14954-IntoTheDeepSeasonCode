package org.firstinspires.ftc.teamcode.commandBased.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class ImuSubsystem extends SubsystemBase {
    private IMU imu;
    private Telemetry telemetry;
    private RevHubOrientationOnRobot orientationOnRobot;
    public ImuSubsystem(IMU imu1, RevHubOrientationOnRobot orientationOnRobot, Telemetry telemetry){
        imu = imu1;
        this.orientationOnRobot = orientationOnRobot;
        this.telemetry = telemetry;
        imu.initialize(new IMU.Parameters(orientationOnRobot));
        telemetry.addLine("ImuInit");
        telemetry.update();
    }
    public YawPitchRollAngles getImuValues(){
        return imu.getRobotYawPitchRollAngles();
    }
    public double getImuYawDeg(){
        return getImuValues().getYaw(AngleUnit.DEGREES);
    }
    public void resetYaw(){
        imu.resetYaw();
    }
    public void getImuTelemetry(){
        telemetry.addData("yaw", getImuYawDeg());
    }

}
