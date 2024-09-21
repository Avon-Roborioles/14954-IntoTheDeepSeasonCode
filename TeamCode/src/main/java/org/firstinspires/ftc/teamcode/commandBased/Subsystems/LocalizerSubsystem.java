package org.firstinspires.ftc.teamcode.commandBased.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LocalizerSubsystem extends SubsystemBase {
    private Telemetry telemetry;
    private ImuSubsystem imuSubsystem;

    public LocalizerSubsystem(Telemetry telemetry, ImuSubsystem imuSubsystem){
        this.telemetry = telemetry;
        this.imuSubsystem = imuSubsystem;

    }
    public double getImuHeading(){
        return imuSubsystem.getImuYawDeg();
    }
    public void getLocalizerTelemetry(){
        telemetry.addData("Localizer Heading", getImuHeading());
    }
}
