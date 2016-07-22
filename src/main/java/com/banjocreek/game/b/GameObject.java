package com.banjocreek.game.b;

public final class GameObject {

	public final float posX,posY;
	
	public final float size = 0.5f;
	
	public GameObject(float px, float py) {
		this.posX = px;
		this.posY = py;
	}
	
	public GameObject(double px, double py) {
		this((float)px,(float)py);
	}
}
