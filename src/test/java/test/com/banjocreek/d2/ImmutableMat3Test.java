package test.com.banjocreek.d2;

import static org.junit.Assert.*;

import org.junit.Test;

import com.banjocreek.d2.ImmutableMat3;

public class ImmutableMat3Test {

    @Test
    public void testConstruction() {

        final ImmutableMat3 m = new ImmutableMat3(0.0, 0.1, 0.2, 1.0, 1.1, 1.2, 2.0, 2.1, 2.2);

        assertEquals(0.0, m.m00(), 0.0);
        assertEquals(0.1, m.m01(), 0.0);
        assertEquals(0.2, m.m02(), 0.0);
        assertEquals(1.0, m.m10(), 0.0);
        assertEquals(1.1, m.m11(), 0.0);
        assertEquals(1.2, m.m12(), 0.0);
        assertEquals(2.0, m.m20(), 0.0);
        assertEquals(2.1, m.m21(), 0.0);
        assertEquals(2.2, m.m22(), 0.0);
    }

}
