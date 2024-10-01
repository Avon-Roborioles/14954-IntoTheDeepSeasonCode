package org.firstinspires.ftc.teamcode.OpModesAndTests;

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

import org.firstinspires.ftc.teamcode.Examples.GobuildaSample.GoBildaPinpointDriver;
import org.firstinspires.ftc.teamcode.commandBased.Commands.DriveCommand;
import org.firstinspires.ftc.teamcode.commandBased.Commands.ImuCommands.ImuResetCommand;
import org.firstinspires.ftc.teamcode.commandBased.Commands.LocalizerCommand;
import org.firstinspires.ftc.teamcode.commandBased.Commands.TelemetryCommand;
import org.firstinspires.ftc.teamcode.commandBased.Commands.VisionCommands.LimelightAprilTagCommand;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.ImuSubsystem;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.LimelightSubsystem;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.LocalizerSubsystem;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.OdometrySubsystem;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.TelemetrySubsystem;


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
    private LimelightSubsystem limelightSubsystem;
    private LimelightAprilTagCommand limeLightCommand;
    private LocalizerSubsystem localizerSubsystem;
    private LocalizerCommand localizerCommand;
    private TelemetrySubsystem telemetrySubsystem;
    private TelemetryCommand telemetryCommand;
    private OdometrySubsystem odometrySubsystem;
    private GoBildaPinpointDriver odometry;

    private GamepadEx driverOp, operatorOp;
    @Override
    public void initialize(){
        frontLeft = new Motor(hardwareMap, "frontLeft");
        frontRight = new Motor(hardwareMap, "frontRight");
        backLeft = new Motor(hardwareMap, "backLeft");
        backRight = new Motor(hardwareMap, "backRight");
        frontLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
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
        imuResetCommand = new ImuResetCommand(imuSubsystem);
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelightSubsystem = new LimelightSubsystem(limelight, telemetry);
        limeLightCommand = new LimelightAprilTagCommand(limelightSubsystem);
        odometry = hardwareMap.get(GoBildaPinpointDriver.class, "odometry");
        odometrySubsystem = new OdometrySubsystem(odometry,telemetry);
        localizerSubsystem = new LocalizerSubsystem(telemetry, limelightSubsystem, odometrySubsystem);
        localizerCommand = new LocalizerCommand(imuSubsystem, localizerSubsystem, limelightSubsystem, odometrySubsystem);
        driveSubsystem = new DriveSubsystem(frontLeft, frontRight, backLeft, backRight, telemetry);
        driveCommand = new DriveCommand(driveSubsystem, driverOp::getLeftX, driverOp::getLeftY, driverOp::getRightX , localizerSubsystem::getLocalizerHeadingTele);
        telemetrySubsystem = new TelemetrySubsystem(telemetry, imuSubsystem, limelightSubsystem, driveSubsystem, localizerSubsystem, odometrySubsystem);
        telemetryCommand= new TelemetryCommand(telemetrySubsystem);
        aButton = (new GamepadButton(driverOp, GamepadKeys.Button.A))
                .whenPressed(imuResetCommand);//should reset Imu's yaw when a is pressed
        bButton = (new GamepadButton(driverOp, GamepadKeys.Button.B))
                .toggleWhenPressed(limeLightCommand);
        register(driveSubsystem, imuSubsystem, limelightSubsystem, localizerSubsystem);
        localizerSubsystem.setDefaultCommand(localizerCommand);
        telemetrySubsystem.setDefaultCommand(telemetryCommand);
        driveSubsystem.setDefaultCommand(driveCommand);
    }
}
