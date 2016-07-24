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
	private double steer = 0.3d;		// forward (left pos, right neg)

	private Vector2d position = new Vector2d();
	private Vector2d scratch = new Vector2d();
	private Vector2d frontWheel = new Vector2d();
	private Vector2d backWheel = new Vector2d();
	
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
	
	
	
	/*


Vector2 frontWheel = carLocation + wheelBase/2 * new Vector2( cos(carHeading) , sin(carHeading) );
Vector2 backWheel = carLocation - wheelBase/2 * new Vector2( cos(carHeading) , sin(carHeading) );

backWheel += carSpeed * dt * new Vector2(cos(carHeading) , sin(carHeading));
frontWheel += carSpeed * dt * new Vector2(cos(carHeading+steerAngle) , sin(carHeading+steerAngle));

carLocation = (frontWheel + backWheel) / 2;
carHeading = atan2( frontWheel.Y - backWheel.Y , frontWheel.X - backWheel.X );

	 */

	@Override
	public void update(double dt) {
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
	
	public Car withThrust() {
		this.thrust = 3d;
		return this;
	}
	
	public Car withoutThrust() {
		this.thrust = 0d;
		return this;
	}

	private void publish() {
		prot = direction;
		ppos.setLocation(position.x, position.y);
		
	}
	

}
