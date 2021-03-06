package com.banjocreek.d2;

public final class ImmutableMat3 implements Mat3 {

    private final double m00;
    private final double m01;
    private final double m02;
    private final double m10;
    private final double m11;
    private final double m12;
    private final double m20;
    private final double m21;
    private final double m22;

    public ImmutableMat3(final double m00, final double m01, final double m02, final double m10, final double m11,
            final double m12, final double m20, final double m21, final double m22) {
        this.m00 = m00;
        this.m01 = m01;
        this.m02 = m02;
        this.m10 = m10;
        this.m11 = m11;
        this.m12 = m12;
        this.m20 = m20;
        this.m21 = m21;
        this.m22 = m22;

    }

    @Override
    public double m00() {
        return this.m00;
    }

    @Override
    public double m01() {
        return this.m01;
    }

    @Override
    public double m02() {
        return this.m02;
    }

    @Override
    public double m10() {
        return this.m10;
    }

    @Override
    public double m11() {
        return this.m11;
    }

    @Override
    public double m12() {
        return this.m12;
    }

    @Override
    public double m20() {
        return this.m20;
    }

    @Override
    public double m21() {
        return this.m21;
    }

    @Override
    public double m22() {
        return this.m22;
    }

    public ImmutableMat3 minus(final Mat3 rhs) {
        return new ImmutableMat3(this.m00 - rhs.m00(),
                this.m01 - rhs.m01(),
                this.m02 - rhs.m02(),
                this.m10 - rhs.m10(),
                this.m11 - rhs.m11(),
                this.m12 - rhs.m12(),
                this.m20 - rhs.m20(),
                this.m21 - rhs.m21(),
                this.m22 - rhs.m22());
    }

    public ImmutableMat3 plus(final Mat3 rhs) {
        return new ImmutableMat3(this.m00 + rhs.m00(),
                this.m01 + rhs.m01(),
                this.m02 + rhs.m02(),
                this.m10 + rhs.m10(),
                this.m11 + rhs.m11(),
                this.m12 + rhs.m12(),
                this.m20 + rhs.m20(),
                this.m21 + rhs.m21(),
                this.m22 + rhs.m22());
    }

    public ImmutableMat3 times(final double s) {
        return new ImmutableMat3(this.m00 * s,
                this.m01 * s,
                this.m02 * s,
                this.m10 * s,
                this.m11 * s,
                this.m12 * s,
                this.m20 * s,
                this.m21 * s,
                this.m22 * s);
    }

    public ImmutableMat3 times(final Mat3 rhs) {

        return new ImmutableMat3(this.m00 * rhs.m00() + this.m10 * rhs.m01() + this.m20 * rhs.m02(),
                this.m01 * rhs.m00() + this.m11 * rhs.m01() + this.m21 * rhs.m02(),
                this.m02 * rhs.m00() + this.m12 * rhs.m01() + this.m22 * rhs.m02(),
                this.m00 * rhs.m10() + this.m10 * rhs.m11() + this.m20 * rhs.m12(),
                this.m01 * rhs.m10() + this.m11 * rhs.m11() + this.m21 * rhs.m12(),
                this.m02 * rhs.m10() + this.m12 * rhs.m11() + this.m22 * rhs.m12(),
                this.m00 * rhs.m20() + this.m10 * rhs.m21() + this.m20 * rhs.m22(),
                this.m01 * rhs.m20() + this.m11 * rhs.m21() + this.m21 * rhs.m22(),
                this.m02 * rhs.m20() + this.m12 * rhs.m21() + this.m22 * rhs.m22());
    }

    @Override
    public String toString() {
        return Helper.show(this);
    }
}
