package test.com.banjocreek.d2;

import static org.junit.Assert.*;

import org.junit.Test;

import com.banjocreek.d2.ImmutableVec2;
import com.banjocreek.d2.MutableVec2;
import com.banjocreek.d2.Vec2;

public class MutableVec2Test {

    private static final Vec2 dv = new Vec2() {

        @Override
        public double x() {
            return dx;
        }

        @Override
        public double y() {
            return dy;
        }
    };
    private static final double dx = -1.334;
    private static final double dy = 3.72;

    private static final double scale = 1.47;

    @Test
    public void testConstruct() {

        final Vec2 v = new MutableVec2(3d, 4.5d);

        assertEquals(3d, v.x(), 0.0);
        assertEquals(4.5d, v.y(), 0.0);

    }

    @Test
    public void testMinus() {
        final MutableVec2 v = new MutableVec2(4.0, 4.1);
        final Vec2 expected = new ImmutableVec2(4.0 - dx, 4.1 - dy);
        final MutableVec2 actual = v.minus(dv);
        assertEqualValue(expected, actual);
        assertSame(v, actual);
    }

    @Test
    public void testMinusTo() {
        final MutableVec2 v = new MutableVec2(4.0, 4.1);
        final MutableVec2 dest = new MutableVec2(1110.0, 2220.0);
        final Vec2 expected = new ImmutableVec2(4.0 - dx, 4.1 - dy);
        final MutableVec2 actual = v.minus(dv, dest);
        assertEqualValue(expected, actual);
        assertSame(dest, actual);
        assertEquals(4.0, v.x(), 0.0);
        assertEquals(4.1, v.y(), 0.0);
    }

    @Test
    public void testPlus() {
        final MutableVec2 v = new MutableVec2(4.0, 4.1);
        final Vec2 expected = new ImmutableVec2(4.0 + dx, 4.1 + dy);
        final MutableVec2 actual = v.plus(dv);
        assertEqualValue(expected, actual);
        assertSame(v, actual);
    }

    @Test
    public void testPlusTo() {
        final MutableVec2 v = new MutableVec2(4.0, 4.1);
        final MutableVec2 dest = new MutableVec2(1110.0, 2220.0);
        final Vec2 expected = new ImmutableVec2(4.0 + dx, 4.1 + dy);
        final MutableVec2 actual = v.plus(dv, dest);
        assertEqualValue(expected, actual);
        assertSame(dest, actual);
        assertEquals(4.0, v.x(), 0.0);
        assertEquals(4.1, v.y(), 0.0);
    }

    @Test
    public void testSafe() {
        final ImmutableVec2 v = new MutableVec2(1d, -1d).safe();
        assertEquals(1d, v.x(), 0d);
        assertEquals(-1d, v.y(), 0d);
    }

    @Test
    public void testScale() {
        final MutableVec2 v = new MutableVec2(4.0, -4.1);
        final Vec2 expected = new ImmutableVec2(4.0 * scale, -4.1 * scale);
        final MutableVec2 actual = v.scale(scale);
        assertEqualValue(expected, actual);
        assertSame(v, actual);
    }

    @Test
    public void testScaleInverse() {
        final MutableVec2 v = new MutableVec2(4.0, -4.1);
        final Vec2 expected = new ImmutableVec2(4.0 / scale, -4.1 / scale);
        final MutableVec2 actual = v.scaleInverse(scale);
        assertEqualValue(expected, actual);
        assertSame(v, actual);
    }

    @Test
    public void testScaleInverseTo() {
        final MutableVec2 v = new MutableVec2(4.0, -4.1);
        final MutableVec2 dest = new MutableVec2(1110.0, 2220.0);
        final Vec2 expected = new ImmutableVec2(4.0 * scale, -4.1 * scale);
        final MutableVec2 actual = v.scale(scale, dest);
        assertEqualValue(expected, actual);
        assertSame(dest, actual);
        assertEquals(4.0, v.x(), 0.0);
        assertEquals(-4.1, v.y(), 0.0);
    }

    @Test
    public void testScaleTo() {
        final MutableVec2 v = new MutableVec2(4.0, -4.1);
        final MutableVec2 dest = new MutableVec2(1110.0, 2220.0);
        final Vec2 expected = new ImmutableVec2(4.0 * scale, -4.1 * scale);
        final MutableVec2 actual = v.scale(scale, dest);
        assertEqualValue(expected, actual);
        assertSame(dest, actual);
        assertEquals(4.0, v.x(), 0.0);
        assertEquals(-4.1, v.y(), 0.0);
    }

    @Test
    public void testSet() {
        final MutableVec2 v = new MutableVec2(1500d, 1500d);
        v.set(dv);
        assertEquals(dx, v.x(), 0d);
        assertEquals(dy, v.y(), 0d);
    }

    @Test
    public void testZero() {

        final MutableVec2 v = new MutableVec2(1d, -1d).zero();
        assertEquals(0d, v.x(), 0d);
        assertEquals(0d, v.y(), 0d);
    }

    private void assertEqualValue(final Vec2 expected, final MutableVec2 actual) {
        assertEquals(expected.x(), actual.x(), 1e-15);
        assertEquals(expected.y(), actual.y(), 1e-15);
    }
}
