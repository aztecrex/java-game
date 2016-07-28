package test.com.banjocreek.d2;

import static org.junit.Assert.*;

import com.banjocreek.d2.Vec2;

public class VectorAssert {

    public static final double tol = 1e-15;

    public static void assertComponents(final double x, final double y, final Vec2 actual) {
        assertNear(x, actual.x());
        assertNear(y, actual.y());
    }

    public static void assertDifference(final Vec2 a, final Vec2 b, final Vec2 actual) {
        assertNear(a.x() - b.x(), actual.x());
        assertNear(a.y() - b.y(), actual.y());
    }

    public static void assertDistance(final Vec2 a, final Vec2 b, final double actual) {
        final double dx = b.x() - a.x();
        final double dy = b.y() - a.y();
        assertNear(Math.sqrt(dx * dx + dy * dy), actual);
    }

    public static void assertDistanceSquared(final Vec2 a, final Vec2 b, final double actual) {
        final double dx = b.x() - a.x();
        final double dy = b.y() - a.y();
        assertNear(dx * dx + dy * dy, actual);
    }

    public static void assertHeading(final Vec2 v, final double actual) {
        assertNear(Math.atan2(v.y(), v.x()), actual);
    }

    public static void assertMagnitude(final Vec2 v, final double actual) {
        assertNear(Math.sqrt(v.x() * v.x() + v.y() * v.y()), actual);
    }

    public static void assertMagnitudeSquared(final Vec2 v, final double actual) {
        assertNear(v.x() * v.x() + v.y() * v.y(), actual);
    }

    public static void assertNear(final double expected, final double actual) {
        assertEquals(expected, actual, tol);
    }

    public static void assertNear(final Vec2 expected, final Vec2 actual) {
        assertNear(expected.x(), actual.x());
        assertNear(expected.y(), actual.y());
    }

    public static void assertScale(final Vec2 v, final double s, final Vec2 actual) {
        assertNear(v.x() * s, actual.x());
        assertNear(v.y() * s, actual.y());
    }

    public static void assertScaleInverse(final Vec2 v, final double s, final Vec2 actual) {
        assertNear(v.x() / s, actual.x());
        assertNear(v.y() / s, actual.y());
    }

    public static void assertSum(final Vec2 a, final Vec2 b, final Vec2 actual) {
        assertNear(a.x() + b.x(), actual.x());
        assertNear(a.y() + b.y(), actual.y());
    }

}
