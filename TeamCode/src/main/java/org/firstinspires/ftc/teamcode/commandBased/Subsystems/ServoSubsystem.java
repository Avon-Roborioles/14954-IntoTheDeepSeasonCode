package org.firstinspires.ftc.teamcode.commandBased.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Call_Upon_Classes.Color_Sensor;

public class ServoSubsystem extends SubsystemBase {
    private CRServo testservo;
    private final double servospeed = 1;
    private RevTouchSensor toucher;
    private RevColorSensorV3 coloreader;
    public ServoSubsystem(final HardwareMap hardwareMap, final String name, final String name1, final String name2) {
        testservo = hardwareMap.get(CRServo.class, name);
        toucher = hardwareMap.get(RevTouchSensor.class, name1);
        coloreader = hardwareMap.get(RevColorSensorV3.class, name2);
//        public boolean touchsensor() {
//            if (toucher.isPressed()) {
//                return true;
//            } else {
//                return false;
//            }
//        }
//
//        public void servorunb(){
//            testservo.setPower(-servospeed);
//        }
//
//
//
//        public void servorunf(){
//            testservo.setPower(servospeed);
//        }

//        public void servostop(){
//            testservo.setPower(0);
//        }
    }

}
