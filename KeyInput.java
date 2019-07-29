package com.game.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.game.core.Game;

public class KeyInput extends KeyAdapter {
	
	private Game game;
	
	public KeyInput(Game game) {
		this.game = game;
	}
	
	public void keyPressed(KeyEvent e) {
			game.keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e) {
		game.keyReleased(e);
	}
}
