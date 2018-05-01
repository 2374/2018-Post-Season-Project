package org.usfirst.frc.team2374.robot.autonomous;

import org.usfirst.frc.team2374.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This class is part of the robot autonomous code, and makes
 * the robot move in a straight line.
 */

public class Direct extends CommandGroup {

	public Direct() {
		
		requires(Robot.drive);
		
	}
	
}
