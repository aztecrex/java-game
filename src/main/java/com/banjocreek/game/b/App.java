package com.banjocreek.game.b;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class App extends JFrame {

	private static final long serialVersionUID = 1L;

	public App() {
		add(new Display(new GameWorld()));
		setTitle("Go Game Go!");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				App ex = new App();
				ex.pack();
				ex.setVisible(true);
			}
		});
	}

}
