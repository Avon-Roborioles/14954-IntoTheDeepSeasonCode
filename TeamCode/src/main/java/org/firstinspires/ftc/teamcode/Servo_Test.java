package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commandBased.Commands.Servo_CommandRun;
import org.firstinspires.ftc.teamcode.commandBased.Commands.Servo_CommandStop;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.ServoSubsystem;


@TeleOp(name="Servo Test OpMode")
public class Servo_Test extends LinearOpMode {

    private ServoSubsystem m_servoSubsystem;
    private Servo_CommandRun m_runServoCommand;
    private Servo_CommandStop m_stopServoCommand;
    private RevTouchSensor toucher;


    public void initialize() {
        m_servoSubsystem = new ServoSubsystem(hardwareMap, "Test", "Touch", "color"); // Replace with actual device names
        m_runServoCommand = new Servo_CommandRun(m_servoSubsystem);

//


    }




    @Override
    public void runOpMode() throws InterruptedException {
        m_runServoCommand.schedule();
//        m_runServoCommand.execute();
//        schedule(new RunCommand(m_servoSubsystem::servorunf, m_servoSubsystem));
    }
}









// Servo= new ServoSubsystem(hardwareMap, "Test", "Touch","color" );