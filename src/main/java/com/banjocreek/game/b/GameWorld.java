package com.banjocreek.game.b;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.banjocreek.game.c.Boid;
import com.banjocreek.game.c.Car;
import com.banjocreek.game.c.GameObject;

public final class GameWorld {

	private final List<Boid> boids;

	private final List<Car> cars;

	private final Car seeking;
	private final Car driving;

	private Point2D target;

	public GameWorld() {

		this.target = new Point2D.Double();

		this.boids = IntStream.range(-2, 3)
				.mapToObj(n -> new Point2D.Double(n * 1.5 + .5, n - .5))
				.map(Boid::new)
				.collect(Collectors.toList());
		;

		seeking = new Car(new Point2D.Double(3, 3), -Math.PI / 4);
		driving = new Car(new Point2D.Double(.5, .5), Math.PI / 2);
		cars = Arrays.asList(seeking, driving);

		boids.forEach(o -> o.seek(this.target));
		cars.forEach(o -> o.seek(this.target));
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
		boids.forEach(o -> o.seek(this.target));
		seeking.seek(this.target);
		return this;
	}

	public GameWorld withDanger(Point2D tpos) {
		boids.forEach(o -> o.avoid(tpos));
		return this;
	}

	public GameWorld withThrottle(final double amount) {
		driving.throttle(amount);
		return this;
	}

	public GameWorld withSteering(double amount) {
		driving.steer(amount);
		return this;
	}
	
	public GameWorld withStopped() {
		driving.stop();
		return this;
	}

	public Rectangle2D field() {
		
		final Rectangle2D rval = new Rectangle2D.Double(this.target.getX() - 2.5d, this.target.getY()- 2.5d,5d,5d);

		Stream.concat(boids.stream(), cars.stream())
			.forEach(o -> rval.add(o.position()));
		
		return rval;
		
	}
	
}
