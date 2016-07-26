package test.com.banjocreek.d2;

import static org.junit.Assert.*;

import org.junit.Test;

import com.banjocreek.d2.Vec2;

public class Vector2Test {

    @Test
    public void testConstruct() {

        final Vec2 v = new Vec2(3d, 4.5d);

        assertEquals(3d, v.x, 0d);
        assertEquals(4.5d, v.y, 0d);

    }

    @Test
    public void testDistance() {

        final Vec2 v1 = new Vec2(3.5, -1.8);
        final Vec2 v2 = new Vec2(-17, 13.77);

        assertEquals(v1.minus(v2).mag(), v1.distance(v2), 0d);
    }

    @Test
    public void testDistanceSquared() {

        final Vec2 v1 = new Vec2(3.5, -1.8);
        final Vec2 v2 = new Vec2(-17, 13.77);

        assertEquals((v2.x - v1.x) * (v2.x - v1.x) + (v2.y - v1.y) * (v2.y - v1.y), v1.distanceSquared(v2), 0d);
    }

    @Test
    public void testHeading() {

        final Vec2 v = new Vec2(12.7, -.153);

        assertEquals(Math.atan2(12.7, -.153), v.heading(), 0.0);

    }

    @Test
    public void testMagnitude() {

        final Vec2 v = new Vec2(-3.2, 1.7);

        assertEquals(Math.sqrt(-3.2 * -3.2 + 1.7 * 1.7), v.mag(), 0.0);

    }

    @Test
    public void testMagnitudeSquared() {

        final Vec2 v = new Vec2(-3.2, 1.7);

        assertEquals(-3.2 * -3.2 + 1.7 * 1.7, v.magSquared(), 0.0);

    }

    @Test
    public void testMinus() {
        assertEquals(new Vec2(-2.5, 1.5), new Vec2(4.0, 4.0).minus(new Vec2(6.5, 2.5)));
    }

    @Test
    public void testPlus() {
        assertEquals(new Vec2(10.5, 6.5), new Vec2(4.0, 4.0).plus(new Vec2(6.5, 2.5)));
    }

    @Test
    public void testScale() {
        assertEquals(new Vec2(3.5 * 4.0, 3.5 * -1.7), new Vec2(4.0, -1.7).scale(3.5));
    }

    @Test
    public void testScaleInverse() {
        assertEquals(new Vec2(4.0 / 3.5, -1.7 / 3.5), new Vec2(4.0, -1.7).scaleInverse(3.5));
    }

}
