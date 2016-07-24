package com.banjocreek.game.c;

import java.awt.geom.Point2D;

import org.joml.Vector2d;

public class Car implements GameObject {

	private static final double mass = 1d; //

	private static final double friction = .5d;
	
	private static final double wheelBase = .5d;
	
	private double prot = 0d;
	private Point2D ppos = new Point2D.Double();

	private double thrust = 0d;
	private double brake = 1d;
	private double speed = 0d; 		// not moving
	private double direction = 0d; 	// face positive x
	private double steer = 0d;		// forward (left pos, right neg)

	private Vector2d position = new Vector2d();
	private Vector2d scratch = new Vector2d();
	private Vector2d frontWheel = new Vector2d();
	private Vector2d backWheel = new Vector2d();
	
	private Vector2d target = new Vector2d();
	
	public Car(Point2D p0, double d0) {
		this.direction = d0;
		this.position.set(p0.getX(), p0.getY());
		
		publish();
	}

	@Override
	public Point2D position() {
		return ppos;
	}

	@Override
	public double rotation() {
		return prot;
	}
	
	private static final double TWO_PI = Math.PI * 2d;
	
	private static double normalizeAngle(double a) {
		return a - TWO_PI * Math.floor((a + Math.PI) / TWO_PI);
	}
	
	private void seek() {
		scratch.set(target).sub(position);	// vector to target
		final double targetDistance = scratch.length();
		if (targetDistance == 0d) 
			return;		// unknown change
		final double targetHeading = Math.atan2(scratch.y,scratch.x);
		final double adjust = normalizeAngle( targetHeading - direction ) ;
		steer = Math.max(-maxSteer, Math.min(maxSteer,adjust));
	}
	
	
	@Override
	public void update(double dt) {
		
		seek();
		
		double f = 0;
		f+= thrust;
		f-= speed * friction;
		
		speed += f * dt;
		speed = speed < .01 ? 0 : speed;
		speed = Math.max(speed, 0);
		double dspeed = speed * dt;
		double cd = Math.cos(direction);
		double sd = Math.sin(direction);
		backWheel.set(position).add(scratch.set(cd,sd).mul(-wheelBase / 2));
		frontWheel.set(position).add(scratch.set(cd,sd).mul(wheelBase / 2));
		backWheel.add(scratch.set(cd,sd).mul(dspeed));
		frontWheel.add(scratch.set(Math.cos(direction + steer), Math.sin(direction + steer)).mul(dspeed));
		
		position.set(scratch.set(frontWheel).add(backWheel).mul(.5));
		direction = Math.atan2(frontWheel.y - backWheel.y, frontWheel.x - backWheel.x);
		
		publish();
	}

	private static final double maxThrust = 4d;
	
	public Car throttle(double amount) {
		thrust = Math.max(0, Math.min(maxThrust, amount * maxThrust));
		return this;
	}

	private void publish() {
		prot = direction;
		ppos.setLocation(position.x, position.y);
		
	}

	
	private static final double maxSteer = Math.PI/4;
	public Car steer(double amount) {
		steer = Math.max(-maxSteer, Math.min(maxSteer, amount * maxSteer));
		return this;
	}
	
	public Car seek(Point2D target) {
		this.target.set(target.getX(), target.getY());
		return this;
	}

}
