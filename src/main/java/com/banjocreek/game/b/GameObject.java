package com.banjocreek.game.b;

import java.awt.geom.Point2D;

public interface GameObject {

	Point2D position();
	
	double rotation();
	
	void update(double dt);
	
}
