package dev.jason.tilegame.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	//Static stuff
	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new GrassTile(0);
	public static Tile dirtTile = new DirtTile(1);
	public static Tile rockTile = new RockTile(2);
	
	//class
	public static final int TILEWIDTH = 32, TILEHEIGHT = 32;
	
	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture, int id){
		this.texture = texture;
		this.id = id;
		
		if(id > -1){
			tiles[id] = this;
		}
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g, int x, int y){
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	public boolean isSolid(){
		// Tiles are not solid by default, but some override this and are true (e.g. StoneTile)
		return false;
	}
	
	public int getId(){
		return this.id;
	}
	
}
