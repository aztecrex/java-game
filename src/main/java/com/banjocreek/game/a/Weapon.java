package com.banjocreek.game.a;

import java.util.List;
import java.util.function.BiConsumer;

public interface Weapon<S extends Solution> {

	void solve(BiConsumer<Integer, List<S>> action, int tag,  List<Target> targets);
	
	void execute (List<S> solutions);
	
}
