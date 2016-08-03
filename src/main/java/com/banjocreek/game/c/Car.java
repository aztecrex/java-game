package com.banjocreek.game.c;

import java.awt.geom.Point2D;

import com.banjocreek.d2.MutableVec2;
import com.banjocreek.d2.Vec2;

public class Car implements GameObject {

    private static final double friction = .5d;

    private static final double inverseMass = 1d; //

    private static final double maxSteer = Math.PI / 5;

    private static final double maxThrust = 4d;
    private static final double TWO_PI = Math.PI * 2d;

    private static final double wheelBase = .5d;

    private static double normalizeAngle(final double a) {
        return a - TWO_PI * Math.floor((a + Math.PI) / TWO_PI);
    }

    private final MutableVec2 backWheel = Vec2.mutable();
    private double brake = 0d;
    private double direction = 0d; // face positive x

    private final MutableVec2 frontWheel = Vec2.mutable();
    private final MutableVec2 position = Vec2.mutable();
    private final Point2D ppos = new Point2D.Double();
    private double prot = 0d;

    private final MutableVec2 scratch = Vec2.mutable();

    private boolean seeking = false;

    private double speed = 0d; // not moving

    private double steer = 0d; // forward (left pos, right neg)

    private final MutableVec2 target = Vec2.mutable();

    private double thrust = 0d;

    public Car(final Point2D p0, final double d0) {
        this.direction = d0;
        this.position.set(p0.getX(), p0.getY());

        publish();
    }

    @Override
    public Point2D position() {
        return this.ppos;
    }

    @Override
    public double rotation() {
        return this.prot;
    }

    public Car seek(final Point2D target) {
        this.seeking = true;
        this.target.set(target.getX(), target.getY());
        return this;
    }

    public Car steer(final double amount) {
        this.seeking = false;
        this.steer = Math.max(-maxSteer, Math.min(maxSteer, amount * maxSteer));
        return this;
    }

    public Car stop() {
        this.seeking = false;
        brake();
        return this;
    }

    public Car throttle(final double amount) {
        this.seeking = false;
        accelerate(Math.max(0, Math.min(maxThrust, amount * maxThrust)));
        return this;
    }

    @Override
    public void update(final double dt) {
        if (this.seeking) {
            seek();
        }

        double f = 0;
        f += this.thrust * inverseMass;
        f -= this.speed * (friction + this.brake);

        this.speed += f * dt;
        this.speed = this.speed < .01 ? 0 : this.speed;
        this.speed = Math.max(this.speed, 0);
        final double dspeed = this.speed * dt;
        final double cd = Math.cos(this.direction);
        final double sd = Math.sin(this.direction);
        this.backWheel.set(this.position).plus(this.scratch.set(cd, sd).scale(-wheelBase / 2));
        this.frontWheel.set(this.position).plus(this.scratch.set(cd, sd).scale(wheelBase / 2));
        this.backWheel.plus(this.scratch.set(cd, sd).scale(dspeed));
        this.frontWheel.plus(this.scratch
                .set(Math.cos(this.direction + this.steer), Math.sin(this.direction + this.steer)).scale(dspeed));

        this.position.set(this.scratch.set(this.frontWheel).plus(this.backWheel).scale(.5));
        this.direction = Math.atan2(this.frontWheel.y() - this.backWheel.y(), this.frontWheel.x() - this.backWheel.x());

        publish();
    }

    private void accelerate(final double thrust) {
        this.brake = 0d;
        this.thrust = thrust;
    }

    private void brake() {
        this.brake = 5d;
        this.thrust = 0d;
    }

    private void publish() {
        this.prot = this.direction;
        this.ppos.setLocation(this.position.x(), this.position.y());

    }

    private void seek() {
        this.scratch.set(this.target).minus(this.position); // vector to target
        final double targetDistance = this.scratch.magnitude();
        if (targetDistance < 1d) {
            brake();
        } else {
            accelerate(maxThrust);
            final double targetHeading = this.scratch.heading();
            final double adjust = normalizeAngle(targetHeading - this.direction);
            this.steer = Math.max(-maxSteer, Math.min(maxSteer, adjust));
        }
    }

}
