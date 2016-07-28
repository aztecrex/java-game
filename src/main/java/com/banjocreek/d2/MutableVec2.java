package com.banjocreek.d2;

public final class MutableVec2 implements Vec2 {

    private double x, y;

    public MutableVec2(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double distance(final Vec2 other) {
        return Math.sqrt((other.x() - x()) * (other.x() - x()) + (other.y() - y()) * (other.y() - y()));
    }

    @Override
    public double distanceSquared(final Vec2 other) {
        return (other.x() - x()) * (other.x() - x()) + (other.y() - y()) * (other.y() - y());
    }

    @Override
    public double heading() {
        return Math.atan2(this.x, this.y);
    }

    @Override
    public double magnitude() {
        return Math.sqrt(x() * x() + y() * y());
    }

    @Override
    public double magnitudeSquared() {
        return x() * x() + y() * y();
    }

    public MutableVec2 minus(final Vec2 rhs) {
        this.x -= rhs.x();
        this.y -= rhs.y();
        return this;
    }

    public MutableVec2 plus(final Vec2 rhs) {
        this.x += rhs.x();
        this.y += rhs.y();
        return this;
    }

    public MutableVec2 scale(final double amount) {
        this.x *= amount;
        this.y *= amount;
        return this;
    }

    public MutableVec2 scaleInverse(final double amount) {
        this.x /= amount;
        this.y /= amount;
        return this;
    }

    @Override
    public String toString() {
        return new StringBuffer().append("(").append(this.x).append(",").append(this.y).append(")").toString();
    }

    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

}
