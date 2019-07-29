package com.game.world;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import com.game.core.Game;
import com.game.core.GrassTile;
import com.game.core.LargeRockTile;
import com.game.core.SmallRockTile;
import com.game.core.State;
import com.game.core.Tile;
import com.game.core.TreeTile;

public class Level extends State {

	private int x;
	private int y;
	
	private int[][] map;
	private int tileSize;
	private int map_width;
	private int map_height;
	
	public static Vector<Tile> tiles = new Vector<Tile>();
	
	public Level(String file, int tileSize) {
		
		x = 0;
		y = 0;
		
		this.tileSize = tileSize;	
		
		try {
			
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			map_width = Integer.parseInt(reader.readLine());
			map_height = Integer.parseInt(reader.readLine());
			map = new int[map_height][map_width];
			
			String delims = " ";
			
			for(int row = 0; row < map_height; row++) {
				String line = reader.readLine();
				String[] tokens = line.split(delims);
				
				for(int col = 0; col < map_width; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		}	
		
		for(int row = 0; row < map_height; row++) {
			for(int col = 0; col < map_width; col++) {
				
				int tile_type = map[row][col];
				
				if(tile_type == 0)
					tiles.add(new GrassTile(Game.ss, x + col * tileSize, y + row * tileSize));
				else if(tile_type == 1) {
					tiles.add(new GrassTile(Game.ss, x + col * tileSize, y + row * tileSize));
					tiles.add(new TreeTile(Game.ss, x + col * tileSize, y + row * tileSize));					
				}
				else if(tile_type == 2) {
					tiles.add(new GrassTile(Game.ss, x + col * tileSize, y + row * tileSize));
					tiles.add(new SmallRockTile(Game.ss, x + col * tileSize, y + row * tileSize));
				}
				else if(tile_type == 3) {
					tiles.add(new GrassTile(Game.ss, x + col * tileSize, y + row * tileSize));
					tiles.add(new LargeRockTile(Game.ss, x + col * tileSize, y + row * tileSize));
				}
			}
		}
	}
	
	public void tick() {}
	
	public void render(Graphics g) {
		for(int i = 0; i < tiles.size(); i++) {
			tiles.get(i).render(g);
		}
	}
}


