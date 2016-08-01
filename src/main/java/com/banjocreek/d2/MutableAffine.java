package com.banjocreek.d2;

public class MutableAffine implements Mat3 {

    private double m00 = 1, m11 = 1, m10 = 0, m01 = 0, m20 = 0, m21 = 0;

    public MutableAffine clear() {
        this.m00 = 1;
        this.m11 = 1;
        this.m10 = 0;
        this.m01 = 0;
        this.m20 = 0;
        this.m21 = 0;
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
        return 0;
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
        return 0;
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
        return 1;
    }

    public MutableAffine rotate(final double a) {
        return rotate(a, this);
    }

    public MutableAffine rotate(final double a, final MutableAffine dest) {
        final double c = Math.cos(a);
        final double s = Math.sin(a);

        final double l00 = c, l01 = s, l10 = -s, l11 = c, l20 = 0, l21 = 0;

        final double m00 = l00 * this.m00 + l10 * this.m01;
        final double m01 = l01 * this.m00 + l11 * this.m01;
        final double m10 = l00 * this.m10 + l10 * this.m11;
        final double m11 = l01 * this.m10 + l11 * this.m11;
        final double m20 = l00 * this.m20 + l10 * this.m21 + l20;
        final double m21 = l01 * this.m20 + l11 * this.m21 + l21;

        dest.m00 = m00;
        dest.m01 = m01;
        dest.m10 = m10;
        dest.m11 = m11;
        dest.m20 = m20;
        dest.m21 = m21;
        return dest;

    }

    public MutableAffine scale(final double x, final double y) {
        return scale(x, y, this);
    }

    public MutableAffine scale(final double x, final double y, final MutableAffine dest) {
        final double l00 = x, l01 = 0, l10 = 0, l11 = y, l20 = 0, l21 = 0;

        final double m00 = l00 * this.m00 + l10 * this.m01;
        final double m01 = l01 * this.m00 + l11 * this.m01;
        final double m10 = l00 * this.m10 + l10 * this.m11;
        final double m11 = l01 * this.m10 + l11 * this.m11;
        final double m20 = l00 * this.m20 + l10 * this.m21 + l20;
        final double m21 = l01 * this.m20 + l11 * this.m21 + l21;

        dest.m00 = m00;
        dest.m01 = m01;
        dest.m10 = m10;
        dest.m11 = m11;
        dest.m20 = m20;
        dest.m21 = m21;
        return dest;
    }

    /**
     * <p>
     * Set the values from an arbitrary 3x3 matrix. This conversion:
     * </p>
     * <ul>
     * <li>assumes {@link Mat3#m02()} and {@link Mat3#m12()} are zero. This
     * method ignores the actual values</li>
     * <li>divides all values by {@link Mat3#m22()}
     * </ul>
     *
     * @param from
     * @return
     */
    public MutableAffine set(final Mat3 from) {
        final double w = from.m22();
        this.m00 = from.m00() / w;
        this.m11 = from.m11() / w;
        this.m10 = from.m10() / w;
        this.m01 = from.m01() / w;
        this.m20 = from.m20() / w;
        this.m21 = from.m21() / w;
        return this;
    }

    public MutableAffine shear(final double x, final double y) {
        return shear(x, y, this);
    }

    public MutableAffine shear(final double x, final double y, final MutableAffine dest) {
        final double l00 = 1, l01 = y, l10 = x, l11 = 1, l20 = 0, l21 = 0;

        final double m00 = l00 * this.m00 + l10 * this.m01;
        final double m01 = l01 * this.m00 + l11 * this.m01;
        final double m10 = l00 * this.m10 + l10 * this.m11;
        final double m11 = l01 * this.m10 + l11 * this.m11;
        final double m20 = l00 * this.m20 + l10 * this.m21 + l20;
        final double m21 = l01 * this.m20 + l11 * this.m21 + l21;

        dest.m00 = m00;
        dest.m01 = m01;
        dest.m10 = m10;
        dest.m11 = m11;
        dest.m20 = m20;
        dest.m21 = m21;
        return dest;
    }

    public MutableAffine translate(final double x, final double y) {
        return translate(x, y, this);
    }

    public MutableAffine translate(final double x, final double y, final MutableAffine dest) {
        final double l00 = 1, l01 = 0, l10 = 0, l11 = 1, l20 = x, l21 = y;

        final double m00 = l00 * this.m00 + l10 * this.m01;
        final double m01 = l01 * this.m00 + l11 * this.m01;
        final double m10 = l00 * this.m10 + l10 * this.m11;
        final double m11 = l01 * this.m10 + l11 * this.m11;
        final double m20 = l00 * this.m20 + l10 * this.m21 + l20;
        final double m21 = l01 * this.m20 + l11 * this.m21 + l21;

        dest.m00 = m00;
        dest.m01 = m01;
        dest.m10 = m10;
        dest.m11 = m11;
        dest.m20 = m20;
        dest.m21 = m21;
        return dest;
    }

}
