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
        return minus(rhs, this);
    }

    public MutableVec2 minus(final Vec2 rhs, final MutableVec2 dest) {
        dest.x = this.x - rhs.x();
        dest.y = this.y - rhs.y();
        return dest;
    }

    public MutableVec2 plus(final Vec2 rhs) {
        return plus(rhs, this);
    }

    public MutableVec2 plus(final Vec2 rhs, final MutableVec2 dest) {
        dest.x = this.x + rhs.x();
        dest.y = this.y + rhs.y();
        return dest;
    }

    public MutableVec2 scale(final double amount) {
        return scale(amount, this);
    }

    public MutableVec2 scale(final double amount, final MutableVec2 dest) {
        dest.x = this.x * amount;
        dest.y = this.y * amount;
        return dest;
    }

    public MutableVec2 scaleInverse(final double amount) {
        return scaleInverse(amount, this);
    }

    public MutableVec2 scaleInverse(final double amount, final MutableVec2 dest) {
        dest.x = this.x / amount;
        dest.y = this.y / amount;
        return dest;
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
