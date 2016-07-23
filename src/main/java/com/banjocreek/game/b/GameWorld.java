package com.banjocreek.game.b;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.banjocreek.game.c.Boid;

public final class GameWorld {

	private final List<Boid> gameObjects;
	
	public GameWorld() {
		
		this.gameObjects = IntStream.range(-2,3)
			.mapToObj(n -> new Point2D.Double(n * 1.5, n))
			.map(Boid::new)
			.collect(Collectors.toList());
		;
	}
	
	public Stream<GameObject> gameObjects() {
		return gameObjects.stream().map(Function.identity());
	}

	public void update(long tick, double dt) {
		gameObjects.forEach(o -> o.update(dt));
	}
	
	public GameWorld withTarget(Point2D tpos) {
		gameObjects.forEach(o -> o.seek(tpos));
		return this;
	}
	
}
