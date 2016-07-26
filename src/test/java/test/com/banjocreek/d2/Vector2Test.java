package test.com.banjocreek.d2;

import static org.junit.Assert.*;

import org.junit.Test;

import com.banjocreek.d2.Vector2;

public class Vector2Test {

    @Test
    public void testConstruct() {

        final Vector2 v = new Vector2(3d, 4.5d);

        assertEquals(3d, v.x, 0d);
        assertEquals(4.5d, v.y, 0d);

    }

    @Test
    public void testHeading() {

        final Vector2 v = new Vector2(12.7, -.153);

        assertEquals(Math.atan2(12.7, -.153), v.heading(), 0.0);

    }

    @Test
    public void testMagnitude() {

        final Vector2 v = new Vector2(-3.2, 1.7);

        assertEquals(Math.sqrt(-3.2 * -3.2 + 1.7 * 1.7), v.mag(), 0.0);

    }

    @Test
    public void testMagnitudeSquared() {

        final Vector2 v = new Vector2(-3.2, 1.7);

        assertEquals(-3.2 * -3.2 + 1.7 * 1.7, v.magSquared(), 0.0);

    }

    @Test
    public void testPlus() {

        final Vector2 v = new Vector2(1.5d, 2.5d);
        final Vector2 actual = v.plus(new Vector2(2.5d, 1.5d));

        assertEquals(4d, actual.x, 0d);
        assertEquals(4d, actual.x, 0d);

    }

}
