package com.game.core;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;

import com.game.graphics.BufferedImageLoader;
import com.game.graphics.Spritesheet;
import com.game.input.KeyInput;
import com.game.input.Keys;
import com.game.world.Level;

public class Game extends Canvas implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 650;
	public static final int HEIGHT = 450;
	public static final String TITLE = "The Legend of King Tib!";
	
	private boolean running;
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);	
	private Thread thread;
	
	private BufferedImage spritesheet = null;
	private BufferedImage player_spritesheet = null;
	private BufferedImage playerslash_spritesheet = null;

	private StateHandler stateHandler;
	
	public static Spritesheet ss;
	public static Spritesheet player_ss;
	public static Spritesheet playerslash_ss;
	
	private double delta;
	
	public void init() {
		delta = 0;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try {
			spritesheet = loader.loadImage("/spritesheet.png");
			player_spritesheet = loader.loadImage("/player_spritesheet.png");			
			playerslash_spritesheet = loader.loadImage("/playerslash_spritesheet.png");
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		addKeyListener(new KeyInput(this));
		
		ss = new Spritesheet(spritesheet, 32);
		player_ss = new Spritesheet(player_spritesheet, 64);
		playerslash_ss = new Spritesheet(playerslash_spritesheet, 64);
		
		stateHandler = new StateHandler();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		
		JFrame jframe = new JFrame(game.TITLE);
		
		jframe.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		jframe.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		jframe.setMaximumSize(new Dimension(WIDTH, HEIGHT));		
		
		jframe.add(game);
		jframe.pack();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setVisible(true);
		jframe.setLocationRelativeTo(null);
		jframe.setResizable(false);
		
		game.start();
	}
	
	public synchronized void start() {
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}	
	
	public synchronized void stop() {
		if(!running)
			return;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.exit(1);
	}	
	
	public void tick() {
		stateHandler.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null)
		{
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		// back buffer
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		
		// in-game content
		stateHandler.render(g);		

		g.dispose();
		bs.show();
	}

	public void run() {
		init();
		
		long lastTime = System.nanoTime();
		final double numTicks = 60.0;
		double ns = 100000000 / numTicks;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		while(running) {
			long now = System.nanoTime();
			delta += (lastTime - now) / ns;
			lastTime = now;
			
			tick();
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		
		stop();
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_ESCAPE)
			System.exit(-1);
		
		if(key == KeyEvent.VK_E)
			Keys.E = true;
		if(key == KeyEvent.VK_W)
			Keys.W = true;
		if(key == KeyEvent.VK_A)
			Keys.A = true;
		if(key == KeyEvent.VK_S)
			Keys.S = true;
		if(key == KeyEvent.VK_D)
			Keys.D = true;
		if(key == KeyEvent.VK_SPACE)
			Keys.SPACE = true;		
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_E)
			Keys.E = false;
		if(key == KeyEvent.VK_W)
			Keys.W = false;
		if(key == KeyEvent.VK_A)
			Keys.A = false;
		if(key == KeyEvent.VK_S)
			Keys.S = false;
		if(key == KeyEvent.VK_D)
			Keys.D = false;
		if(key == KeyEvent.VK_SPACE)
			Keys.SPACE = false;	
	}

	// Not Used
	public void keyTyped(KeyEvent e) {}
}
