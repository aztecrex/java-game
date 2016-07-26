package test.com.banjocreek.d2;

import static org.junit.Assert.*;

import org.junit.Test;

import com.banjocreek.d2.Vector2;

public class Vector2Test {

	@Test
	public void testConstruct() {

		final Vector2 v2 = new Vector2(3d, 4.5d);

		assertEquals(3d, v2.x, 0d);
		assertEquals(4.5d, v2.y, 0d);

	}

	
	
	@Test
	public void testPlus() {

		final Vector2 v = new Vector2(1.5d, 2.5d);
		final Vector2 actual = v.plus(new Vector2(2.5d, 1.5d));

		assertEquals(4d, actual.x, 0d);
		assertEquals(4d, actual.x, 0d);

	}
	
}

