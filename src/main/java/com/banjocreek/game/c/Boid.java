package com.banjocreek.game.c;

import java.awt.geom.Point2D;

import org.joml.Vector2d;

import com.banjocreek.game.b.GameObject;

public final class Boid implements GameObject {

	private static final double inverseMass = 1d/1.5d;
	private static final double maxForce = 2d;

	private static final double maxSpeed = 5d; // meters per second

	// destructive
	private static Vector2d truncate(Vector2d v, double max) {
		if (v.length() > max) {
			v.normalize().mul(max);
		}
		return v;
	}

	private static final boolean small(Vector2d v, double e) {
		return v.length() < e;
	}

	private final Point2D ppos = new Point2D.Double();
	private double rotation = 0d;
	private final Vector2d position = new Vector2d();
	private final Vector2d velocity = new Vector2d();
	private final Vector2d target = new Vector2d();
	private final Vector2d scratch = new Vector2d();
	private final Vector2d steering = new Vector2d();

	private void seek() {
		scratch.set(target).sub(position); // distance to target
		if (!small(scratch, .01)) {
			scratch.normalize().mul(maxSpeed); // desired velocity
			scratch.sub(velocity); // steering force a la Reynolds
			steering.add(scratch);
		}
	}

	@Override
	public void update(double dt) {
		steering.zero();				// re-compute all steering forces
		seek();							// seek force
		
		truncate(scratch, maxForce); 	// no warp drive
		scratch.mul(inverseMass); 		// steering acceleration
		velocity.add(scratch.mul(dt)); 	// Euler 
		truncate(velocity, maxSpeed);	// speed limit
		position.add(scratch.set(velocity).mul(dt)); // Euler again
		
		/*
		 * for inquiries
		 */
		rotation = Math.atan2(velocity.y, velocity.x);
		ppos.setLocation(position.x, position.y);
	}

	public Boid(Point2D p0) {
		this.position.set(p0.getX(), p0.getY());
		this.target.set(this.position);
		this.velocity.zero();
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

}
