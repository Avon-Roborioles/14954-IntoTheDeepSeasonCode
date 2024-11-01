package org.firstinspires.ftc.teamcode.OpModesAndTests;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Examples.GobuildaSample.GoBildaPinpointDriver;
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


@TeleOp(name = "pedroDrive")
public class PedroDriveTest extends CommandOpMode {
    private Motor frontLeft, frontRight, backLeft, backRight;
    private DcMotorEx extensionMotor;

    private DriveSubsystem driveSubsystem;
    private DriveCommand driveCommand;

    private Limelight3A limelight;
    private LimelightSubsystem limelightSubsystem;
    private LimelightAprilTagCommand limeLightCommand;

    private LocalizerSubsystem localizerSubsystem;
    private LocalizerCommand localizerCommand;

    private TelemetrySubsystem telemetrySubsystem;
    private TelemetryCommand telemetryCommand;

    private OdometrySubsystem odometrySubsystem;
    private GoBildaPinpointDriver odometry;
    private Telemetry mtelemetry;

    private ExtensionSubsystem extensionSubsystem;
    private ExtensionOutCommand extensionOutCommand;

    private Follower follower;
    private PedroDriveSubsystem pedroDriveSubsystem;
    private TelePedroDriveCommand telePedroDriveCommand;

    private GamepadEx driverOp, operatorOp;
    private Button aButton, bButton;
    private DcMotorEx leftFront;
    private DcMotorEx leftRear;
    private DcMotorEx rightFront;
    private DcMotorEx rightRear;

    @Override
    public void initialize(){
        leftFront = hardwareMap.get(DcMotorEx.class, "frontLeft" );
        leftRear = hardwareMap.get(DcMotorEx.class, "backLeft");
        rightRear = hardwareMap.get(DcMotorEx.class, "backRight");
        rightFront = hardwareMap.get(DcMotorEx.class, "frontRight");

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

//        frontLeft = new Motor(hardwareMap, "frontLeft");
//        frontRight = new Motor(hardwareMap, "frontRight");
//        backLeft = new Motor(hardwareMap, "backLeft");
//        backRight = new Motor(hardwareMap, "backRight");
//        frontLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
//        frontRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
//        backRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
//        backLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
//        frontLeft.setInverted(true);
//        frontRight.setInverted(true);
//        backLeft.setInverted(true);
//        backRight.setInverted(true);

//        extensionMotor = hardwareMap.get(DcMotorEx.class, "extensionMotor");
//
//        extensionSubsystem = new ExtensionSubsystem(extensionMotor);
//        extensionOutCommand = new ExtensionOutCommand(extensionSubsystem);

        driverOp = new GamepadEx(gamepad1);
        operatorOp = new GamepadEx(gamepad2);

//        limelight = hardwareMap.get(Limelight3A.class, "limelight");
//        limelightSubsystem = new LimelightSubsystem(limelight, telemetry);
//        limeLightCommand = new LimelightAprilTagCommand(limelightSubsystem);

//        odometry = hardwareMap.get(GoBildaPinpointDriver.class, "odometry");
//        odometrySubsystem = new OdometrySubsystem(odometry,telemetry, new Pose2D(DistanceUnit.INCH, 0, 0 , AngleUnit.RADIANS, 0));

//        localizerSubsystem = new LocalizerSubsystem(telemetry, limelightSubsystem, odometrySubsystem);
//        localizerCommand = new LocalizerCommand(localizerSubsystem, limelightSubsystem, odometrySubsystem);

        follower = new Follower(hardwareMap, telemetry);
        pedroDriveSubsystem = new PedroDriveSubsystem(1, follower);
        telePedroDriveCommand = new TelePedroDriveCommand(pedroDriveSubsystem, telemetry, driverOp::getLeftY, driverOp::getLeftX, driverOp::getRightX, false);

//        driveSubsystem = new DriveSubsystem(frontLeft, frontRight, backLeft, backRight, telemetry);
//        driveCommand = new DriveCommand(driveSubsystem, driverOp::getLeftX, driverOp::getLeftY, driverOp::getRightX , localizerSubsystem::getLocalizerHeadingTele, true);

//        mtelemetry = new  MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
//        telemetrySubsystem = new TelemetrySubsystem(mtelemetry, limelightSubsystem, driveSubsystem, localizerSubsystem, odometrySubsystem);
//        telemetryCommand= new TelemetryCommand(telemetrySubsystem);


//        bButton = (new GamepadButton(driverOp, GamepadKeys.Button.B))
//                .whenPressed(localizerCommand);


//        register(driveSubsystem, limelightSubsystem, localizerSubsystem, odometrySubsystem, extensionSubsystem, telemetrySubsystem, pedroDriveSubsystem);
        register(pedroDriveSubsystem);
//        telemetrySubsystem.setDefaultCommand(telemetryCommand);
//        localizerSubsystem.setDefaultCommand(localizerCommand);
//        extensionSubsystem.setDefaultCommand(extensionOutCommand);
        pedroDriveSubsystem.setDefaultCommand(telePedroDriveCommand);
//        driveSubsystem.setDefaultCommand(driveCommand);


    }
}
