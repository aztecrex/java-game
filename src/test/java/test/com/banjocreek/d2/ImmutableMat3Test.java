package test.com.banjocreek.d2;

import static test.com.banjocreek.d2.MatrixAssert.*;

import org.junit.Test;

import com.banjocreek.d2.ImmutableMat3;
import com.banjocreek.d2.Mat3;

public class ImmutableMat3Test {

    private static final Mat3 a = new RandomMat3(), b = new RandomMat3();

    private static final ImmutableMat3 m;

    static {
        m = new ImmutableMat3(a.m00(), a.m01(), a.m02(), a.m10(), a.m11(), a.m12(), a.m20(), a.m21(), a.m22());
    }

    @Test
    public void testConstruction() {

        final ImmutableMat3 actual = new ImmutableMat3(0.0, 0.1, 0.2, 1.0, 1.1, 1.2, 2.0, 2.1, 2.2);
        assertComponents(0.0, 0.1, 0.2, 1.0, 1.1, 1.2, 2.0, 2.1, 2.2, actual);
    }

    @Test
    public void testMinus() {
        assertDifference(a, b, m.minus(b));
    }

    @Test
    public void testPlus() {
        assertSum(a, b, m.plus(b));
    }

    @Test
    public void testTimes() {
        assertProduct(a, b, m.times(b));
    }

}
