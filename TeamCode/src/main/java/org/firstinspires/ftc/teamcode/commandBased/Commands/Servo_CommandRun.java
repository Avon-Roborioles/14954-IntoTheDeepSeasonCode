package org.firstinspires.ftc.teamcode.commandBased.Commands;



import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.hardware.rev.RevTouchSensor;

import org.firstinspires.ftc.teamcode.commandBased.Subsystems.ServoSubsystem;

public class Servo_CommandRun extends CommandBase {

    private ServoSubsystem m_servoSubsystem;
    private boolean isfinished = false;
    private RevTouchSensor toucher;




    public Servo_CommandRun( final ServoSubsystem servoSubsystem) {
        m_servoSubsystem = servoSubsystem;
        addRequirements(servoSubsystem);


    }

    @Override

    public void execute() {
m_servoSubsystem.servorunf();
//        if (m_servoSubsystem.isTouchSensorPressed()) {
//            isfinished = true;
//
//        }
    }
    @Override
    public boolean isFinished () {
        if (m_servoSubsystem.isTouchSensorPressed()) {
            return true;
        } else {
            return false;
        }
        // Change this condition if you want the command to end automatically
    }
        @Override
        public void end ( boolean interrupted){
            m_servoSubsystem.servostop();
        }


        }



