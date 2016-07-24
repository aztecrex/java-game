package com.banjocreek.game.b;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import javax.swing.JFrame;

public class App extends JFrame implements GameDriver {

	private static final long serialVersionUID = 1L;
	
	private final Display display;
	private final GameWorld world;
	private final View view;
	
	public App() throws Exception {
		this.world = new GameWorld();
		this.view  = new View().withField(10).withSize(new Dimension(300,200));
		this.display = new Display(world, view);
		add(display);
		setTitle("Go Game Go!");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		display.addMouseListener(mouser);
		display.addMouseMotionListener(mouser);
		addKeyListener(keyer);
	}
	
	
	

	public static void main(String[] args) throws Exception {

		final App app = new App();
		final GameEngine engine = new GameEngine(app);
		
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				app.pack();
				app.setVisible(true);
			}
		});
		
		Thread.sleep(1000);
		engine.go();
	}

	@Override
	public void update(long next, double dt) {
		
		world.update(next, dt);
		
	}

	@Override
	public void reveal() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				App.this.repaint();
			}
		});
	}

	
	
	final MouseAdapter mouser = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			Point mp = e.getPoint();
			Point2D wmp = view.world(mp);
			if (e.getButton() == MouseEvent.BUTTON1)
				world.withTarget(wmp);
		};
		
		public void mouseEntered(MouseEvent e) {
			mouseMoved(e);
		};
		
		public void mouseExited(MouseEvent e) {
			world.withDanger(new Point2D.Double(1000,1000));
		};
		
	    public void mouseMoved(MouseEvent e) {
			Point mp = e.getPoint();
			Point2D worldTarget = view.world(mp);
			world.withDanger(worldTarget);	    	
	    }
	};

	KeyAdapter keyer = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			char c = e.getKeyChar();
			if (c >= '0' && c <= '9') {
				final double m = 1d / 9d;
				world.withThrottle(m * (c - '0'));
			} else if (e.isActionKey()) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					world.withSteering(.5d);
					break;
				case KeyEvent.VK_RIGHT:
					world.withSteering(-.5d);
					break;
				case KeyEvent.VK_UP:
					world
						.withSteering(0d)
						.withThrottle(.6d);
					break;
				case KeyEvent.VK_DOWN:
					world.withStopped();
					break;
				}
			}
		}
		
	};
}
