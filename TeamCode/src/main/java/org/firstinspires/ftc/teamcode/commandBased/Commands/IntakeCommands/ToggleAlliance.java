package org.firstinspires.ftc.teamcode.commandBased.Commands.IntakeCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.commandBased.Subsystems.IntakeSubsystem;

public class ToggleAlliance extends CommandBase {
    private IntakeSubsystem subsystem;

    public ToggleAlliance(IntakeSubsystem subsystem) {
        this.subsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        subsystem.changeAlliance();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
