package test.com.banjocreek.d2;

import static test.com.banjocreek.d2.MatrixAssert.*;

import org.junit.Test;

import com.banjocreek.d2.ImmutableMat3;
import com.banjocreek.d2.Mat3;
import com.banjocreek.d2.MutableMat3;

public class Mat3Test {

    private static final Mat3 a = new RandomMat3();

    @Test
    public void testConstructImmutable() {
        final ImmutableMat3 actual = Mat3.immutable(a.m00(),
                a.m01(),
                a.m02(),
                a.m10(),
                a.m11(),
                a.m12(),
                a.m20(),
                a.m21(),
                a.m22());
        assertNear(a, actual);
    }

    @Test
    public void testConstructMutable() {
        final MutableMat3 actual = Mat3.mutable(a.m00(),
                a.m01(),
                a.m02(),
                a.m10(),
                a.m11(),
                a.m12(),
                a.m20(),
                a.m21(),
                a.m22());
        assertNear(a, actual);
    }

    @Test
    public void testConstructZeroImmutable() {
        final ImmutableMat3 actual = Mat3.immutable();
        assertComponents(0, 0, 0, 0, 0, 0, 0, 0, 0, actual);
    }

    @Test
    public void testConstructZeroMutable() {
        final MutableMat3 actual = Mat3.mutable();
        assertComponents(0, 0, 0, 0, 0, 0, 0, 0, 0, actual);
    }

    @Test
    public void testIdentity() {
        assertComponents(1, 0, 0, 0, 1, 0, 0, 0, 1, Mat3.identity);
    }

}
