package com.banjocreek.d2;

public final class MutableMat3 implements Mat3 {

    private double m00;
    private double m01;
    private double m02;
    private double m10;
    private double m11;
    private double m12;
    private double m20;
    private double m21;
    private double m22;

    public MutableMat3(final double m00, final double m01, final double m02, final double m10, final double m11,
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

    public MutableMat3 clear() {
        this.m00 = 0;
        this.m01 = 0;
        this.m02 = 0;
        this.m10 = 0;
        this.m11 = 0;
        this.m12 = 0;
        this.m20 = 0;
        this.m21 = 0;
        this.m22 = 0;
        return this;
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

    public MutableMat3 minus(final Mat3 rhs) {
        return minus(rhs, this);
    }

    public MutableMat3 minus(final Mat3 rhs, final MutableMat3 dest) {
        dest.m00 = this.m00 - rhs.m00();
        dest.m01 = this.m01 - rhs.m01();
        dest.m02 = this.m02 - rhs.m02();
        dest.m10 = this.m10 - rhs.m10();
        dest.m11 = this.m11 - rhs.m11();
        dest.m12 = this.m12 - rhs.m12();
        dest.m20 = this.m20 - rhs.m20();
        dest.m21 = this.m21 - rhs.m21();
        dest.m22 = this.m22 - rhs.m22();
        return dest;
    }

    public MutableMat3 plus(final Mat3 rhs) {
        return plus(rhs, this);
    }

    public MutableMat3 plus(final Mat3 rhs, final MutableMat3 dest) {
        dest.m00 = this.m00 + rhs.m00();
        dest.m01 = this.m01 + rhs.m01();
        dest.m02 = this.m02 + rhs.m02();
        dest.m10 = this.m10 + rhs.m10();
        dest.m11 = this.m11 + rhs.m11();
        dest.m12 = this.m12 + rhs.m12();
        dest.m20 = this.m20 + rhs.m20();
        dest.m21 = this.m21 + rhs.m21();
        dest.m22 = this.m22 + rhs.m22();
        return dest;
    }

    public MutableMat3 set(final double m00, final double m01, final double m02, final double m10, final double m11,
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
        return this;
    }

    public MutableMat3 set(final Mat3 from) {
        this.m00 = from.m00();
        this.m01 = from.m01();
        this.m02 = from.m02();
        this.m10 = from.m10();
        this.m11 = from.m11();
        this.m12 = from.m12();
        this.m20 = from.m20();
        this.m21 = from.m21();
        this.m22 = from.m22();
        return this;
    }

    public MutableMat3 times(final Mat3 rhs) {
        return times(rhs, this);
    }

    public MutableMat3 times(final Mat3 rhs, final MutableMat3 dest) {

        final double m00 = this.m00 * rhs.m00() + this.m10 * rhs.m01() + this.m20 * rhs.m02();
        final double m01 = this.m01 * rhs.m00() + this.m11 * rhs.m01() + this.m21 * rhs.m02();
        final double m02 = this.m02 * rhs.m00() + this.m12 * rhs.m01() + this.m22 * rhs.m02();
        final double m10 = this.m00 * rhs.m10() + this.m10 * rhs.m11() + this.m20 * rhs.m12();
        final double m11 = this.m01 * rhs.m10() + this.m11 * rhs.m11() + this.m21 * rhs.m12();
        final double m12 = this.m02 * rhs.m10() + this.m12 * rhs.m11() + this.m22 * rhs.m12();
        final double m20 = this.m00 * rhs.m20() + this.m10 * rhs.m21() + this.m20 * rhs.m22();
        final double m21 = this.m01 * rhs.m20() + this.m11 * rhs.m21() + this.m21 * rhs.m22();
        final double m22 = this.m02 * rhs.m20() + this.m12 * rhs.m21() + this.m22 * rhs.m22();
        dest.m00 = m00;
        dest.m01 = m01;
        dest.m02 = m02;
        dest.m10 = m10;
        dest.m11 = m11;
        dest.m12 = m12;
        dest.m20 = m20;
        dest.m21 = m21;
        dest.m22 = m22;
        return dest;
    }

}
