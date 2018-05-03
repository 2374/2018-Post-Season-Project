package org.usfirst.frc.team2374.robot.autonomous;

import org.usfirst.frc.team2374.robot.Robot;
import org.usfirst.frc.team2374.robot.commands.DriveToInch;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This class is part of the robot autonomous code, and makes
 * the robot move in a straight line.
 */

public class DriveStraight extends CommandGroup {

	public DriveStraight() {
		
		requires(Robot.drive);
		
		addSequential(new DriveToInch(80));
		
	}
	
}
