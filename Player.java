package com.game.core;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.game.animation.Animation;
import com.game.graphics.Spritesheet;
import com.game.input.Keys;

// initialises, updates and renders the player
public class Player extends MoveableObject {

	// Boolean variables that check the direction of movement
	private boolean bwalking_forward; // walking forward
	private boolean bwalking_right; // walking right
	private boolean bwalking_left; // walking left
	private boolean bwalking_backward;	// walking backward
	private boolean battacking; // checks if the player is attacking (pressing space)
	
	private BufferedImage[] idle; // Idle animation | the subimages for the idle animation 
	private BufferedImage[] walking_forward; // Walking forward animation | the subimages for the walking forward animation
	private BufferedImage[] walking_right; // Walking right animation | the subimages for the walking right animation
	private BufferedImage[] walking_left; // Walking left animation | the subimages for the walking left animation
	private BufferedImage[] walking_backward; // Walking backward animation | the subimages for the backward right animation
	
	private BufferedImage[] slash_forward; // Slash forward animation | the subimages for the forward slash animation
	private BufferedImage[] slash_right; // Slash right animation | the subimages for the right slash animation
	private BufferedImage[] slash_left; // Slash left animation | the subimages for the left slash animation
	private BufferedImage[] slash_backward; // Slash backward animation | the subimages for the backward slash animation
	
	// The animation class | stores the animation for interpolating a subimage animation using a spritesheet
	private Animation animation;
	
	// Constructer | initialise here
	public Player(Spritesheet ss) {	
		x = Game.WIDTH / 2 - 40; // x | position the player in the center of the screen
		y = Game.HEIGHT / 2 - 40; // y | position the player in the center of the screen
		
		// set all the players action to false
		battacking = false;
		bwalking_forward = false;
		bwalking_right = false;
		bwalking_left = false;
		bwalking_backward = false;
				
		// Initialise Idle animation frames
		idle = new BufferedImage[1];
		
		// Initialise walking frames
		walking_forward = new BufferedImage[8]; // Forward
		walking_right = new BufferedImage[8]; // Right
		walking_backward = new BufferedImage[8]; // Backward
		walking_left = new BufferedImage[8]; // Left

		// Initialise slash frames
		slash_forward = new BufferedImage[5]; // Forward
		slash_right = new BufferedImage[5];	// Right
		slash_backward = new BufferedImage[5]; // Backward
		slash_left = new BufferedImage[5]; // Left
			
		// grab the 1st frame of the walking forward animation as the idle stance
		idle[0] = Game.player_ss.grabSprite(1, 3, 64, 64);
			
		// loop through all the subimages on the spritesheet for the walking animation
		for(int i = 0; i < 8; i++) {
			walking_forward[i] = Game.player_ss.grabSprite(i+2, 3, Game.player_ss.getTileSize(), Game.player_ss.getTileSize());
			walking_right[i] = Game.player_ss.grabSprite(i+2, 4, Game.player_ss.getTileSize(), Game.player_ss.getTileSize());
			walking_backward[i] = Game.player_ss.grabSprite(i+2, 1, Game.player_ss.getTileSize(), Game.player_ss.getTileSize());
			walking_left[i] = Game.player_ss.grabSprite(i+2, 2, Game.player_ss.getTileSize(), Game.player_ss.getTileSize());
		}		
			
		// loop through all the subimages on the spritesheet for the slash animation
		for(int i = 0; i < 5; i++) {
			slash_forward[i] = Game.playerslash_ss.grabSprite(i+2, 3, Game.playerslash_ss.getTileSize(), Game.playerslash_ss.getTileSize());
			slash_right[i] = Game.playerslash_ss.grabSprite(i+2, 4, Game.playerslash_ss.getTileSize(), Game.playerslash_ss.getTileSize());
			slash_backward[i] = Game.playerslash_ss.grabSprite(i+2, 1, Game.playerslash_ss.getTileSize(), Game.playerslash_ss.getTileSize());
			slash_left[i] = Game.playerslash_ss.grabSprite(i+2, 2, Game.playerslash_ss.getTileSize(), Game.playerslash_ss.getTileSize());			
		}	

		// initialise the animation class
		animation = new Animation();
	}
	
