package test.com.banjocreek.d2;

import static org.junit.Assert.*;
import static test.com.banjocreek.d2.VectorAssert.*;

import org.junit.Before;
import org.junit.Test;

import com.banjocreek.d2.ImmutableVec2;
import com.banjocreek.d2.MutableVec2;
import com.banjocreek.d2.Vec2;

public class MutableVec2Test {

    private static final double scale = 1.47;

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
    private static final double x1 = -1.334, x2 = 7.14, y1 = 3.72, y2 = -1.91;

    private final MutableVec2 dest = new MutableVec2(0, 0);
    private final MutableVec2 v = new MutableVec2(0, 0);

    @Before
    public void setup() {
        this.v.set(v1);
        this.dest.zero();
    }

    @Test
    public void testConstruct() {
        assertComponents(x1, y1, new MutableVec2(x1, y1));
    }

    @Test
    public void testMinus() {
        final MutableVec2 actual = this.v.minus(v2);
        assertDifference(v1, v2, actual);
        assertSame(this.v, actual);
    }

    @Test
    public void testMinusTo() {
        final MutableVec2 actual = this.v.minus(v2, this.dest);
        assertDifference(v1, v2, actual);
        assertSame(this.dest, actual);
        assertNear(v1, this.v);
    }

    @Test
    public void testPlus() {
        final MutableVec2 actual = this.v.plus(v2);
        assertSum(v1, v2, actual);
        assertSame(this.v, actual);
    }

    @Test
    public void testPlusTo() {
        final MutableVec2 actual = this.v.plus(v2, this.dest);
        assertSum(v1, v2, actual);
        assertSame(this.dest, actual);
        assertNear(v1, this.v);
    }

    @Test
    public void testSafe() {
        final ImmutableVec2 actual = this.v.safe();
        assertNear(v1, actual);
    }

    @Test
    public void testScale() {
        final MutableVec2 actual = this.v.scale(scale);
        assertScale(v1, scale, actual);
        assertSame(this.v, actual);
    }

    @Test
    public void testScaleInverse() {
        final MutableVec2 actual = this.v.scaleInverse(scale);
        assertScaleInverse(v1, scale, actual);
        assertSame(this.v, actual);
    }

    @Test
    public void testScaleInverseTo() {
        final MutableVec2 actual = this.v.scaleInverse(scale, this.dest);
        assertScaleInverse(v1, scale, actual);
        assertSame(this.dest, actual);
        assertNear(v1, this.v);
    }

    @Test
    public void testScaleTo() {
        final MutableVec2 actual = this.v.scale(scale, this.dest);
        assertScale(v1, scale, actual);
        assertSame(this.dest, actual);
        assertNear(v1, this.v);
    }

    @Test
    public void testSet() {
        final MutableVec2 v = new MutableVec2(1500d, 1500d);
        v.set(v1);
        assertEquals(x1, v.x(), 0d);
        assertEquals(y1, v.y(), 0d);
    }

    @Test
    public void testZero() {
        this.v.zero();
        assertComponents(0, 0, this.v);
    }

}
