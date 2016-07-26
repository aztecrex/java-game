package com.banjocreek.d2;

public final class Vector2 {

	public final double x, y;

	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Vector2)
				&& ((Vector2)obj).x == this.x && ((Vector2)obj).y == this.y;
	}

	@Override
	public int hashCode() {
		return Double.hashCode(x) & (Double.hashCode(y) << 16);
	}
	
	public Vector2 plus(Vector2 rhs) {
		return new Vector2(this.x + rhs.x, this.y + rhs.y);
	}
	
}
