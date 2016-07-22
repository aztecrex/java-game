package com.banjocreek.game.b;

import java.awt.geom.Point2D;

public final class GameObject {

	private Point2D velocity = new Point2D.Double(.25,-.25); // point as vector
	public Point2D position;
	
	public final double size = 0.5f;
	
	public GameObject(float px, float py) {
		this.position = new Point2D.Double(px,py); 
	}
	
	public GameObject(double px, double py) {
		this((float)px,(float)py);
	}
	
	void update(double dt) {
		position.setLocation(position.getX() + velocity.getX() * dt, position.getY() + velocity.getY() * dt);
	}
	
}
