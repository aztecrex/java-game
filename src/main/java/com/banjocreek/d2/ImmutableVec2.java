package com.banjocreek.d2;

public final class ImmutableVec2 implements Vec2 {

    static final ImmutableVec2 east = new ImmutableVec2(1d, 0d);
    static final ImmutableVec2 north = new ImmutableVec2(0d, 1d);
    static final ImmutableVec2 south = new ImmutableVec2(0d, -1d);
    static final ImmutableVec2 west = new ImmutableVec2(-1d, 0d);

    private final double x, y;

    public ImmutableVec2(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof ImmutableVec2 && ((ImmutableVec2) obj).x == this.x && ((ImmutableVec2) obj).y == this.y;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(this.x) & Double.hashCode(this.y) << 16;
    }

    public ImmutableVec2 minus(final Vec2 rhs) {
        return new ImmutableVec2(this.x - rhs.x(), this.y - rhs.y());
    }

    public ImmutableVec2 normalize() {
        final double mag = Math.sqrt(this.x * this.x + this.y * this.y);
        return new ImmutableVec2(this.x / mag, this.y / mag);
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

    public ImmutableVec2 transform(final Mat3 xf) {

        /*
         * Mat3 and simplified transform are coincident. potentially save some
         * ops.
         */
        if (xf instanceof SimplifiedTransform)
            return transformn((SimplifiedTransform) xf);

        final double x = xf.m00() * this.x + xf.m10() * this.y + xf.m20();
        final double y = xf.m01() * this.x + xf.m11() * this.y + xf.m21();
        final double w = xf.m02() * this.x + xf.m12() * this.y + xf.m22();

        return new ImmutableVec2(x / w, y / w);
    }

    public ImmutableVec2 transformn(final SimplifiedTransform xf) {
        final double x = xf.m00() * this.x + xf.m10() * this.y + xf.m20();
        final double y = xf.m01() * this.x + xf.m11() * this.y + xf.m21();

        return new ImmutableVec2(x, y);
    }

    public ImmutableVec2 truncate(final double max) {
        final double mag = Math.sqrt(this.x * this.x + this.y * this.y);
        if (mag > max) {
            final double s = max / mag;
            return new ImmutableVec2(this.x * s, this.y * s);
        } else
            return this;
    }

    public MutableVec2 unsafe() {
        return new MutableVec2(this.x, this.y);
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
