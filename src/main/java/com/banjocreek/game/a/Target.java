package com.banjocreek.game.a;

public interface Target {
	
	long id();
	
	Disposition disposition();

	float range();
	
	Type type();
	
	enum Type {
		PEST, OUCH, YIKES;
	}
	
	enum Disposition {
		FRIEND, ENEMY;
	}
	
}
