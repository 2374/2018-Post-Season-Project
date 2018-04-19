package org.usfirst.frc.team2374.robot.subsystems;

import org.usfirst.frc.team2374.robot.RobotMap;
import org.usfirst.frc.team2374.robot.commands.DrivetrainTeleop;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain {
	
	private TalonSRX frontLeft, midLeft, backLeft, frontRight, midRight, backRight;
	
	public Drivetrain(){
		//Create Talon variables in memory
		frontLeft = new TalonSRX(RobotMap.TALON_DRIVE_FRONT_LEFT);
		midLeft = new TalonSRX(RobotMap.TALON_DRIVE_MIDDLE_LEFT);
		backLeft = new TalonSRX(RobotMap.TALON_DRIVE_BACK_LEFT);
		frontRight = new TalonSRX(RobotMap.TALON_DRIVE_FRONT_RIGHT);
		midRight = new TalonSRX(RobotMap.TALON_DRIVE_MIDDLE_RIGHT);
		backRight = new TalonSRX(RobotMap.TALON_DRIVE_BACK_RIGHT);
		
		//Set the front and back Talons to do whatever their respective middle is doing
		frontLeft.follow(midLeft);
		backLeft.follow(midLeft);
		
		frontRight.follow(midRight);
		backRight.follow(midRight);
		
		//Setting to determine whether or not the left motors need to run in reverse or not
		midLeft.setInverted(false);
		frontLeft.setInverted(false);
		backLeft.setInverted(false);
	}
	
	public void initDefaultCommand(){ setDefaultCommand(new DrivetrainTeleop()); }
	
}
