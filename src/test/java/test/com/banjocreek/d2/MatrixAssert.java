package test.com.banjocreek.d2;

import static org.junit.Assert.*;

import com.banjocreek.d2.Mat3;

public class MatrixAssert {

    public static void assertComponents(final double m00, final double m01, final double m02, final double m10,
            final double m11, final double m12, final double m20, final double m21, final double m22,
            final Mat3 actual) {
        assertNear(0.0, actual.m00());
        assertNear(0.1, actual.m01());
        assertNear(0.2, actual.m02());
        assertNear(1.0, actual.m10());
        assertNear(1.1, actual.m11());
        assertNear(1.2, actual.m12());
        assertNear(2.0, actual.m20());
        assertNear(2.1, actual.m21());
        assertNear(2.2, actual.m22());

    }

    public static void assertDifference(final Mat3 a, final Mat3 b, final Mat3 actual) {
        assertNear(a.m00() - b.m00(), actual.m00());
        assertNear(a.m01() - b.m01(), actual.m01());
        assertNear(a.m02() - b.m02(), actual.m02());
        assertNear(a.m10() - b.m10(), actual.m00());
        assertNear(a.m11() - b.m11(), actual.m01());
        assertNear(a.m12() - b.m12(), actual.m02());
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

    public static void assertProduct(final Mat3 a, final Mat3 b, final Mat3 actual) {
        assertNear(a.m00() * b.m00() + a.m10() * b.m01() + a.m20() * b.m02(), actual.m00());
        assertNear(a.m01() * b.m00() + a.m11() * b.m01() + a.m21() * b.m02(), actual.m00());
        assertNear(a.m02() * b.m00() + a.m12() * b.m01() + a.m22() * b.m02(), actual.m00());
        assertNear(a.m00() * b.m10() + a.m10() * b.m11() + a.m20() * b.m12(), actual.m00());
        assertNear(a.m01() * b.m10() + a.m11() * b.m11() + a.m21() * b.m12(), actual.m00());
        assertNear(a.m02() * b.m10() + a.m12() * b.m11() + a.m22() * b.m12(), actual.m00());
        assertNear(a.m00() * b.m20() + a.m10() * b.m21() + a.m20() * b.m22(), actual.m00());
        assertNear(a.m01() * b.m20() + a.m11() * b.m21() + a.m21() * b.m22(), actual.m00());
        assertNear(a.m02() * b.m20() + a.m12() * b.m21() + a.m22() * b.m22(), actual.m00());
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
