package org.firstinspires.ftc.teamcode.commandBased.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.AutoDriveSubsystem;

import java.util.function.DoubleSupplier;

public class TelePedroDriveCommand extends CommandBase {
    private AutoDriveSubsystem autoDriveSubsystem;
    private Telemetry telemetry;
    private DoubleSupplier strafe, forward, turn;
    private boolean fieldCentric;

    public TelePedroDriveCommand(AutoDriveSubsystem autoDriveSubsystem, Telemetry telemetry, DoubleSupplier turn, DoubleSupplier strafe, DoubleSupplier forward, boolean fieldCentric){
        this.autoDriveSubsystem = autoDriveSubsystem;
        this.telemetry = telemetry;
        this.strafe = strafe;
        this.forward = forward;
        this.turn = turn;
        this.fieldCentric = fieldCentric;
        this.autoDriveSubsystem.startTeleopDrive();
        addRequirements(autoDriveSubsystem);
    }
    @Override
    public void execute(){
        autoDriveSubsystem.setTeleOpMovementVectors(forward.getAsDouble(), strafe.getAsDouble(), -turn.getAsDouble(), true);
        autoDriveSubsystem.update();
    }

}
