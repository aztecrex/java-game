package com.banjocreek.game.b;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class App extends JFrame {

	private static final long serialVersionUID = 1L;
	
	
	public App(Display display)  {
		add(display);
		setTitle("Go Game Go!");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) throws Exception {

		final GameWorld world = new GameWorld();
		final Display display = new Display(world);

		
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				App ex = new App(display);
				ex.pack();
				ex.setVisible(true);
			}
		});
		
		Thread.sleep(1000);
		new GameEngine(world,display).go();
	}

}
