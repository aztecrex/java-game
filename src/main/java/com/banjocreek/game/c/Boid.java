package com.banjocreek.game.c;

import java.awt.geom.Point2D;
import java.util.Random;

import org.joml.Vector2d;

import com.banjocreek.game.b.GameObject;

public final class Boid implements GameObject {

	private static final double inverseMass = 1d / 1d;
	private static final double maxForce = 2d;

	private static final double maxSpeed = 2d; // meters per second

	private static final double slowRadius = 3d;

	private static final Random rng = new Random();
	
	// destructive
	private static Vector2d truncate(Vector2d v, double max) {
		if (v.length() > max) {
			v.normalize().mul(max);
		}
		return v;
	}

	private final Point2D ppos = new Point2D.Double();
	private double rotation = 0d;
	private final Vector2d position = new Vector2d();
	private final Vector2d velocity = new Vector2d();
	private final Vector2d target = new Vector2d();
	private final Vector2d danger = new Vector2d();
	private final Vector2d scratch = new Vector2d();
	private final Vector2d steering = new Vector2d();

	private void seek() {
		scratch.set(target).sub(position); 	// vector to target
		double distance = scratch.length();	// distance to target
		if (distance == 0d)
			return; 						// no influence
		scratch.normalize().mul(maxSpeed); 	// desired velocity
		if (distance < slowRadius) {
			scratch.mul(distance / slowRadius); // nearing target, slow down
		}
		scratch.sub(velocity); 				// steering force a la Reynolds
		steering.add(scratch); 				// accumulate force
	}

	private void avoid() {
		scratch.set(danger).sub(position);	// vector to danger
		double distance = scratch.length();
		if (distance > 1d)
			return;							// no influence
		if (distance == 0d)
			scratch.set(rng.nextDouble(),rng.nextDouble());	// danger right on us
		
		scratch.normalize().mul(-maxSpeed); // desired velocity
		scratch.sub(velocity); 				// steering force a la Reynolds
		steering.add(scratch);				// accumulate force

	}

	private void applyForce(double dt) {
		truncate(steering, maxForce);  // no warp drive
		scratch.set(steering).mul(inverseMass);	// convert to acceleration
		velocity.add(scratch.mul(dt)); // thanks, Leonhard
	}
	
	private void applyVelocity(double dt) {
		truncate(velocity, maxSpeed);
		if (velocity.length() < .01)
			return;					// static friction, keep things from bouncing around
		scratch.set(velocity);
		position.add(scratch.mul(dt)); // integrate
	}
	
	private void publish() {
		rotation = Math.atan2(velocity.y, velocity.x);
		ppos.setLocation(position.x, position.y);
		
	}
	
	@Override
	public void update(double dt) {
		steering.zero(); // re-compute all steering forces
		seek(); // seek force
		avoid(); // run away

		applyForce(dt);
		applyVelocity(dt);


		publish();
	}

	public Boid(Point2D p0) {
		this.position.set(p0.getX(), p0.getY());
		this.target.set(this.position);
		this.danger.set(500, 500).add(this.position);
		this.velocity.zero();
		publish();
	}

	@Override
	public Point2D position() {
		return ppos;
	}

	@Override
	public double rotation() {
		return rotation;
	}

	public Boid seek(Point2D target) {
		this.target.set(target.getX(), target.getY());
		return this;
	}

	public Boid avoid(Point2D target) {
		this.danger.set(target.getX(), target.getY());
		return this;
	}

}
