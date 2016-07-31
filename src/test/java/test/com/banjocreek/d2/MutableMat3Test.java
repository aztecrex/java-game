package test.com.banjocreek.d2;

import static org.junit.Assert.*;
import static test.com.banjocreek.d2.MatrixAssert.*;

import org.junit.Before;
import org.junit.Test;

import com.banjocreek.d2.Mat3;
import com.banjocreek.d2.MutableMat3;

public class MutableMat3Test {

    private static final Mat3 a = new RandomMat3(), b = new RandomMat3();

    private final MutableMat3 m = new MutableMat3(0, 0, 0, 0, 0, 0, 0, 0, 0),
            dest = new MutableMat3(0, 0, 0, 0, 0, 0, 0, 0, 0);

    @Before
    public void setup() {
        this.m.set(a);
        this.dest.set(0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    @Test
    public void testConstruction() {

        final MutableMat3 m = new MutableMat3(0.0, 0.1, 0.2, 1.0, 1.1, 1.2, 2.0, 2.1, 2.2);

        assertNear(0.0, m.m00());
        assertNear(0.1, m.m01());
        assertNear(0.2, m.m02());
        assertNear(1.0, m.m10());
        assertNear(1.1, m.m11());
        assertNear(1.2, m.m12());
        assertNear(2.0, m.m20());
        assertNear(2.1, m.m21());
        assertNear(2.2, m.m22());
    }

    @Test
    public void testMinus() {

        final MutableMat3 actual = this.m.minus(b);
        assertDifference(a, b, actual);
        assertSame(this.m, actual);
    }

    @Test
    public void testMinusTo() {
        final MutableMat3 actual = this.m.minus(b, this.dest);
        assertDifference(a, b, actual);
        assertSame(this.dest, actual);
        assertNear(a, this.m);
    }

    @Test
    public void testPlus() {

        final MutableMat3 actual = this.m.plus(b);
        assertSum(a, b, actual);
        assertSame(this.m, actual);
    }

    @Test
    public void testPlusTo() {
        final MutableMat3 actual = this.m.plus(b, this.dest);
        assertSum(a, b, actual);
        assertSame(this.dest, actual);
        assertNear(a, this.m);
    }

    @Test
    public void testSet() {
        final MutableMat3 actual = this.m.set(b);
        assertNear(b, actual);
        assertSame(this.m, actual);
    }

    @Test
    public void testSetComponents() {
        final MutableMat3 actual = this.m.set(0, 1, 2, 3, 4, 5, 6, 7, 8);
        assertComponents(0, 1, 2, 3, 4, 5, 6, 7, 8, actual);
        assertSame(this.m, actual);
    }

    @Test
    public void testTimes() {

        final MutableMat3 actual = this.m.times(b);
        assertProduct(a, b, actual);
        assertSame(this.m, actual);
    }

    @Test
    public void testTimesTo() {
        final MutableMat3 actual = this.m.times(b, this.dest);
        assertProduct(a, b, actual);
        assertSame(this.dest, actual);
        assertNear(a, this.m);
    }

}
