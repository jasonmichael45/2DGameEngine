package dev.jason.tilegame.worlds;

import java.awt.Graphics;

import javax.security.auth.x500.X500Principal;

import dev.jason.tilegame.Game;
import dev.jason.tilegame.Handler;
import dev.jason.tilegame.entities.EntityManager;
import dev.jason.tilegame.entities.creatures.Creature;
import dev.jason.tilegame.entities.creatures.Player;
import dev.jason.tilegame.entities.statics.Tree;
import dev.jason.tilegame.gfx.Assets;
import dev.jason.tilegame.tiles.ErrorTile;
import dev.jason.tilegame.tiles.Tile;
import dev.jason.tilegame.utils.Utils;

public class World {
	private Handler handler;
	private int width, height;
	private int[][] tiles;
	private int spawnX, spawnY;
	private boolean[][] occupiedTiles, visited;
	
	//Entities
	private EntityManager entityManager;
	
	public World(Handler handler, String path){
		this.handler = handler;
		loadWorld(path);
		entityManager = new EntityManager(handler, new Player(handler, spawnX * Tile.TILEWIDTH, spawnY * Tile.TILEHEIGHT));
		//entityManager.addEntity(new Tree(handler, 100, 250));
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void tick(){
		entityManager.tick();
	}
	
	public void render(Graphics g){
		//Only renders tiles within camera's FOV
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
		
		for(int y = yStart; y < yEnd; y++){
			for(int x = xStart; x < xEnd; x++){
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), 
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		
		//Entities
		entityManager.render(g);
	}
	
	public Tile getTile(int x, int y){	
		Tile t = Tile.tiles[tiles[x][y]];
		return t;
	}
	
	private void loadWorld(String path){
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		occupiedTiles = new boolean[width][height];
		visited = new boolean[width][height];
		
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public boolean isOccupied(int x, int y){
		if(x < 0 || y < 0){
			return true;
		}
		return occupiedTiles[x][y];
	}
	
	public void setOccupied(int x, int y, boolean b){
		if(x < 0 || y < 0){        
			return;
		}
		occupiedTiles[x][y] = b;
	}
	
	public void pathFinderVisited(int x, int y) {
		visited[x][y] = true;
	}
	
	public void clearVisited() {
		for (int x = 0; x < width; x++) {
			for (int y = 0;y < height; y++) {
				visited[x][y] = false;
			}
		}
	}
	
}
