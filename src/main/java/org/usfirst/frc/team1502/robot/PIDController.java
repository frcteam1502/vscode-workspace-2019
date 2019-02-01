package org.usfirst.frc.team1502.robot;

import java.util.ArrayList;

public class PIDController {
	double P;
	double I;
	double D;
	ArrayList<Point> points = new ArrayList<Point>();
	
	public PIDController(final double p, final double i, final double d) {
		P = p;
		I = i;
		D = d;
	}
	
	public void reset() {
		points.clear();
	}
	
	public void input(double err) {
		if ((err > 0 && latest().err < 0) || (err < 0 && latest().err > 0)) {
			points.clear();
		}
		points.add(new Point(System.currentTimeMillis(), err));
	}
	
	public double getCorrection() {
		return getP() + getI() + getD();
	}
	
	public double getP() {
		return P * latest().err;
	}
	
	public double getI() {
		if (points.size() < 2) {
			return 0;
		}
		double area = 0;
		for (int i = 1; i < points.size(); i++) {
			double rectWidth = points.get(i).millis - points.get(i - 1).millis;
			double rectHeight = (points.get(i - 1).err + points.get(i).err) / 2;
			area += rectHeight * rectWidth;
		}
		return I * area;
	}
	
	public double getD() {
		if (points.size() < 2) {
			return 0;
		}
		double derr = latest().err - prev().err;
		double dt = latest().millis - prev().millis;
		return D * derr / dt;
	}
	
	public boolean isStable(double threshold) {
		return Math.abs(latest().err) < threshold && Math.abs(getD()) / D * 8000/*ms*/ < threshold;
	}
	
	public Point prev() {
		try {
			return points.get(points.size() - 2);
		} catch (ArrayIndexOutOfBoundsException e) {
			return new Point(System.currentTimeMillis(), 0);
		}
	}
	
	public Point latest() {
		try {
			return points.get(points.size() - 1);
		} catch (ArrayIndexOutOfBoundsException e) {
			return new Point(System.currentTimeMillis(), 0);
		}
	}
}
