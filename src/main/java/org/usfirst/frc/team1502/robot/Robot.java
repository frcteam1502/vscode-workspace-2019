/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1502.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc.team1502.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1502.robot.subsystems.HatchRelease;
import org.usfirst.frc.team1502.robot.subsystems.Intake;
import org.usfirst.frc.team1502.robot.subsystems.LinearSlide;
import org.usfirst.frc.team1502.robot.subsystems.PlatformLift;
import org.usfirst.frc.team1502.robot.subsystems.HorizontalSlide;
import org.usfirst.frc.team1502.robot.subsystems.Sonar;
import org.usfirst.frc.team1502.robot.subsystems.TankDrive;
import org.usfirst.frc.team1502.robot.subsystems.Vacuum;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static Drivetrain drivetrain = null;
	public static OI m_oi; 
	//public static ArcadeDrive m_arcadeDrive = new ArcadeDrive(null, null, null, null);
	public static TankDrive m_tankDrive = new TankDrive(null, null, null, null);
	public static Intake intake = new Intake(null);
	public static HatchRelease hatchRelease = new HatchRelease(null, null, null);
	public static Vacuum vacuum1 = new Vacuum(null);
	public static Vacuum vacuum2 = new Vacuum(null);
	public static HorizontalSlide horizontalSlide = new HorizontalSlide(null);
	public static PlatformLift lift =  new PlatformLift(null, null);
	//public static Sonar sonar = new Sonar(null);
	public static LinearSlide slide = new LinearSlide(null, null);
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		drivetrain = new Drivetrain();
		intake = new Intake(RobotMap.INTAKE_SPARK);
		hatchRelease = new HatchRelease(RobotMap.SOLENOID_1, RobotMap.SOLENOID_2, RobotMap.SOLENOID_3);
		vacuum1 = new Vacuum(RobotMap.VACUUM_SPARK1);
		vacuum1 = new Vacuum(RobotMap.VACUUM_SPARK2);
		horizontalSlide = new HorizontalSlide(RobotMap.RACK_SPARK);
		lift = new PlatformLift(new TalonSRX(RobotMap.PLATFORM_TALON_LEFT), new TalonSRX(RobotMap.PLATFORM_TALON_RIGHT));
		//sonar = new Sonar(RobotMap.SONAR_SPARK);
		m_oi = new OI();
		slide = new LinearSlide(new TalonSRX(RobotMap.LINEAR_SLIDE_TALON_LEFT), new TalonSRX(RobotMap.LINEAR_SLIDE_TALON_RIGHT));
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", m_chooser);

		//UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
	}
	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		m_autonomousCommand = m_chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		//sonar.readSensor();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
