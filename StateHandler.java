package com.game.core;

import java.awt.Graphics;
import java.util.Vector;

import com.game.input.CollisionData;
import com.game.world.Level;

public class StateHandler {

	private enum States {
		MENU,
		LEVEL
	};
	
	public static Player player;
	
	private Vector<State> states = new Vector<State>();
	
	private States currentState;
	
	public StateHandler() {
		currentState = States.MENU;
		
		states.add(new MainMenu());
		states.add(new Level("res/Level.txt", 64));
		
		player = new Player(Game.player_ss);
	}
	
	public void tick() {
		if(currentState == States.MENU) {
			if(states.get(0).bTransition == true)
				currentState = States.LEVEL;
		}
		
		switch (currentState) {
		case MENU:
			states.get(0).tick();
			break;
		case LEVEL:
			states.get(1).tick();
			player.tick(1.0);
			
			for(int i = 0; i < Level.tiles.size(); i++) {
				if(CollisionData.isIntersecting(player.x, player.y, 64, Level.tiles.get(i))
						&& Level.tiles.get(i).bCollidable) {
					player.x -= player.speed;
				} 
			}
					
			break;
		default:
			break;
		}	
	}
	
	public void render(Graphics g) {
		switch (currentState) {
		case MENU:
			states.get(0).render(g);
			break;
		case LEVEL:
			states.get(1).render(g);
			player.render(g);
			break;
		default:
			break;
		}	
	}
}
