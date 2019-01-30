/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1502.robot.subsystems;

import java.util.EnumMap;
import java.util.Map;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Sonar extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public enum Boundaries {
    high, low;
  }; //these are the boundaries for the sonar to detect

  // private static final double LINEAR_SLIDE_HIGH = 0;
  // private static final double LINEAR_SLIDE_LOW = 0;
  // private static final double PLATFORM_HIGH = 0;
  // private static final double PLATFORM_LOW = 0;;

  AnalogInput analogSonar;

  double analogVolts; // raw output
  double cm; 

  public Sonar(AnalogInput analogSonar) {
    this.analogSonar = analogSonar;
    
    PlatForm.put(Boundaries.high, 0.0); //initiates the values for the different maps, the names are pretty explanatory for what is what
    PlatForm.put(Boundaries.low, 1.0);
    Rocket.put(Boundaries.high, 0.0);
    Rocket.put(Boundaries.low, 1.0);
  }

  public static Map<Boundaries, Double> PlatForm = new EnumMap<Boundaries, Double>(Boundaries.class); // initiates the map itself
  // {{ different way to initiate the values, but it sucks. still cool
  //   put(Distance.high, 0.0);
  //   put(Distance.low, 1.0);
  // }};
  public static Map<Boundaries, Double> Rocket = new EnumMap<Boundaries, Double>(Boundaries.class);

  public double getBound(Map<Boundaries, Double> type, Boundaries distance){ //simple get function. its slick
    return (double) type.get(distance);
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
    //printRange();
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
