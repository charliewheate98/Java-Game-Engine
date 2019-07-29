package com.game.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class OABB {

	private Rectangle rect;
	
	public OABB(int x, int y, int width, int height) {
		rect = new Rectangle(x, y, width, height);
	}
	
	private boolean isColliding(OABB a) {
		if(this.rect.intersects(a.rect)) {
			return true;
		}
		
		return false;
	}
	
	public void visualise(Graphics g) {		
		if(rect != null) {
			g.setColor(Color.RED);
			g.fillRect(rect.x, rect.y, rect.width, rect.height);
		}
	}
}
