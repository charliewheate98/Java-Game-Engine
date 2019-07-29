package com.game.input;

import com.game.core.Tile;

public class CollisionData {
	
	public CollisionData() {}
	
	public static boolean isIntersecting(int px, int py, int ps, Tile a) {		
		if(px + ps / 2 >= a.GetX() - a.GetWidth() / 2) {
			return true;
		}
		
		return false;
	}
}
