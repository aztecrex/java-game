package com.banjocreek.d2;

public final class ImmutableVec2 implements Vec2 {

    private final double x, y;

    public ImmutableVec2(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(final ImmutableVec2 other) {
        return Math.sqrt((other.x - this.x) * (other.x - this.x) + (other.y - this.y) * (other.y - this.y));
    }

    public double distanceSquared(final ImmutableVec2 other) {
        return (other.x - this.x) * (other.x - this.x) + (other.y - this.y) * (other.y - this.y);
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof ImmutableVec2 && ((ImmutableVec2) obj).x == this.x && ((ImmutableVec2) obj).y == this.y;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(this.x) & Double.hashCode(this.y) << 16;
    }

    @Override
    public double heading() {
        return Math.atan2(this.x, this.y);
    }

    @Override
    public double magnitude() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    @Override
    public double magnitudeSquared() {
        return this.x * this.x + this.y * this.y;
    }

    public ImmutableVec2 minus(final Vec2 rhs) {
        return new ImmutableVec2(this.x - rhs.x(), this.y - rhs.y());
    }

    public ImmutableVec2 plus(final Vec2 rhs) {
        return new ImmutableVec2(this.x + rhs.x(), this.y + rhs.y());
    }

    public ImmutableVec2 scale(final double amount) {
        return new ImmutableVec2(amount * this.x, amount * this.y);
    }

    public ImmutableVec2 scaleInverse(final double amount) {
        return new ImmutableVec2(this.x / amount, this.y / amount);
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
