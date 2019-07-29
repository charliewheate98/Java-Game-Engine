package com.game.animation;

import java.awt.image.BufferedImage;

public class Animation {

	private BufferedImage[] images;
	
	private int currentFrame;
	private long startTime;
	private long delay;
	
	public Animation () {}
	
	public void setFrames(BufferedImage[] frames) {
		this.images = frames;
		if(currentFrame > frames.length) currentFrame = 0;
	}
	
	public void setDelay(long d) {
		delay = d;
	}
	
	public void tick() {
		if(delay == -1) return;
		
		long elapsedTime = (System.nanoTime() - startTime) / 1000000;
		
		if(elapsedTime > delay) {
			currentFrame++;
			startTime = System.nanoTime();
		}
		
		if(currentFrame >= images.length) {
			currentFrame = 0;
		}
	}
	
	public int getCurrentFrame() {
		return currentFrame;
	}
}
