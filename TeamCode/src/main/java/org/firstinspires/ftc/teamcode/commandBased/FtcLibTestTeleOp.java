package org.firstinspires.ftc.teamcode.commandBased;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.commandBased.Commands.DriveCommand;
import org.firstinspires.ftc.teamcode.commandBased.Commands.ImuCommands.ImuResetCommand;
import org.firstinspires.ftc.teamcode.commandBased.Commands.VisionCommands.LimelightCommand;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.ImuSubsystem;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.LimeLightSubsystem;


@TeleOp(name = "FtcLibTest")
public class FtcLibTestTeleOp extends CommandOpMode {
    private Motor frontLeft, frontRight, backLeft, backRight;
    private IMU imu;
    private ImuSubsystem imuSubsystem;
    private RevHubOrientationOnRobot orientationOnRobot;
    private RevHubOrientationOnRobot.UsbFacingDirection  usbDirection;
    private RevHubOrientationOnRobot.LogoFacingDirection logoDirection;
    private ImuResetCommand imuResetCommand;
    private DriveSubsystem driveSubsystem;
    private DriveCommand driveCommand;
    private Button aButton, bButton;
    private Limelight3A limelight;
    private LimeLightSubsystem limeLightSubsystem;
    private LimelightCommand limeLightCommand;

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
        imu = hardwareMap.get(IMU.class, "imu");
        logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.UP;
        usbDirection  = RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;
        orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);
        imuSubsystem = new ImuSubsystem(imu, orientationOnRobot, telemetry);
        driveSubsystem = new DriveSubsystem(frontLeft, frontRight, backLeft, backRight, telemetry);
        imuResetCommand = new ImuResetCommand(imuSubsystem, imu, telemetry);
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limeLightSubsystem = new LimeLightSubsystem(limelight, telemetry, 0);
        limeLightCommand = new LimelightCommand(limelight, limeLightSubsystem, telemetry, 0);
        aButton = (new GamepadButton(driverOp, GamepadKeys.Button.A))
                .whenPressed(imuResetCommand);//should reset Imu's yaw when a is pressed
        driveCommand = new DriveCommand(driveSubsystem, driverOp::getLeftX, driverOp::getLeftY, driverOp::getRightX , imuSubsystem::getImuYawDeg, telemetry);
        register(driveSubsystem, imuSubsystem, limeLightSubsystem);
        limeLightSubsystem.setDefaultCommand(limeLightCommand);
        driveSubsystem.setDefaultCommand(driveCommand);

    }
}
