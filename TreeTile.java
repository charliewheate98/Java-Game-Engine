package com.game.core;

import java.awt.Graphics;
import com.game.graphics.Spritesheet;

public class TreeTile extends Tile {

	public TreeTile(Spritesheet ss, int x, int y) {
		super();
		
		this.x = x;
		this.y = y;
		
		bRendered = true;
		bCollidable = true;
		
		oabb = new OABB(this.GetX(), this.GetY(), 64, 64);
		
		tile_img = ss.grabSprite(1, 7, 64, 64);
		
		mat_type = Material.normal;
	}
	
	public void tick() {}
	
	public void render(Graphics g) {
		oabb.visualise(g);
		
		if(bRendered) {
			if(tile_img != null) {
				g.drawImage(tile_img, this.x, this.y, 64, 64, null);
			}
			else System.out.println("Error: Grass Tile is Equal to Null");	
		}
	}	
	
}
