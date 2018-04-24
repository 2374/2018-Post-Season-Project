package org.usfirst.frc.team2374.robot.subsystems;

import org.usfirst.frc.team2374.robot.RobotMap;
import org.usfirst.frc.team2374.robot.commands.DrivetrainTeleop;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem {
	
	private TalonSRX frontLeft, midLeft, backLeft, frontRight, midRight, backRight;
	
	private AHRS navX;
	
	public Drivetrain(){
		
		// Create Talon variables in memory
		frontLeft = new TalonSRX(RobotMap.TALON_DRIVE_FRONT_LEFT);
		midLeft = new TalonSRX(RobotMap.TALON_DRIVE_MIDDLE_LEFT);
		backLeft = new TalonSRX(RobotMap.TALON_DRIVE_BACK_LEFT);
		frontRight = new TalonSRX(RobotMap.TALON_DRIVE_FRONT_RIGHT);
		midRight = new TalonSRX(RobotMap.TALON_DRIVE_MIDDLE_RIGHT);
		backRight = new TalonSRX(RobotMap.TALON_DRIVE_BACK_RIGHT);
		
		// Set the front and back Talons to do whatever their respective middle is doing
		frontLeft.follow(midLeft);
		backLeft.follow(midLeft);
		
		frontRight.follow(midRight);
		backRight.follow(midRight);
		
		// Setting to determine whether or not the left motors need to run in reverse or not
		midLeft.setInverted(false);
		frontLeft.setInverted(false);
		backLeft.setInverted(false);
		
		navX = new AHRS(SPI.Port.kMXP);
		navX.setPIDSourceType(PIDSourceType.kDisplacement);
		
	}

	public void initDefaultCommand(){ setDefaultCommand(new DrivetrainTeleop()); }
	
	/**
	 * 
	 * tankDrive sets speed for left and right side of
	 * drivetrain independently (like a tank), squares
	 * inputs (while preserving sign) to improve control
	 * while preserving top speed
	 * 
	 * @param leftValue The speed of the left drive.
	 * @param rightValue The speed of the right drive.
	 * 
	 */
	public void tankDrive(double leftValue, double rightValue) {
		
		// make sure input is capped at 1.0
		leftValue = limit(leftValue);
		rightValue = limit(rightValue);
	    // set left and right drive
	    midRight.set(ControlMode.PercentOutput, rightValue);
	    midLeft.set(ControlMode.PercentOutput, leftValue);
		
	}
	
	/**
	   * Arcade drive implements single stick driving. This function lets you directly provide
	   * joystick values from any source.
	   *
	   * @param moveValue The value to use for forwards/backwards
	   * @param rotateValue The value to use for the rotate right/left
	   */
	public void arcadeDrive(double moveValue, double rotateValue) {
	    double leftMotorSpeed;
	    double rightMotorSpeed;
	    // cap values at -1.0 to 1.0
	    moveValue = limit(moveValue);
	    rotateValue = limit(rotateValue);
	    
	    if (moveValue > 0.0) {
	    	if (rotateValue > 0.0) {
	    		leftMotorSpeed = moveValue - rotateValue;
	    		rightMotorSpeed = Math.max(moveValue, rotateValue);
	    	} else {
	    		leftMotorSpeed = Math.max(moveValue, -rotateValue);
	    		rightMotorSpeed = moveValue + rotateValue;
	    	}
	    } else {
	    	if (rotateValue > 0.0) {
	    		leftMotorSpeed = -Math.max(-moveValue, rotateValue);
	    		rightMotorSpeed = moveValue + rotateValue;
	    	} else {
	    		leftMotorSpeed = moveValue - rotateValue;
	    		rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
	    	}
	    }
	    midRight.set(ControlMode.PercentOutput, leftMotorSpeed);
	    midLeft.set(ControlMode.PercentOutput, rightMotorSpeed);
	    
	}
	
	/**
	 * Called when reseting gyro (for example before executing TurnToAngle())
	 */
	public void resetGyro() { navX.reset(); }
	
	/**
	 * Limits the input to between -1.0 and 1.0.
	 * @param num Original input value.
	 * @return Returns modified/limited number.
	 */
	private double limit(double num) { return Math.max(Math.min(num, 1.0), -1.0); }
	
}
