package com.banjocreek.game.c;

import java.awt.geom.Point2D;

import org.joml.Vector2d;

public class Car implements GameObject {

	private static final double mass = 1d; //

	private static final double friction = 1d;
	
	private double prot = 0d;
	private Point2D ppos = new Point2D.Double();

	private double thrust = 0d;
	private double brake = 1d;
	private double speed = 0d; 		// not moving
	private double direction = 0d; 	// face positive x
	private double steer = 0d;		// forward (left pos, right neg)

	private Vector2d position = new Vector2d();
	private Vector2d scratch = new Vector2d();
	
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

	@Override
	public void update(double dt) {
		double f = 0;
		f+= thrust;
		f-= speed * friction;

		speed += thrust * dt;
		
		scratch.set(Math.cos(direction), Math.sin(direction)).mul(speed);
		position.add(scratch.mul(dt));
		
		publish();
	}
	
	public Car withThrust() {
		this.thrust = 2d;
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
