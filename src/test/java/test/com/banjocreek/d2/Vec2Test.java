package test.com.banjocreek.d2;

import static org.junit.Assert.*;
import static test.com.banjocreek.d2.VectorAssert.*;

import org.junit.Test;

import com.banjocreek.d2.Vec2;

public class Vec2Test {

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
    public void testConstructImmutable() {
        assertComponents(x1, y1, Vec2.immutable(x1, y1));
    }

    @Test
    public void testConstructMutable() {
        assertComponents(x1, y1, Vec2.mutable(x1, y1));
    }

    @Test
    public void testConstructZeroImmutable() {
        assertComponents(0d, 0d, Vec2.immutable());
    }

    @Test
    public void testConstructZeroMutable() {
        assertComponents(0d, 0d, Vec2.mutable());
    }

    @Test
    public void testDistance() {
        assertDistance(v1, v2, v1.distance(v2));
    }

    @Test
    public void testDistanceSquared() {
        assertDistanceSquared(v1, v2, v1.distanceSquared(v2));
    }

    @Test
    public void testEast() {
        assertComponents(1, 0, Vec2.east);
    }

    @Test
    public void testHeading() {
        assertHeading(v1, v1.heading());
    }

    @Test
    public void testMagnitude() {
        assertEquals(Math.sqrt(x1 * x1 + y1 * y1), v1.magnitude(), 0.0);
    }

    @Test
    public void testMagnitudeSquared() {
        assertEquals(x1 * x1 + y1 * y1, v1.magnitudeSquared(), 0.0);
    }

    @Test
    public void testNorth() {
        assertComponents(0, 1, Vec2.north);
    }

    @Test
    public void testSouth() {
        assertComponents(0, -1, Vec2.south);
    }

    @Test
    public void testWest() {
        assertComponents(-1, 0, Vec2.west);
    }

}
