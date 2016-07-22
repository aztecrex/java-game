package com.banjocreek.game.b;

import java.awt.EventQueue;

public final class GameEngine {

	private final GameWorld world;
	private final Display display;
	
	public GameEngine(GameWorld world, Display display) {
		this.world = world;
		this.display = display;
	}
	
	private static final int rate = 50;
	private static long tickmil = 1000/(long)rate;
	private static double ticksec = 1d/(double)rate;
	
	public void go() throws InterruptedException {
		
		
		long last = System.currentTimeMillis();
		for(;;) {
			final long next = last + tickmil;
			world.update(next, ticksec);
			while(System.currentTimeMillis() < next) {
				Thread.sleep(1);
			}
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					display.repaint();
				}
			});
			last = next;
		}
		
	}
	
	
}
