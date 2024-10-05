package org.firstinspires.ftc.teamcode.commandBased.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.commandBased.Subsystems.ExtensionSubsystem;

public class ExtensionOutCommand extends CommandBase {
    private ExtensionSubsystem extensionSubsystem;

    public ExtensionOutCommand(ExtensionSubsystem extensionSubsystem) {
        this.extensionSubsystem = extensionSubsystem;
        addRequirements(extensionSubsystem);
    }
}