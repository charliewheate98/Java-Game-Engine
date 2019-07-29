package com.game.core;

import java.awt.Graphics;

public abstract class State {

	protected boolean bTransition = false;
	
	public State() {}
	
	public abstract void tick();
	public abstract void render(Graphics g);
}
