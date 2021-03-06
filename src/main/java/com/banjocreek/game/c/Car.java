package com.banjocreek.game.c;

import java.awt.geom.Point2D;

import org.joml.Vector2d;

public class Car implements GameObject {

	private static final double inverseMass = 1d; //

	private static final double friction = .5d;
	
	private static final double wheelBase = .5d;
	
	private double prot = 0d;
	private Point2D ppos = new Point2D.Double();

	private double thrust = 0d;
	private double brake = 0d;
	private double speed = 0d; 		// not moving
	private double direction = 0d; 	// face positive x
	private double steer = 0d;		// forward (left pos, right neg)

	private Vector2d position = new Vector2d();
	private Vector2d scratch = new Vector2d();
	private Vector2d frontWheel = new Vector2d();
	private Vector2d backWheel = new Vector2d();
	
	private Vector2d target = new Vector2d();
	
	private boolean seeking = false;
	
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
	
	private void brake() {
		brake = 5d;
		thrust = 0d;
	}

	private void accelerate(double thrust) {
		brake = 0d;
		this.thrust = thrust;
	}
	
	private void seek() {
		scratch.set(target).sub(position);	// vector to target
		final double targetDistance = scratch.length();
		if (targetDistance < 1d) { 
			brake();
		} else {
			accelerate(maxThrust);
			final double targetHeading = Math.atan2(scratch.y,scratch.x);
			final double adjust = normalizeAngle( targetHeading - direction ) ;
			steer = Math.max(-maxSteer, Math.min(maxSteer,adjust));
		}
	}
	
	
	@Override
	public void update(double dt) {
		if (seeking)
			seek();
		
		double f = 0;
		f+= thrust * inverseMass;
		f-= speed * (friction + brake);
		
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
		this.seeking = false;
		accelerate(Math.max(0, Math.min(maxThrust, amount * maxThrust)));
		return this;
	}

	public Car stop() {
		this.seeking = false;
		brake();
		return this;
	}
	
	private void publish() {
		prot = direction;
		ppos.setLocation(position.x, position.y);
		
	}

	
	private static final double maxSteer = Math.PI/5;
	public Car steer(double amount) {
		this.seeking = false;
		steer =  Math.max(-maxSteer, Math.min(maxSteer, amount * maxSteer));
		return this;
	}
	
	public Car seek(Point2D target) {
		this.seeking = true;
		this.target.set(target.getX(), target.getY());
		return this;
	}

	
	
}
