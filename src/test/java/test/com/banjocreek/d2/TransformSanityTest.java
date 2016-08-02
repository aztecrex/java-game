package test.com.banjocreek.d2;

import static test.com.banjocreek.d2.VectorAssert.*;

import org.junit.Test;

import com.banjocreek.d2.ImmutableVec2;
import com.banjocreek.d2.Mat3;
import com.banjocreek.d2.MutableAffine;
import com.banjocreek.d2.MutableMat3;

public class TransformSanityTest {

    @Test
    public void testTransformingScalarMultiplyDoesntMatter() {

        /*
         * A square centered at (3,3);
         */
        final ImmutableVec2 tl = new ImmutableVec2(3 - .5, 3 + .5);
        final ImmutableVec2 tr = new ImmutableVec2(3 + .5, 3 + .5);
        final ImmutableVec2 br = new ImmutableVec2(3 + .5, 3 - .5);
        final ImmutableVec2 bl = new ImmutableVec2(3 - .5, 3 - .5);
        final ImmutableVec2 c = new ImmutableVec2(3, 3);

        /*
         * rotate, scale, and move
         */
        final MutableAffine builder = new MutableAffine();
        builder.translate(-3, -3); // move to center of system
        builder.rotate(Math.PI / 4); // rotate pi/4
        builder.scale(1.5, 1.5); // scale 1.5 each direction
        builder.translate(-1, .4); // move to new location

        final MutableMat3 xf = Mat3.mutable();
        xf.set(builder).times(1.35);

        /*
         * show off the beautiful thing
         */
        // System.out.println(xf);

        final double offs = 1.5 * Math.sqrt(2) / 2;
        assertNear(new ImmutableVec2(-1 - offs, .4), tl.transform(xf));
        assertNear(new ImmutableVec2(-1, .4 + offs), tr.transform(xf));
        assertNear(new ImmutableVec2(-1 + offs, .4), br.transform(xf));
        assertNear(new ImmutableVec2(-1, .4 - offs), bl.transform(xf));
        assertNear(new ImmutableVec2(-1, .4), c.transform(xf));

    }

    @Test
    public void testTransformingWorks() {

        /*
         * A square centered at (3,3);
         */
        final ImmutableVec2 tl = new ImmutableVec2(3 - .5, 3 + .5);
        final ImmutableVec2 tr = new ImmutableVec2(3 + .5, 3 + .5);
        final ImmutableVec2 br = new ImmutableVec2(3 + .5, 3 - .5);
        final ImmutableVec2 bl = new ImmutableVec2(3 - .5, 3 - .5);
        final ImmutableVec2 c = new ImmutableVec2(3, 3);

        /*
         * rotate, scale, and move
         */
        final MutableAffine xf = new MutableAffine();
        xf.translate(-3, -3); // move to center of system
        xf.rotate(Math.PI / 4); // rotate pi/4
        xf.scale(1.5, 1.5); // scale 1.5 each direction
        xf.translate(-1, .4); // move to new location

        /*
         * show off the beautiful thing
         */
        // System.out.println(xf);

        final double offs = 1.5 * Math.sqrt(2) / 2;
        assertNear(new ImmutableVec2(-1 - offs, .4), tl.transform(xf));
        assertNear(new ImmutableVec2(-1, .4 + offs), tr.transform(xf));
        assertNear(new ImmutableVec2(-1 + offs, .4), br.transform(xf));
        assertNear(new ImmutableVec2(-1, .4 - offs), bl.transform(xf));
        assertNear(new ImmutableVec2(-1, .4), c.transform(xf));

    }

}
