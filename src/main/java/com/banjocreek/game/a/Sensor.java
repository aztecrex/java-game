package com.banjocreek.game.a;

import java.util.List;
import java.util.function.Consumer;

public interface Sensor {

	void enable(Consumer<List<Target>> action);

	void disable();
	
}
