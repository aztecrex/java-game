package com.banjocreek.game.b;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import com.banjocreek.game.c.Boid;
import com.banjocreek.game.c.Car;
import com.banjocreek.game.c.GameObject;

public final class Display extends JPanel {

	private static final long serialVersionUID = 1L;

	private final GameWorld world;
	
	private final View view;
	
	public Display(GameWorld world, View view) {
		this.world = world;
		this.view = view;
		addComponentListener(listener);
		setPreferredSize(new Dimension(300,200));
	}

	
	private static final Map<Object, Shape> models = new HashMap<>();
	static {
		final Path2D boid = new Path2D.Double();
		boid.moveTo(-.3, .1);
		boid.lineTo(.3, 0);
		boid.lineTo(-.3, -.1);
		boid.closePath();
		models.put(Boid.class, boid);
		
		final Rectangle2D car = new Rectangle2D.Double(-.3,-.1, .6, .2);
		models.put(Car.class, car);
		
	}
	
	private final Shape element(GameObject obj) {
		
		Point2D pos  = obj.position();
		double rot = obj.rotation();
		Shape model = models.get(obj.getClass());
		
		final AffineTransform x = new AffineTransform();
		// IN REVERSE OF APPLICATION
		x.translate(pos.getX(), pos.getY());
		x.rotate(rot);
		return x.createTransformedShape(model);
	}
	
	private final Shape targetModel = new Ellipse2D.Double(-.2, -.2, .4, .4);
	private final Shape target(Point2D where) {
		final AffineTransform x = new AffineTransform();
		x.translate(where.getX(), where.getY());
		return x.createTransformedShape(targetModel);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g.create();
		try {
			g2d.setStroke(new BasicStroke(1));
			g2d.setPaint(Color.BLUE);
			world.gameObjects()
				.map(this::element)
				.map(view::project)
				.forEach(g2d::draw);

			g2d.setPaint(Color.BLACK);
			g2d.fill(view.project(target(world.target())));
			
		} finally {
			g2d.dispose();
		}
		
		
	}
	
	final ComponentListener listener = new ComponentAdapter() {
		public void componentResized(java.awt.event.ComponentEvent e) {
			view.withSize(e.getComponent().getSize());
			Display.this.repaint();
		}
	};

	
}
