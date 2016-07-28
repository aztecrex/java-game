package com.banjocreek.d2m;

public final class Vec2m {

    private double x, y;

    public Vec2m(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(final Vec2m other) {
        return Math.sqrt((other.x - this.x) * (other.x - this.x) + (other.y - this.y) * (other.y - this.y));
    }

    public double distanceSquared(final Vec2m other) {
        return (other.x - this.x) * (other.x - this.x) + (other.y - this.y) * (other.y - this.y);
    }

    public double heading() {
        return Math.atan2(this.x, this.y);
    }

    public double mag() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public double magSquared() {
        return this.x * this.x + this.y * this.y;
    }

    public Vec2m minus(final Vec2m rhs) {
        this.x -= rhs.x();
        this.y -= rhs.y();
        return this;
    }

    public Vec2m plus(final Vec2m rhs) {
        this.x += rhs.x;
        this.y += rhs.y;
        return this;
    }

    public Vec2m scale(final double amount) {
        this.x *= amount;
        this.y *= amount;
        return this;
    }

    public Vec2m scaleInverse(final double amount) {
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
