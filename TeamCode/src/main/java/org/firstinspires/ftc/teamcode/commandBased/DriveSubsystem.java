package org.firstinspires.ftc.teamcode.commandBased;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;

public class DriveSubsystem extends SubsystemBase {
    private MecanumDrive drive;
    private Motor frontLeft, frontRight, backLeft, backRight;
    public DriveSubsystem(Motor frontL, Motor frontR, Motor backL, Motor backR){
        frontLeft = frontL;
        frontRight = frontR;
        backLeft = backL;
        backRight = backR;
        drive = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);
    }
    public void drive(double strafeSpeed, double forwardSpeed, double turnSpeed){
//        drive.driveRobotCentric(strafeSpeed, forwardSpeed, turnSpeed);
    }
}
