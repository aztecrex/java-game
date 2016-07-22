package com.banjocreek.game.b;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

public class View {

	private double displayW = 300;
	private double displayH = 200;
	private Point2D camera = new Point2D.Double();
	private double field = 5; // meters to display

	public View withCamera(double x, double y) {
		this.camera.setLocation(x, y);
		return this;
	}
	
	public View withField(double field) {
		this.field = field;
		return this;
	}
	
	public View withSize(Dimension2D size) {
		displayW = size.getWidth();
		displayH = size.getHeight();
		return this;
	}
	
	public Shape project(Shape obj) {
		
		final double scale = Math.min(displayW, displayH)/field;
		final double pageX = displayW/scale;
		final double pageY = displayH/scale;

		AffineTransform projection = new AffineTransform();
			// THESE ARE IN REVERSE ORDER OF APPLICATION
		projection.scale(scale,-scale); 		// 3. scale the whole thing
		projection.translate(0, -pageY);  		// 2. move down a whole page because of flipped origin
		projection.translate(pageX/2, pageY/2); // 1. center the view on the origin (TODO use camera)
		
		return projection.createTransformedShape(obj);
	}
	
	public Shape cross() {
		final Path2D c2d = new Path2D.Double();
		final double extent = field/2;
		c2d.moveTo(-extent, 0);
		c2d.lineTo(extent, 0);
		c2d.moveTo(0, -extent);
		c2d.lineTo(0, extent);
		return c2d;
	}
	
	
}
