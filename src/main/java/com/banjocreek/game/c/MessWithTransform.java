package com.banjocreek.game.c;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

class C extends JPanel  {

	final ComponentListener listener = new ComponentAdapter() {
		public void componentResized(java.awt.event.ComponentEvent e) {
			final Dimension sz = e.getComponent().getSize();
			final double sc = 40d;
			view.setToIdentity();
			view.scale(sc,-sc);
			view.translate(0, -sz.getHeight()/sc);
			view.translate(sz.getWidth()/2/sc, sz.getHeight()/2/sc);
			e.getComponent().invalidate();
		}
	};
	
	C() {
		addComponentListener(listener);
		setPreferredSize(new Dimension(300, 200));
	}
	
	static double d2r(double degrees) {
		return degrees * 2 * Math.PI / 360d;
	}

	private static final Shape model =  new Rectangle2D.Double(-.5,-.5,1,1);
	
	private static final Shape cross;
	static {
		Path2D c2d = new Path2D.Double();
		c2d.moveTo(-2, 0);
		c2d.lineTo(2, 0);
		c2d.moveTo(0, -2);
		c2d.lineTo(0, 2);
		cross = c2d;
	}
	
	private static final AffineTransform p1= new AffineTransform(), p2=new AffineTransform();
	static {
		p1.rotate(d2r(45d));
		p1.translate(1,0);


		p2.translate(1, 0);
		p2.rotate(d2r(45d));

	}

	
	private final AffineTransform view = new AffineTransform();
	
	
	private void draw(Graphics2D g2,Shape s, Color c) {
		g2.setPaint(c);
		g2.draw(view.createTransformedShape(s));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();
		try {
			
			g2.setStroke(new BasicStroke(1));
			
			Shape vcross = view.createTransformedShape(cross);
			g2.draw(vcross);
//
//			g2.transform(view);
//			g2.drawLine(0, -100, 0, 100);
//			g2.drawLine(-150, 0, 150, 0);
			
//			g2.setStroke(null);
			
			AffineTransform rotate = new AffineTransform();
			rotate.rotate(d2r(45));
			AffineTransform xlat = new AffineTransform();
			xlat.translate(1, 1);
			
			AffineTransform c1 = new AffineTransform();
			c1.translate(1, 1);
			c1.rotate(d2r(45));
			
			AffineTransform c2 = new AffineTransform();
			c2.concatenate(rotate);
			c2.concatenate(xlat);
			
			Shape s1 = rotate.createTransformedShape(model);
			draw(g2,s1,Color.black);

			Shape s2 = xlat.createTransformedShape(s1);
			draw(g2,s2,Color.blue);
			
//			Shape s3 = c1.createTransformedShape(model);
//			draw(g2,s3,Color.red);
			
			Shape s4 = xlat.createTransformedShape(model);
			draw(g2,s4,Color.green);
			

			Shape s5 = rotate.createTransformedShape(s4);
			draw(g2,s5,Color.orange);
			
			Shape s6 = c2.createTransformedShape(model);
			draw(g2,s6,Color.cyan);
			
//			Shape i1 = p1.createTransformedShape(model);
//			g2.setPaint(Color.black);
//			g2.draw(view.createTransformedShape(i1));
//			
//			Shape i2 = p2.createTransformedShape(model);
//			g2.setPaint(Color.red);
//			g2.draw(view.createTransformedShape(i2));
			
//			g2.setStroke(new BasicStroke(2));
//			g2.setPaint(Color.blue);
//			g2.drawRect(0, 0, 300, 200);
			
			
//			g2.setPaint(Color.blue);
//			g2.fillArc(0, 0, 1, 1, 0, 360);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("this handler doesn't provide any value");
		} finally {
			g2.dispose();
		}
		
	}
	
	
}

public class MessWithTransform extends JFrame {

    public MessWithTransform() {

        initUI();
    }

    private void initUI() {

        add(new C());

        setTitle("Simple Java 2D example");
//        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
            	MessWithTransform ex = new MessWithTransform();
            	ex.pack();
                ex.setVisible(true);
            }
        });
    }
}


