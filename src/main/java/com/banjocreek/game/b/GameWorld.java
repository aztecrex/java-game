package com.banjocreek.game.b;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class GameWorld {

	private final List<GameObject> gameObjects = new ArrayList<>();
	
	public GameWorld() {
		
		this.gameObjects.addAll( IntStream.range(-2,3)
			.mapToDouble(n -> n * .6)
			.mapToObj(p -> new GameObject(p,p))
			.collect(Collectors.toList()));
	}
	
	public Stream<GameObject> gameObjects() {
		return gameObjects.stream();
	}

	public void update(long tick, double dt) {
		gameObjects.forEach(o -> o.update(dt));
	}
	
	
}
