package com.banjocreek.game.c;

import java.awt.geom.Point2D;
import java.util.Random;

import com.banjocreek.d2.MutableVec2;
import com.banjocreek.d2.Vec2;

public final class Boid implements GameObject {

    private static final double inverseMass = 1d / 1d;
    private static final double maxForce = 2d;

    private static final double maxSpeed = 2d; // meters per second

    private static final Random rng = new Random();

    private static final double slowRadius = 3d;

    private final MutableVec2 danger = Vec2.mutable();
    private final MutableVec2 position = Vec2.mutable();

    private final Point2D ppos = new Point2D.Double();
    private double rotation = 0d;
    private final MutableVec2 scratch = Vec2.mutable();
    private final MutableVec2 steering = Vec2.mutable();
    private final MutableVec2 target = Vec2.mutable();
    private final MutableVec2 velocity = Vec2.mutable();

    public Boid(final Point2D p0) {
        this.position.set(p0.getX(), p0.getY());
        this.target.set(this.position);
        this.danger.set(500, 500).plus(this.position);
        this.velocity.zero();
        publish();
    }

    public Boid avoid(final Point2D target) {
        this.danger.set(target.getX(), target.getY());
        return this;
    }

    @Override
    public Point2D position() {
        return this.ppos;
    }

    @Override
    public double rotation() {
        return this.rotation;
    }

    public Boid seek(final Point2D target) {
        this.target.set(target.getX(), target.getY());
        return this;
    }

    @Override
    public void update(final double dt) {
        this.steering.zero(); // re-compute all steering forces
        seek(); // seek force
        avoid(); // run away

        applyForce(dt);
        applyVelocity(dt);

        publish();
    }

    private void applyForce(final double dt) {
        this.steering.truncate(maxForce); // no warp drive
        this.scratch.set(this.steering).scale(inverseMass); // convert to
                                                            // acceleration
        this.velocity.plus(this.scratch.scale(dt)); // thanks Leonhard
    }

    private void applyVelocity(final double dt) {
        this.velocity.truncate(maxSpeed);
        if (this.velocity.magnitude() < .01)
            return; // static friction, keep things from bouncing around
        this.scratch.set(this.velocity);
        this.position.plus(this.scratch.scale(dt)); // integrate
    }

    private void avoid() {
        this.scratch.set(this.danger).minus(this.position); // vector to danger
        final double distance = this.scratch.magnitude();
        if (distance > 1d)
            return; // no influence
        if (distance == 0d) {
            this.scratch.set(rng.nextDouble(), rng.nextDouble()); // danger
                                                                  // right on us
        }

        this.scratch.normalize().scale(-maxSpeed); // desired velocity
        this.scratch.minus(this.velocity); // steering force a la Reynolds
        this.steering.plus(this.scratch); // accumulate force

    }

    private void publish() {
        this.rotation = Math.atan2(this.velocity.y(), this.velocity.x());
        this.ppos.setLocation(this.position.x(), this.position.y());

    }

    private void seek() {
        this.scratch.set(this.target).minus(this.position); // vector to target
        final double distance = this.scratch.magnitude(); // distance to target
        if (distance == 0d)
            return; // no influence
        this.scratch.normalize().scale(maxSpeed); // desired velocity
        if (distance < slowRadius) {
            this.scratch.scale(distance / slowRadius); // nearing target, slow
                                                       // down
        }
        this.scratch.minus(this.velocity); // steering force a la Reynolds
        this.steering.plus(this.scratch); // accumulate force
    }

}
