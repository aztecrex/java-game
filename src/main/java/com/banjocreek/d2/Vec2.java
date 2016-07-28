package com.banjocreek.d2;

public final class Vec2 {

    public final double x, y;

    public Vec2(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(final Vec2 other) {
        return Math.sqrt((other.x - this.x) * (other.x - this.x) + (other.y - this.y) * (other.y - this.y));
    }

    public double distanceSquared(final Vec2 other) {
        return (other.x - this.x) * (other.x - this.x) + (other.y - this.y) * (other.y - this.y);
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof Vec2 && ((Vec2) obj).x == this.x && ((Vec2) obj).y == this.y;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(this.x) & Double.hashCode(this.y) << 16;
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

    public Vec2 minus(final Vec2 rhs) {
        return new Vec2(this.x - rhs.x, this.y - rhs.y);
    }

    public Vec2 plus(final Vec2 rhs) {
        return new Vec2(this.x + rhs.x, this.y + rhs.y);
    }

    public Vec2 scale(final double amount) {
        return new Vec2(amount * this.x, amount * this.y);
    }

    public Vec2 scaleInverse(final double amount) {
        return new Vec2(this.x / amount, this.y / amount);
    }
    
    @Override
    public String toString() {
        return new StringBuffer().append("(").append(x).append(",").append(y).append(")").toString();
    }

}
