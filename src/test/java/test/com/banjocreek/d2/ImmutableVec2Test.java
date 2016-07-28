package test.com.banjocreek.d2;

import static org.junit.Assert.*;

import org.junit.Test;

import com.banjocreek.d2.ImmutableVec2;
import com.banjocreek.d2.Vec2;

public class ImmutableVec2Test {

    @Test
    public void testConstruct() {

        final Vec2 v = new ImmutableVec2(3d, 4.5d);

        assertEquals(3d, v.x(), 0d);
        assertEquals(4.5d, v.y(), 0d);

    }

    @Test
    public void testMinus() {
        assertEquals(new ImmutableVec2(-2.5, 1.5), new ImmutableVec2(4.0, 4.0).minus(new ImmutableVec2(6.5, 2.5)));
    }

    @Test
    public void testPlus() {
        assertEquals(new ImmutableVec2(10.5, 6.5), new ImmutableVec2(4.0, 4.0).plus(new ImmutableVec2(6.5, 2.5)));
    }

    @Test
    public void testScale() {
        assertEquals(new ImmutableVec2(3.5 * 4.0, 3.5 * -1.7), new ImmutableVec2(4.0, -1.7).scale(3.5));
    }

    @Test
    public void testScaleInverse() {
        assertEquals(new ImmutableVec2(4.0 / 3.5, -1.7 / 3.5), new ImmutableVec2(4.0, -1.7).scaleInverse(3.5));
    }

}
