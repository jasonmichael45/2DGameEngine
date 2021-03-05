package dev.jason.tilegame.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

import dev.jason.tilegame.Handler;
import dev.jason.tilegame.entities.creatures.Player;
import dev.jason.tilegame.tiles.Tile;

public class EntityManager {
	private Handler handler;
	private Player player;
	private static Player playerStatic;
	private ArrayList<Entity> entities;
	private Comparator<Entity> renderSorter = new Comparator<Entity>(){
		@Override
		public int compare(Entity a, Entity b) {
			if(a.getY() + a.getHeight() < b.getY() + b.getHeight()){
				return -1;
			}else{
				return 1;
			}
		}	
	};
	
	public EntityManager(Handler handler, Player player){
		this.handler = handler;
		this.player = player;
		this.playerStatic = player;
		entities = new ArrayList<Entity>();
		addEntity(player);
	}
	
	public void tick(){
		for(int i = 0; i < entities.size(); i++){
			Entity e = entities.get(i);
			e.tick();
		}
		//entities.sort(renderSorter);
	}
	
	int xp = 5;
	
	public void render(Graphics g){
//		g.setColor(Color.black);
//		g.fillRect(0, 0, 600, 400);
		
		for(Entity e : entities){
			e.render(g);
		}
		
		int counter = -1; // used for single-tile path preview
		
		for (int x = 0; x < handler.getWorld().getWidth();x++) {
			for (int y=0;y<handler.getWorld().getHeight();y++) {	
				if (handler.getPath() != null) {
					if (handler.getPath().contains(x, y)) {
						int xTile = 0;
						int yTile = 0;
						
						if(handler.getGameCamera().getxOffset() == 0){
							xTile = x / Tile.TILEWIDTH;
						}else if(handler.getGameCamera().getxOffset() >= 4){
							xTile = ((int) (handler.getGameCamera().getxOffset() + x) / Tile.TILEWIDTH);
						}
						
						if(handler.getGameCamera().getyOffset() == 0){
							yTile = y / Tile.TILEHEIGHT;
						}else if(handler.getGameCamera().getyOffset() >= 4){
							yTile = ((int) (handler.getGameCamera().getyOffset() + y) / Tile.TILEHEIGHT);
						}
						
						//Single-tile preview
						if(counter == handler.getPath().getLength() - 1){
							g.setColor(Color.blue);
							g.fillRect((x * 32) + 10 - (int) handler.getGameCamera().getxOffset(), 
									((y * 32) + 10 - (int) handler.getGameCamera().getyOffset()), 12, 12);
							counter = 0;
						}else{
							counter++;
						}
						
						//Full-path preview
						g.setColor(Color.blue);
						g.fillRect((x * 32) + 10 - (int) handler.getGameCamera().getxOffset(), 
								((y * 32) + 10 - (int) handler.getGameCamera().getyOffset()), 12, 12);

					}
				}	
			}
		}
	}
	
	public void addEntity(Entity e){
		entities.add(e);
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}
	
	public static Player getPlayerStatic(){
		return playerStatic;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
}
