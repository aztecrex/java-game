package test.com.banjocreek.d2;

import static org.junit.Assert.*;
import static test.com.banjocreek.d2.MatrixAssert.*;

import org.junit.Before;
import org.junit.Test;

import com.banjocreek.d2.Mat3;
import com.banjocreek.d2.MutableAffine;

public class MutableAffineTest {

    private static final Mat3 xf1 = new RandomAffine();

    private final MutableAffine xf = new MutableAffine(), dest = new MutableAffine();

    @Before
    public void setup() {
        this.xf.set(xf1);
        this.dest.clear();
    }

    @Test
    public void testRotate() {
        final double a = Math.PI / 3;
        final MutableAffine actual = this.xf.rotate(a);
        assertAffineRotation(xf1, a, actual);
        assertSame(this.xf, actual);
    }

    @Test
    public void testRotateTo() {
        final double a = Math.PI / 7;
        final MutableAffine actual = this.xf.rotate(a, this.dest);
        assertAffineRotation(xf1, a, actual);
        assertSame(this.dest, actual);
        assertNear(xf1, this.xf);
    }

    @Test
    public void testScale() {
        final double x = 3.3, y = -0.7;
        final MutableAffine actual = this.xf.scale(x, y);
        assertAffineScale(xf1, x, y, actual);
        assertSame(this.xf, actual);
    }

    @Test
    public void testScaleTo() {
        final double x = -4, y = 2.7;
        final MutableAffine actual = this.xf.scale(x, y, this.dest);
        assertAffineScale(xf1, x, y, actual);
        assertSame(this.dest, actual);
        assertNear(xf1, this.xf);
    }

    @Test
    public void testSet() {
        assertNear(this.xf, xf1);
    }

    @Test
    public void testShear() {
        final double x = 3.3, y = -0.7;
        final MutableAffine actual = this.xf.shear(x, y);
        assertAffineShear(xf1, x, y, actual);
        assertSame(this.xf, actual);
    }

    @Test
    public void testShearTo() {
        final double x = -4, y = 2.7;
        final MutableAffine actual = this.xf.shear(x, y, this.dest);
        assertAffineShear(xf1, x, y, actual);
        assertSame(this.dest, actual);
        assertNear(xf1, this.xf);
    }

    @Test
    public void testTranslate() {
        final double x = 3.3, y = -0.7;
        final MutableAffine actual = this.xf.translate(x, y);
        assertAffineTranslate(xf1, x, y, actual);
        assertSame(this.xf, actual);
    }

    @Test
    public void testTranslateTo() {
        final double x = -4, y = 2.7;
        final MutableAffine actual = this.xf.translate(x, y, this.dest);
        assertAffineTranslate(xf1, x, y, actual);
        assertSame(this.dest, actual);
        assertNear(xf1, this.xf);
    }

}
