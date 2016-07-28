package com.banjocreek.d2;

public final class MutableVec2 implements Vec2 {

    private double x, y;

    public MutableVec2(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(final Vec2 other) {
        return Math.sqrt((other.x() - this.x()) * (other.x() - this.x()) + (other.y() - this.y()) * (other.y() - this.y()));
    }

    public double distanceSquared(final Vec2 other) {
        return (other.x() - this.x()) * (other.x() - this.x()) + (other.y() - this.y()) * (other.y() - this.y());
    }

    public double heading() {
        return Math.atan2(this.x, this.y);
    }

    public double magnitude() {
        return Math.sqrt(this.x() * this.x() + this.y() * this.y());
    }

    public double magnitudeSquared() {
        return this.x() * this.x() + this.y() * this.y();
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
    
    public double x() { return x; }
    public double y() { return y; }
    
    @Override
    public String toString() {
        return new StringBuffer().append("(").append(x).append(",").append(y).append(")").toString();
    }

}
