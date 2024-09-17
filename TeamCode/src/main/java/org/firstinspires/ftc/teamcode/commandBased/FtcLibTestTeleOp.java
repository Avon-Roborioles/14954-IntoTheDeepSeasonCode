package org.firstinspires.ftc.teamcode.commandBased;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.commandBased.Commands.DriveCommand;
import org.firstinspires.ftc.teamcode.commandBased.Commands.ImuCommand;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.ImuSubsystem;


@TeleOp(name = "FtcLibTest")
public class FtcLibTestTeleOp extends CommandOpMode {
    private Motor frontLeft, frontRight, backLeft, backRight;
    private IMU imu;
    private ImuSubsystem imuSubsystem;

    private DriveSubsystem driveSubsystem;
    private DriveCommand driveCommand;

    private GamepadEx driverOp, operatorOp;
    @Override
    public void initialize(){
        frontLeft = new Motor(hardwareMap, "frontLeft");
        frontRight = new Motor(hardwareMap, "frontRight");
        backLeft = new Motor(hardwareMap, "backLeft");
        backRight = new Motor(hardwareMap, "backRight");
        frontLeft.setInverted(true);
        frontRight.setInverted(true);
        backLeft.setInverted(true);
        backRight.setInverted(true);
        driverOp = new GamepadEx(gamepad1);
        operatorOp = new GamepadEx(gamepad2);
        imuSubsystem = new ImuSubsystem(imu,hardwareMap, telemetry);
//        imuCommand = new ImuCommand(imuSubsystem, imu, hardwareMap); // not used for this case
        driveSubsystem = new DriveSubsystem(frontLeft, frontRight, backLeft, backRight, telemetry);
        telemetry.addLine("test1");
        telemetry.update();
        driveCommand = new DriveCommand(driveSubsystem, driverOp::getLeftX, driverOp::getLeftY, driverOp::getRightX , imuSubsystem::getImuYawDeg, telemetry);
        telemetry.addLine("test2");
        telemetry.update();
        register(driveSubsystem, imuSubsystem);
        driveSubsystem.setDefaultCommand(driveCommand);
    }
}
