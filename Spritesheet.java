package com.game.graphics;

import java.awt.image.BufferedImage;

public class Spritesheet {
	
	private int tile_size;
	private BufferedImage image;
	
	public Spritesheet(BufferedImage image, int tile_size) {
		this.image = image;
		this.tile_size = tile_size;
	}	
	
	public BufferedImage grabSprite(int col, int row, int width, int height) {
		BufferedImage img = image.getSubimage((col * tile_size) - tile_size, (row * tile_size) - tile_size, width, height);
		return img;
	}
	
	public int getTileSize() {
		return tile_size;
	}
}
