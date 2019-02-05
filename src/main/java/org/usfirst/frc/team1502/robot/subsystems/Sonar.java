/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1502.robot.subsystems;

import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class Sonar extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  boolean enabled = false;
  public enum Boundaries {
    high, low;
  }; // these are the boundaries for the sonar to detect

  AnalogInput analogSonar;

  double analogVolts; // raw output
  double cm;

  public Sonar(AnalogInput analogSonar) {
    this.analogSonar = analogSonar;
  }

  public static Map<Boundaries, Double> PlatForm = new EnumMap<Boundaries, Double>(Boundaries.class) {{ // "error" is because i havent set a serial number for it
    put(Boundaries.high, 0.0);
    put(Boundaries.low, 1.0);
  }};

  public static Map<Boundaries, Double> Rocket = new EnumMap<Boundaries, Double>(Boundaries.class) {{
    put(Boundaries.high, 0.0);
    put(Boundaries.low, 1.0);
  }};
  
  Map<Boundaries, Double> PlatFormRef = Collections.unmodifiableMap(new LinkedHashMap<Boundaries, Double>(PlatForm));
  Map<Boundaries, Double> RocketRef = Collections.unmodifiableMap(new LinkedHashMap<Boundaries, Double>(Rocket));

  public double getBound(Map<Boundaries, Double> type, Boundaries distance) { // simple get function. its slick
    return (double) type.get(distance);
  }

  // public void check(Map<Boundaries, Double> type) {
  //   double place = readSensor();
  //   if (place < getBound(type, Boundaries.low)) {
  //     SmartDashboard.putBoolean("close", true);
  //   }
  //   else if(place > getBound(type, Boundaries.high)) {
  //     SmartDashboard.putBoolean("far", true);
  //   }
  //   else {
  //     SmartDashboard.putBoolean("just right", true);
  //   }
  // }

  public boolean check() {
    double place = readSensor();
    return !(enabled && place < .3);
    // if (enabled && place < .3) {
    //   return false;
    // }
    // else {
    //   return true;
    // }
  }

  public void softStopToggle() {
    enabled = !enabled;
  }

  // public double getBoundary(Type type, Distance distance) {
  //   switch (type) {
  //     case LinearSlide:
  //     if (distance == Distance.high) return LINEAR_SLIDE_HIGH;
  //     if(distance == Distance.low) return LINEAR_SLIDE_LOW;
  //     case PlatForm:
  //     if (distance == Distance.high) return PLATFORM_HIGH;
  //     if (distance == Distance.low) return PLATFORM_LOW;
  //   default:
  //     return 0.0; // This won't happen
  //   }
  // }

  public double readSensor() {
    analogVolts = analogSonar.getVoltage();
    cm = analogVolts / 2;
    return cm;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
