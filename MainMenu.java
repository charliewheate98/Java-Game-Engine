package com.game.core;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import com.game.graphics.BufferedImageLoader;
import com.game.input.Keys;

public class MainMenu extends State {

	private BufferedImage background;
	
	public MainMenu() {
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try {
			background = loader.loadImage("/Menu_Bgm.png");
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		AudioPlayer audioPlayer = new AudioPlayer("/bgm.wav");
		audioPlayer.play();
	} 

	public void tick() {
		if(Keys.SPACE) {
			bTransition = true;
		}
	}

	public void render(Graphics g) {
		g.drawImage(background, 0, 0, Game.WIDTH, Game.HEIGHT, null);
	}	
}
