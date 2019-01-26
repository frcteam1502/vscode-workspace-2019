/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1502.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Sonar extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  AnalogInput sonar;

  double analogVolts;
  double cm;

  public Sonar(AnalogInput sonar){
    this.sonar = sonar;
  }

  public double readSensor() {
    analogVolts = sonar.getVoltage();
    cm = analogVolts / 2;
    printRange();
    return cm;
  }

  public void printRange(){
    System.out.println(cm);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    readSensor();
  }
}