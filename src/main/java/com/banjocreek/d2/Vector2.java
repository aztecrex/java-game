package com.banjocreek.d2;

public final class Vector2 {

    public final double x, y;

    public Vector2(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof Vector2 && ((Vector2) obj).x == this.x && ((Vector2) obj).y == this.y;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(this.x) & Double.hashCode(this.y) << 16;
    }

    public double mag() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public double magSquared() {
        return this.x * this.x + this.y * this.y;
    }

    public Vector2 plus(final Vector2 rhs) {
        return new Vector2(this.x + rhs.x, this.y + rhs.y);
    }

}
