package org.firstinspires.ftc.teamcode.OpModesAndTests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

@TeleOp(name = "Sensor: Color", group = "Sensor")
public class colorTestGemini extends LinearOpMode {

    ColorSensor colorSensor;

    @Override
    public void runOpMode() {

        colorSensor = hardwareMap.get(ColorSensor.class, "color");

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Red  ", colorSensor.red());
            telemetry.addData("Green", colorSensor.green());
            telemetry.addData("Blue ", colorSensor.blue());
            if(colorSensor.red()>colorSensor.blue() && colorSensor.red()>colorSensor.green()) {
                telemetry.addData("Color", "Red");
            } else if(colorSensor.blue()>colorSensor.red() && colorSensor.blue()>colorSensor.green()) {
                telemetry.addData("Color", "Blue");
            } else if(colorSensor.red()>colorSensor.blue() && colorSensor.green()>colorSensor.blue()) {
                telemetry.addData("Color", "Yellow");
            } else {
                telemetry.addData("Color", "Unknown");
            }
            telemetry.update();
        }
    }
}