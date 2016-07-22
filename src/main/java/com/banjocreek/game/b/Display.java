package com.banjocreek.game.b;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public final class Display extends JPanel {

	private static final long serialVersionUID = 1L;

	private final GameWorld world;
	
	private final View view = new View().withField(10).withSize(new Dimension(300,200));
	
	public Display(GameWorld world) {
		this.world = world;
		addComponentListener(listener);
		setPreferredSize(new Dimension(300,200));
		addMouseListener(mouser);
	}

	private static final Stroke hudStroke;
	static {
		final float[] dash = {3f,7f};
		hudStroke = new BasicStroke(1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10.0f, dash , 0.0f);
	}
	private static final Shape model = new Rectangle2D.Double(-.5,-.5, 1, 1);
	
	private final Shape element(GameObject obj) {
		final AffineTransform x = new AffineTransform();
		// IN REVERSE OF APPLICATION
		x.translate(obj.position.getX(), obj.position.getY());
		x.scale(obj.size, obj.size);
		return x.createTransformedShape(model);
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.setStroke(new BasicStroke(1));
		g2d.setPaint(Color.BLUE);
		try {
			world.gameObjects()
				.map(this::element)
				.map(view::project)
				.forEach(g2d::draw);

			g2d.setPaint(Color.GRAY);
			g2d.setStroke(hudStroke);
			g2d.draw(view.project(view.cross()));
			g2d.draw(view.project(new Rectangle2D.Double(-5,-5,10,10)));
		
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

	final MouseListener mouser = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			Point clicked = e.getPoint();
			System.out.println("clicked " + clicked);
			Point2D world = view.world(clicked);
			System.out.println("world " + world);
			view.withCamera(world);
			Display.this.repaint();
		};
	};
	
}