	// tick | update the contents of this function every frame
	public void tick(double delta) 
	{
		// if D is pressed - then tib is walking right
		if(Keys.D) {
			bwalking_right = true; // set the walking right action to true
			
			// check if the player is attacking as well
			if(Keys.SPACE) {
				battacking = true; // if so, then set the attacking action to true
				animation.setFrames(slash_right); // set the animation to the slash right animation
				animation.setDelay(50); // set the delay between frames/subimages
			}
			else { // if space is not pressed
				battacking = false; // if so, then just play the walking right animation
				animation.setFrames(walking_right); // set the animation to the walking right animation
				animation.setDelay(50); // the delay between frames/subimages

				// move right
				this.Move(Direction.right, 2.0, delta);
			}					
		}	
		// if A is pressed - then tib is walking left
		else if(Keys.A) {
			bwalking_left = true; // set the walking left animation to true
			
			// check if the player is attacking as well
			if(Keys.SPACE) {
				battacking = true; // attacking action - true
				animation.setFrames(slash_left); // current animation - slash left
				animation.setDelay(50);	// delay between frames - 50
			}
			else { // if space is not pressed
				battacking = false; // attacking action - false
				animation.setFrames(walking_left); // current animation - walking left
				animation.setDelay(50);	// delay between frames - 50

				// Move left
				this.Move(Direction.left, 2.0, delta);
			}			
		}	
		else if(Keys.W) { // if pressed - tib is walking backward/up
			bwalking_backward = true; // walking action - backward
			
			// check if tib is attacking as well
			if(Keys.SPACE) {
				battacking = true; // attacking action - true
				animation.setFrames(slash_backward); // set the current animation - slash backward
				animation.setDelay(50);	// delay between frames - 50
			} else { // if not attacking
				battacking = false; // attacking action - false
				animation.setFrames(walking_backward); // current animation - walking backward
				animation.setDelay(50);	// delay between frames - 50

				// Move left down
				this.Move(Direction.down, 2.0, delta);
			}
		}
		else if(Keys.S) { // if pressed - tib is moving forward
			bwalking_forward = true; // walking forward action - true
			
			// Check if attacking as well
			if(Keys.SPACE) {
				battacking = true; // attacking action - true
				animation.setFrames(slash_forward); // current animation - slash forward
				animation.setDelay(50);	// delay between frames - 50
			} else { // if not attacking
				battacking = false; // attacking action - false
				animation.setFrames(walking_forward); // set current animation - walking forward
				animation.setDelay(50);	// delay between frames

				// Move up
				this.Move(Direction.up, 2.0, delta);
			}
		}		
		else { // if not keys are pressed
			// then set all the walking animation to false, because we must be idle.. surely
			bwalking_forward = false; 
			bwalking_backward = false;
			bwalking_right = false;
			bwalking_left = false;		
		}
		
		// if idle
		if(!bwalking_forward && !bwalking_backward && 
				!bwalking_right && !bwalking_left)
		{
			// if attacking
			if(Keys.SPACE) {
				battacking = true; // attacking action - true
				animation.setFrames(slash_forward); // current animation - slash forward
				animation.setDelay(50);	// delay between frames - 50
			} else { // if not attacking
				battacking = false; // attacking action - false
				animation.setFrames(idle); // set current animation - idle
				animation.setDelay(1); // delay between frames - 1
			}		
		}   
		
		// update the animation every frame
		animation.tick();
	}
	
	// Render the player and his different actions
	public void render(Graphics g) {
		// if the player is walking forward
		if(bwalking_forward) {
			// check if the player is attacking or not
			if(!battacking) // if not
				g.drawImage(walking_forward[animation.getCurrentFrame()], this.x, this.y, 64, 64, null); // render the walking forward animation
			else if(battacking) // if yes
				g.drawImage(slash_forward[animation.getCurrentFrame()], this.x, this.y, 64, 64, null); // render the slash animation
		}
		// if the player is walking backward
		else if(bwalking_backward) {
			if(!battacking) // if not attacking
				g.drawImage(walking_backward[animation.getCurrentFrame()], this.x, this.y, 64, 64, null); // render the walking backward animation
			else if(battacking) // if attacking
				g.drawImage(slash_backward[animation.getCurrentFrame()], this.x, this.y, 64, 64, null); // render the slash animation
		}
		// if the player is walking left
		else if(bwalking_left) {
			if(!battacking) // if not attacking
				g.drawImage(walking_left[animation.getCurrentFrame()], this.x, this.y, 64, 64, null); // render the walking left animation
			else if(battacking) // if attacking
				g.drawImage(slash_left[animation.getCurrentFrame()], this.x, this.y, 64, 64, null); // render the slash animation
		}
		// if walking right
		else if(bwalking_right) {
			if(!battacking) // if not attacking
				g.drawImage(walking_right[animation.getCurrentFrame()], this.x, this.y, 64, 64, null); // render the walking right animation
			else if(battacking) // if attacking
				g.drawImage(slash_right[animation.getCurrentFrame()], this.x, this.y, 64, 64, null); // render the slash animation
		}		
		// if idle
		else {
			if(!battacking) // if not attacking
				g.drawImage(idle[0], this.x, this.y, 64, 64, null); // render the idle animation
			else if(battacking)	// if attacking
				g.drawImage(slash_forward[animation.getCurrentFrame()], this.x, this.y, 64, 64, null); // render the slash animation
		}
	}
}
