package com.banjocreek.game.b;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.banjocreek.game.c.Boid;
import com.banjocreek.game.c.GameObject;

public final class GameWorld {

	private final List<Boid> gameObjects;

	private Point2D target;

	public GameWorld() {

		this.target = new Point2D.Double();

		this.gameObjects = IntStream.range(-2, 3)
				.mapToObj(n -> new Point2D.Double(n * 1.5 + .5, n - .5))
				.map(Boid::new)
				.collect(Collectors.toList());
		;

		gameObjects.forEach(o -> o.seek(target));
	}

	public Stream<GameObject> gameObjects() {
		return gameObjects.stream().map(Function.identity());
	}

	public Point2D target() {
		return target;
	}

	public void update(long tick, double dt) {
		gameObjects.forEach(o -> o.update(dt));
	}

	public GameWorld withTarget(Point2D tpos) {
		this.target = tpos;
		gameObjects.forEach(o -> o.seek(tpos));
		return this;
	}

	public GameWorld withDanger(Point2D tpos) {
		gameObjects.forEach(o -> o.avoid(tpos));
		return this;
	}

}
