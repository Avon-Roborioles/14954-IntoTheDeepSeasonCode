package org.firstinspires.ftc.teamcode.commandBased;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commandBased.Subsystems.DriveSubsystem;


@TeleOp(name = "FtcLibTest")
public class FtcLibTestTeleOp extends CommandOpMode {
    private Motor frontLeft, frontRight, backLeft, backRight;
    private DriveSubsystem driveSubsystem;
    private DriveCommand driveCommand;

    private GamepadEx driverOp, operatorOp;
    @Override
    public void initialize(){
        frontLeft = new Motor(hardwareMap, "frontLeft");
        frontRight = new Motor(hardwareMap, "frontRight");
        backLeft = new Motor(hardwareMap, "backLeft");
        backRight = new Motor(hardwareMap, "backRight");

        driverOp = new GamepadEx(gamepad1);
        operatorOp = new GamepadEx(gamepad2);

        driveSubsystem = new DriveSubsystem(frontLeft, frontRight, backLeft, backRight);
        driveCommand = new DriveCommand(driveSubsystem, driverOp::getLeftX, driverOp::getLeftY, driverOp::getRightX);

        register(driveSubsystem);
        driveSubsystem.setDefaultCommand(driveCommand);
    }

    public void run() {

    }
}
