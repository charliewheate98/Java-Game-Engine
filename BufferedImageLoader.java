package com.game.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {

	private BufferedImage image;

	public BufferedImage loadImage(String file) throws IOException {
		image = ImageIO.read(this.getClass().getResource(file));
		return image;
	}
}
