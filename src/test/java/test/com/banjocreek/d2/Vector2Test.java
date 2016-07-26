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
    public void testMinus() {
        assertEquals(new Vector2(-2.5, 1.5), new Vector2(4.0, 4.0).minus(new Vector2(6.5, 2.5)));
    }

    @Test
    public void testPlus() {
        assertEquals(new Vector2(10.5, 6.5), new Vector2(4.0, 4.0).plus(new Vector2(6.5, 2.5)));
    }

    @Test
    public void testScale() {
        assertEquals(new Vector2(3.5 * 4.0, 3.5 * -1.7), new Vector2(4.0, -1.7).scale(3.5));
    }

    @Test
    public void testScaleInverse() {
        assertEquals(new Vector2(4.0 / 3.5, -1.7 / 3.5), new Vector2(4.0, -1.7).scaleInverse(3.5));
    }

}
