package org.firstinspires.ftc.teamcode.commandBased.Commands;


import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.commandBased.Subsystems.ImuSubsystem;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.LimelightSubsystem;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.LocalizerSubsystem;
import org.firstinspires.ftc.teamcode.commandBased.Subsystems.OdometrySubsystem;

public class LocalizerCommand extends CommandBase {
    private ImuSubsystem imuSubsystem;
    private LocalizerSubsystem localizerSubsystem;
    private OdometrySubsystem odometrySubsystem;
    private LimelightSubsystem limelightSubsystem;

    public LocalizerCommand(ImuSubsystem imuSubsystem, LocalizerSubsystem localizerSubsystem, LimelightSubsystem limelightSubsystem, OdometrySubsystem odometrySubsystem){
        this.imuSubsystem = imuSubsystem;
        this.localizerSubsystem = localizerSubsystem;
        this.limelightSubsystem = limelightSubsystem;
        this.odometrySubsystem = odometrySubsystem;
        addRequirements(imuSubsystem, localizerSubsystem, odometrySubsystem, limelightSubsystem);
    }
    @Override
    public void execute(){
        localizerSubsystem.getLocalizerHeadingTele();
    }
}
