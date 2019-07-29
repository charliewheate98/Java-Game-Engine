package com.game.core;

import java.awt.Graphics;

public abstract class Object {

	protected int x;
	protected int y;
	protected int w;
	protected int h;
	protected int speed;
	protected double mass;
	protected int health;
	protected double friction;

	public Object() 
	{
		x = 0;
		y = 0;
		w = 32;
		h = 32;
		speed = 1;
		mass = 5.0;
		health = 10;
		friction = 3.0;
	}
		
	public int GetX() { return x; }
	public int GetY() { return y; }
	public int GetWidth() { return w; }
	public int GetHeight() { return h; }
	
	public abstract void tick();
	public abstract void render(Graphics g);
}
