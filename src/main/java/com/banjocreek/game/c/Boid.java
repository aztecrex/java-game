package com.banjocreek.game.c;

import java.awt.geom.Point2D;

import org.joml.Vector2d;

import com.banjocreek.game.b.GameObject;

public final class Boid implements GameObject {

	private static final double maxSpeed = 1d; // meters per second

	private static final boolean small(Vector2d v, double e) {
		return v.length() < e;
	}
	
	private final Point2D ppos = new Point2D.Double();
	private double rotation = 0d;
	private final Vector2d position = new Vector2d();
	private final Vector2d velocity = new Vector2d();
	private final Vector2d target = new Vector2d();
	private final Vector2d scratch = new Vector2d();
	
	@Override
	public void update(double dt) {
		scratch.set(target).sub(position);
		if (!small(scratch,.01)) {
			velocity.set(scratch).normalize().mul(maxSpeed);
			rotation = Math.atan2(velocity.y,velocity.x);
			position.add(scratch.set(velocity).mul(dt));
			rotation = Math.atan2(velocity.y, velocity.x);
			ppos.setLocation(position.x, position.y);
		}
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
