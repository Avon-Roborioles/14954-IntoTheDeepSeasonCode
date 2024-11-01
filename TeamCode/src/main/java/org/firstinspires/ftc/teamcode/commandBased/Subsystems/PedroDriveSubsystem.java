package org.firstinspires.ftc.teamcode.commandBased.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.pedroPathing.follower.Follower;

public class PedroDriveSubsystem extends SubsystemBase {
    private Follower follower;
    private double speed;
    public PedroDriveSubsystem(double speed,Follower follower){
        this.speed = speed;
        this.follower = follower;
    }
    public void startTeleopDrive(){
        follower.startTeleopDrive();
        follower.setMaxPower(speed);
    }
    public void setTeleOpMovementVectors(double forward, double strafe, double turn){
        follower.setTeleOpMovementVectors(forward, strafe, turn);
    }
    public void setTeleOpMovementVectors(double forward, double strafe, double turn, boolean fieldCentric){
        follower.setTeleOpMovementVectors(forward, strafe, turn, !fieldCentric);
    }
    public void update(){
        follower.update();
    }
}
