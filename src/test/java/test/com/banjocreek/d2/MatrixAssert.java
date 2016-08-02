package test.com.banjocreek.d2;

import static org.junit.Assert.*;

import com.banjocreek.d2.ImmutableMat3;
import com.banjocreek.d2.Mat3;

public class MatrixAssert {

    public static void assertAffineRotation(final Mat3 m, final double a, final Mat3 actual) {
        final double cos = Math.cos(a);
        final double sin = Math.sin(a);
        final ImmutableMat3 lhs = Mat3.immutable(cos, sin, 0, -sin, cos, 0, 0, 0, 1);

        assertNear(lhs.times(m), actual);
    }

    public static void assertAffineScale(final Mat3 m, final double x, final double y, final Mat3 actual) {
        final ImmutableMat3 lhs = Mat3.immutable(x, 0, 0, 0, y, 0, 0, 0, 1);

        assertNear(lhs.times(m), actual);
    }

    public static void assertAffineShear(final Mat3 m, final double x, final double y, final Mat3 actual) {
        final ImmutableMat3 lhs = Mat3.immutable(1, y, 0, x, 1, 0, 0, 0, 1);

        assertNear(lhs.times(m), actual);
    }

    public static void assertAffineTranslate(final Mat3 m, final double x, final double y, final Mat3 actual) {
        final ImmutableMat3 lhs = Mat3.immutable(1, 0, 0, 0, 1, 0, x, y, 1);

        assertNear(lhs.times(m), actual);
    }

    public static void assertComponents(final double m00, final double m01, final double m02, final double m10,
            final double m11, final double m12, final double m20, final double m21, final double m22,
            final Mat3 actual) {
        assertNear(m00, actual.m00());
        assertNear(m01, actual.m01());
        assertNear(m02, actual.m02());
        assertNear(m10, actual.m10());
        assertNear(m11, actual.m11());
        assertNear(m12, actual.m12());
        assertNear(m20, actual.m20());
        assertNear(m21, actual.m21());
        assertNear(m22, actual.m22());

    }

    public static void assertDifference(final Mat3 a, final Mat3 b, final Mat3 actual) {
        assertNear(a.m00() - b.m00(), actual.m00());
        assertNear(a.m01() - b.m01(), actual.m01());
        assertNear(a.m02() - b.m02(), actual.m02());
        assertNear(a.m10() - b.m10(), actual.m10());
        assertNear(a.m11() - b.m11(), actual.m11());
        assertNear(a.m12() - b.m12(), actual.m12());
        assertNear(a.m20() - b.m20(), actual.m20());
        assertNear(a.m21() - b.m21(), actual.m21());
        assertNear(a.m22() - b.m22(), actual.m22());
    }

    public static void assertNear(final double expected, final double actual) {
        assertEquals(expected, actual, VectorAssert.tol);
    }

    public static void assertNear(final Mat3 expected, final Mat3 actual) {
        assertNear(expected.m00(), actual.m00());
        assertNear(expected.m01(), actual.m01());
        assertNear(expected.m02(), actual.m02());
        assertNear(expected.m10(), actual.m10());
        assertNear(expected.m11(), actual.m11());
        assertNear(expected.m12(), actual.m12());
        assertNear(expected.m20(), actual.m20());
        assertNear(expected.m21(), actual.m21());
        assertNear(expected.m22(), actual.m22());
    }

    public static void assertProduct(final Mat3 m, final double s, final Mat3 actual) {
        assertNear(m.m00() * s, actual.m00());
        assertNear(m.m01() * s, actual.m01());
        assertNear(m.m02() * s, actual.m02());
        assertNear(m.m10() * s, actual.m10());
        assertNear(m.m11() * s, actual.m11());
        assertNear(m.m12() * s, actual.m12());
        assertNear(m.m20() * s, actual.m20());
        assertNear(m.m21() * s, actual.m21());
        assertNear(m.m22() * s, actual.m22());
    }

    public static void assertProduct(final Mat3 a, final Mat3 b, final Mat3 actual) {
        assertNear(a.m00() * b.m00() + a.m10() * b.m01() + a.m20() * b.m02(), actual.m00());
        assertNear(a.m01() * b.m00() + a.m11() * b.m01() + a.m21() * b.m02(), actual.m01());
        assertNear(a.m02() * b.m00() + a.m12() * b.m01() + a.m22() * b.m02(), actual.m02());
        assertNear(a.m00() * b.m10() + a.m10() * b.m11() + a.m20() * b.m12(), actual.m10());
        assertNear(a.m01() * b.m10() + a.m11() * b.m11() + a.m21() * b.m12(), actual.m11());
        assertNear(a.m02() * b.m10() + a.m12() * b.m11() + a.m22() * b.m12(), actual.m12());
        assertNear(a.m00() * b.m20() + a.m10() * b.m21() + a.m20() * b.m22(), actual.m20());
        assertNear(a.m01() * b.m20() + a.m11() * b.m21() + a.m21() * b.m22(), actual.m21());
        assertNear(a.m02() * b.m20() + a.m12() * b.m21() + a.m22() * b.m22(), actual.m22());
    }

    public static void assertScale(final Mat3 a, final double scale, final Mat3 actual) {
        assertNear(a.m00() * scale, actual.m00());
        assertNear(a.m01() * scale, actual.m01());
        assertNear(a.m02() * scale, actual.m02());
        assertNear(a.m10() * scale, actual.m10());
        assertNear(a.m11() * scale, actual.m11());
        assertNear(a.m12() * scale, actual.m12());
        assertNear(a.m20() * scale, actual.m20());
        assertNear(a.m21() * scale, actual.m21());
        assertNear(a.m22() * scale, actual.m22());
    }

    public static void assertSum(final Mat3 a, final Mat3 b, final Mat3 actual) {
        assertNear(a.m00() + b.m00(), actual.m00());
        assertNear(a.m01() + b.m01(), actual.m01());
        assertNear(a.m02() + b.m02(), actual.m02());
        assertNear(a.m10() + b.m10(), actual.m10());
        assertNear(a.m11() + b.m11(), actual.m11());
        assertNear(a.m12() + b.m12(), actual.m12());
        assertNear(a.m20() + b.m20(), actual.m20());
        assertNear(a.m21() + b.m21(), actual.m21());
        assertNear(a.m22() + b.m22(), actual.m22());
    }

}
