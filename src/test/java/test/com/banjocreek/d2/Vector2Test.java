package test.com.banjocreek.d2;

import static org.junit.Assert.*;

import org.junit.Test;

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

class Vector2 {

	public final double x, y;

	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Vector2 plus(Vector2 rhs) {
		return new Vector2(this.x + rhs.x, this.y + rhs.y);
	}

}
