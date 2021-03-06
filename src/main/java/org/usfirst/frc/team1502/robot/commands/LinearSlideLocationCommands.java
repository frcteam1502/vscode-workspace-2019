/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1502.robot.commands;

import org.usfirst.frc.team1502.robot.Robot;
import org.usfirst.frc.team1502.robot.subsystems.LinearSlide;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LinearSlideLocationCommands extends Command {
  LinearSlide.Level level;
  double upSpeed;

  public LinearSlideLocationCommands(LinearSlide.Level level, double upSpeed) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.level = level;
    requires(Robot.slide);
    this.upSpeed = upSpeed;
  }

  public LinearSlideLocationCommands(LinearSlide.Level level) {
    this.level = level;
    requires(Robot.slide);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.slide.moveTo(level);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.slide.centered;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.slide.hold();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
