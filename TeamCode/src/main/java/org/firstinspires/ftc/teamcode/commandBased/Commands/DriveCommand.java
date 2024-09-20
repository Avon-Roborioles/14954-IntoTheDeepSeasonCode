package org.firstinspires.ftc.teamcode.commandBased.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.DriveSubsystem;

import java.util.function.DoubleSupplier;

public class DriveCommand extends CommandBase {
    private DriveSubsystem driveSubsystem;
    private DoubleSupplier strafe, forward, turn, heading;

    public DriveCommand(DriveSubsystem driveSubsystem, DoubleSupplier strafe, DoubleSupplier forward, DoubleSupplier turn, DoubleSupplier heading){
        this.driveSubsystem = driveSubsystem;
        this.strafe = strafe;
        this.forward = forward;
        this.turn = turn;
        this.heading = heading;
        addRequirements(driveSubsystem);
    }
    @Override
    public void execute(){
        driveSubsystem.drive(strafe.getAsDouble(), forward.getAsDouble(), turn.getAsDouble(), heading.getAsDouble());
    }
}
