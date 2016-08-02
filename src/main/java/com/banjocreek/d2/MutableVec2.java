package com.banjocreek.d2;

public final class MutableVec2 implements Vec2 {

    private double x, y;

    public MutableVec2(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public MutableVec2 minus(final Vec2 rhs) {
        return minus(rhs, this);
    }

    public MutableVec2 minus(final Vec2 rhs, final MutableVec2 dest) {
        dest.x = this.x - rhs.x();
        dest.y = this.y - rhs.y();
        return dest;
    }

    public MutableVec2 normalize() {
        return normalize(this);
    }

    public MutableVec2 normalize(final MutableVec2 dest) {
        final double mag = Math.sqrt(this.x * this.x + this.y * this.y);
        dest.x = this.x / mag;
        dest.y = this.y / mag;
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

    public ImmutableVec2 safe() {
        return new ImmutableVec2(this.x, this.y);
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

    public MutableVec2 set(final Vec2 from) {
        this.x = from.x();
        this.y = from.y();
        return this;
    }

    @Override
    public String toString() {
        return new StringBuffer().append("(").append(this.x).append(",").append(this.y).append(")").toString();
    }

    public MutableVec2 transform(final Mat3 xf) {
        /*
         * Mat3 and simplified Transform interfaces coincide, potentially get
         * rid of a few ops
         */
        if (xf instanceof SimplifiedTransform) {
            xfn((SimplifiedTransform) xf, this);
        } else {
            xfm(xf, this);
        }
        return this;
    }

    public MutableVec2 transform(final Mat3 xf, final MutableVec2 dest) {

        /*
         * Mat3 and simplified Transform interfaces coincide, potentially get
         * rid of a few ops
         */
        if (xf instanceof SimplifiedTransform) {
            xfn((SimplifiedTransform) xf, dest);
        } else {
            xfm(xf, dest);
        }
        return dest;

    }

    public MutableVec2 transformn(final SimplifiedTransform xf) {
        xfn(xf, this);
        return this;
    }

    public MutableVec2 transformn(final SimplifiedTransform xf, final MutableVec2 dest) {
        xfn(xf, dest);
        return dest;
    }

    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    public MutableVec2 zero() {
        this.x = this.y = 0d;
        return this;
    }

    private void xfm(final Mat3 xf, final MutableVec2 dest) {
        final double x = xf.m00() * this.x + xf.m10() * this.y + xf.m20();
        final double y = xf.m01() * this.x + xf.m11() * this.y + xf.m21();
        final double w = xf.m02() * this.x + xf.m12() * this.y + xf.m22();

        dest.x = x / w;
        dest.y = y / w;
    }

    private void xfn(final SimplifiedTransform xf, final MutableVec2 dest) {
        final double x = xf.m00() * this.x + xf.m10() * this.y + xf.m20();
        final double y = xf.m01() * this.x + xf.m11() * this.y + xf.m21();

        dest.x = x;
        dest.y = y;
    }

}
