package test.com.banjocreek.d2;

import static test.com.banjocreek.d2.VectorAssert.*;

import org.junit.Test;

import com.banjocreek.d2.ImmutableVec2;
import com.banjocreek.d2.Mat3;
import com.banjocreek.d2.MutableVec2;
import com.banjocreek.d2.Vec2;

public class ImmutableVec2Test {

    private static final double scale = 1.47;

    private static final ImmutableVec2 v;

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

    static {
        v = new ImmutableVec2(v1.x(), v1.y());
    }

    @Test
    public void testConstruct() {
        assertComponents(x1, y1, new ImmutableVec2(x1, y1));
    }

    @Test
    public void testMinus() {
        assertDifference(v1, v2, v.minus(v2));
    }

    @Test
    public void testNormalize() {

        assertNormal(v1, v.normalize());

    }

    @Test
    public void testPlus() {
        assertSum(v1, v2, v.plus(v2));
    }

    @Test
    public void testScale() {
        assertScale(v1, scale, v.scale(scale));
    }

    @Test
    public void testScaleInverse() {
        assertScaleInverse(v1, scale, v.scaleInverse(scale));
    }

    @Test
    public void testTransform() {
        final Mat3 xf = new RandomAffine();

        assertTransform(v1, xf, v.transform(xf));
    }

    @Test
    public void testTransformNormalized() {
        final RandomAffine xf = new RandomAffine();

        assertTransform(v1, xf, v.transformn(xf));
    }

    @Test
    public void testTruncateHit() {
        final double max = v1.magnitude() / 2d;
        assertTruncated(v, max, v.truncate(max));
    }

    @Test
    public void testTruncateMiss() {
        final double max = v1.magnitude() + 1d;
        assertTruncated(v, max, v.truncate(max));
    }

    @Test
    public void testUnsafe() {
        final MutableVec2 actual = v.unsafe();
        assertNear(v1, actual);
    }

}
