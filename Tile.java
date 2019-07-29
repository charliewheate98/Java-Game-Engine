package com.game.core;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Tile extends Object {
	
	public enum Material {
		normal,
		rough,
		slippery,
		sticky
	};
	
	protected boolean bRendered; // is the tile valid to be rendered to the screen
	protected boolean bCollidable; // is the tile a collidable tile?
	protected OABB    oabb;	// object aligned bounding box
	
	protected Material mat_type = Material.normal;

	protected BufferedImage tile_img;

	public void setRenderState(boolean val) { val = bRendered; }
	
	public abstract void tick();
	public abstract void render(Graphics g);
}
