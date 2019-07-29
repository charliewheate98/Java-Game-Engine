package com.game.core;

import java.awt.Graphics;

public class MoveableObject extends Object {
	
	public enum Direction {
		left,
		right,
		up,
		down,
		none
	};
	
	public Direction current_direction;
	
	public MoveableObject() {
		super();
		
		current_direction = Direction.none;
	}
	
	public void Move(Direction dir, double speed, double delta) {
		current_direction = dir;
		
		switch (current_direction) {
			case left:
				x -= speed * delta;
				break;
			case right:
				x += speed * delta;
				break;
			case up:
				y += speed * delta;
				break;
			case down:
				y -= speed * delta;
				break;
			default:
				break;
		}
	}
	
	public void tick() {}
	public void render(Graphics g) {}
}
