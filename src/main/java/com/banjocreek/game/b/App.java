package com.banjocreek.game.b;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
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
			Point clicked = e.getPoint();
			Point2D worldClicked = view.world(clicked);
			System.out.println(worldClicked);
			view.withCamera(worldClicked);
		};
		
	    public void mouseMoved(MouseEvent e) {
			Point mp = e.getPoint();
			Point2D worldTarget = view.world(mp);
			world.withTarget(worldTarget);	    	
	    }
	};

	
}
