package com.banjocreek.d2;

public final class Vec2i {

    public final double x, y;

    public Vec2i(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(final Vec2i other) {
        return Math.sqrt((other.x - this.x) * (other.x - this.x) + (other.y - this.y) * (other.y - this.y));
    }

    public double distanceSquared(final Vec2i other) {
        return (other.x - this.x) * (other.x - this.x) + (other.y - this.y) * (other.y - this.y);
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof Vec2i && ((Vec2i) obj).x == this.x && ((Vec2i) obj).y == this.y;
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

    public Vec2i minus(final Vec2i rhs) {
        return new Vec2i(this.x - rhs.x, this.y - rhs.y);
    }

    public Vec2i plus(final Vec2i rhs) {
        return new Vec2i(this.x + rhs.x, this.y + rhs.y);
    }

    public Vec2i scale(final double amount) {
        return new Vec2i(amount * this.x, amount * this.y);
    }

    public Vec2i scaleInverse(final double amount) {
        return new Vec2i(this.x / amount, this.y / amount);
    }
    
    @Override
    public String toString() {
        return new StringBuffer().append("(").append(x).append(",").append(y).append(")").toString();
    }

}
