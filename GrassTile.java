package com.game.core;

import java.awt.Graphics;

import com.game.graphics.Spritesheet;

public class GrassTile extends Tile {

	public GrassTile(Spritesheet ss, int x, int y) {
		super();
		
		this.x = x;
		this.y = y;
		
		bCollidable = false;
		bRendered = true;
		
		tile_img = ss.grabSprite(1, 1, 32, 32);
		
		mat_type = Material.normal;
	}
	
	public void tick() {}
	
	public void render(Graphics g) {
		if(bRendered) {
			if(tile_img != null) {
				g.drawImage(tile_img, this.x, this.y, 64, 64, null);
			}
			else System.out.println("Error: Grass Tile is Equal to Null");	
		}
	}	
}
