package test.com.banjocreek.d2;

import static org.junit.Assert.*;

import org.junit.Test;

import com.banjocreek.d2.Vec2;

public class Vec2DefaultsTest {

    private static final Vec2 v1 = new Vec2() {

        @Override
        public double x() {
            return x1;
        }

        @Override
        public double y() {
            return y1;
        }

    };

    private static final Vec2 v2 = new Vec2() {

        @Override
        public double x() {
            return x2;
        }

        @Override
        public double y() {
            return y2;
        }

    };

    private static final double x1 = 1.7, x2 = -4.3, y1 = -2.22, y2 = 4.0;

    @Test
    public void testDistance() {

        final double x = x1 - x2;
        final double y = y1 - y2;
        final double expected = Math.sqrt(x * x + y * y);

        assertEquals(expected, v1.distance(v2), 0d);
    }

    @Test
    public void testDistanceSquared() {

        final double x = x1 - x2;
        final double y = y1 - y2;
        final double expected = x * x + y * y;

        assertEquals(expected, v1.distanceSquared(v2), 0d);
    }

    @Test
    public void testHeading() {
        assertEquals(Math.atan2(y1, x1), v1.heading(), 0.0);
    }

    @Test
    public void testMagnitude() {
        assertEquals(Math.sqrt(x1 * x1 + y1 * y1), v1.magnitude(), 0.0);
    }

    @Test
    public void testMagnitudeSquared() {
        assertEquals(x1 * x1 + y1 * y1, v1.magnitudeSquared(), 0.0);
    }

}
