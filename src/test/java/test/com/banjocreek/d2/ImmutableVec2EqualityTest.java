package test.com.banjocreek.d2;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.junit.Test;

import com.banjocreek.d2.ImmutableVec2;
import com.banjocreek.d2.Vec2;

public class ImmutableVec2EqualityTest {

    private static final Vec2 control, copy;
    private static final Collection<ImmutableVec2> diffs;

    static {
        control = new ImmutableVec2(12.0, -3.0);
        copy = new ImmutableVec2(12.0, -3.0);

        diffs = Arrays.asList(new ImmutableVec2(13.2, -3.0),
                new ImmutableVec2(12.0, -4.3),
                new ImmutableVec2(0.0, 0.0));

    }

    @Test
    public void testEqualCommutes() {
        assertTrue(copy.equals(control));
    }

    @Test
    public void testEqualRef() {
        assertTrue(control.equals(control));
    }

    @Test
    public void testEqualValue() {
        assertTrue(control.equals(copy));
    }

    @Test
    public void testHashEqual() {
        assertEquals(control.hashCode(), copy.hashCode());
    }

    @Test
    public void testHashRepeatable() {
        assertEquals(control.hashCode(), control.hashCode());
    }

    @Test
    public void testNotEqual() {

        assertTrue(diffs.stream().filter(control::equals).collect(Collectors.toList()).isEmpty());
        assertTrue(diffs.stream().filter(d -> d.equals(control)).collect(Collectors.toList()).isEmpty());
    }

    @Test
    public void testNotEqualNonVector() {
        assertFalse(control.equals("hello, world"));
    }

}
