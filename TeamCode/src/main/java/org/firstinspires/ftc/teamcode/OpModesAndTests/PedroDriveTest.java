package org.firstinspires.ftc.teamcode.OpModesAndTests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Examples.GobuildaSample.GoBildaPinpointDriver;
import org.firstinspires.ftc.teamcode.commandBased.Commands.CameraAdjustTeleCommand;
import org.firstinspires.ftc.teamcode.commandBased.Commands.DriveCommand;
import org.firstinspires.ftc.teamcode.commandBased.Commands.ExtensionOutCommand;
import org.firstinspires.ftc.teamcode.commandBased.Commands.LocalizerCommand;
import org.firstinspires.ftc.teamcode.commandBased.Commands.TelePedroDriveCommand;
import org.firstinspires.ftc.teamcode.commandBased.Commands.TelemetryCommand;
import org.firstinspires.ftc.teamcode.commandBased.Commands.VisionCommands.LimelightAprilTagCommand;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.LimelightSubsystem;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.LocalizerSubsystem;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.OdometrySubsystem;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.PedroDriveSubsystem;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.TelemetrySubsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.follower.Follower;


@TeleOp(name = "26 Practice")
public class PedroDriveTest extends CommandOpMode {
    private Motor leftFront, rightFront, leftRear, rightRear;

    private Follower follower;

    private PedroDriveSubsystem pedroDriveSubsystem;
    private TelePedroDriveCommand telePedroDriveCommand;
    private CameraAdjustTeleCommand cameraAdjustTeleCommand;

    private GamepadEx driverOp;
    private Button a;

    @Override
    public void initialize(){
        follower = new Follower(hardwareMap);

        leftFront = new Motor(hardwareMap, "frontLeft");
        leftRear = new Motor(hardwareMap, "frontRight");
        rightRear = new Motor(hardwareMap, "backLeft");
        rightFront = new Motor(hardwareMap, "backRight");
        leftFront.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        driverOp = new GamepadEx(gamepad1);

        follower.startTeleopDrive();
        follower.setMaxPower(1);
        follower.startTeleopDrive();

        pedroDriveSubsystem = new PedroDriveSubsystem( follower);
        telePedroDriveCommand = new TelePedroDriveCommand(pedroDriveSubsystem, telemetry, driverOp::getLeftY, driverOp::getLeftX, driverOp::getRightX, true);
        cameraAdjustTeleCommand = new CameraAdjustTeleCommand(pedroDriveSubsystem);

//        a = (new GamepadButton(driverOp, GamepadKeys.Button.A))
//                .whenPressed(cameraAdjustTeleCommand);
        register(pedroDriveSubsystem, pedroDriveSubsystem);

        pedroDriveSubsystem.setDefaultCommand(telePedroDriveCommand);
    }
}
