package com.banjocreek.game.b;

import java.awt.geom.Point2D;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.banjocreek.game.c.Boid;
import com.banjocreek.game.c.Car;
import com.banjocreek.game.c.GameObject;

public final class GameWorld {

	private final List<Boid> boids;

	private final List<Car> cars;
	
	private Point2D target;

	public GameWorld() {

		this.target = new Point2D.Double();

		this.boids = IntStream.range(-2, 3)
				.mapToObj(n -> new Point2D.Double(n * 1.5 + .5, n - .5))
				.map(Boid::new)
				.collect(Collectors.toList());
		;
		
		cars = Collections.singletonList(new Car(new Point2D.Double(.5,.5), Math.PI/3));

		boids.forEach(o -> o.seek(target));
		cars.forEach(o -> o.withThrust());
	}

	public Stream<GameObject> gameObjects() {
		return Stream.concat(boids.stream(), cars.stream());
	}

	public Point2D target() {
		return target;
	}

	public void update(long tick, double dt) {
		boids.forEach(o -> o.update(dt));
		cars.forEach(o -> o.update(dt));
	}

	public GameWorld withTarget(Point2D tpos) {
		this.target = tpos;
		boids.forEach(o -> o.seek(tpos));
		return this;
	}

	public GameWorld withDanger(Point2D tpos) {
		boids.forEach(o -> o.avoid(tpos));
		return this;
	}

}
