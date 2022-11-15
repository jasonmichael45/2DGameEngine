package dev.jason.tilegame.input.pathfinder;

import dev.jason.tilegame.Handler;
import dev.jason.tilegame.tiles.Tile;

public class PathTest {
	private Handler handler;
	private PathFinder finder;
	private Path path;
	private int selectedX = -1, selectedY = -1;
	private int lastFindX = -1, lastFindY = -1;
	
	public PathTest(Handler handler){
		this.handler = handler;
		finder = new AStarPathFinder(handler.getWorld(), 500, true);
	}
	
	public void handleMousePressed(int x, int y){
		if ((x < 0) || (y < 0) || (x >= handler.getWorld().getWidth()) || (y >= handler.getWorld().getHeight())) {
			return;
		}
	}
	
	public void handleMouseMoved(int x, int y){
		int mx = x;
		int my = y;
		
		int mouseTileX = 0;
		int mouseTileY = 0;
		
		if(handler.getGameCamera().getxOffset() == 0){
			mouseTileX = mx / Tile.TILEWIDTH;
		}else if(handler.getGameCamera().getxOffset() >= 4){
			mouseTileX = ((int) (handler.getGameCamera().getxOffset() + mx) / Tile.TILEWIDTH);
		}
		
		if(handler.getGameCamera().getyOffset() == 0){
			mouseTileY = my / Tile.TILEHEIGHT;
		}else if(handler.getGameCamera().getyOffset() >= 4){
			mouseTileY = ((int) (handler.getGameCamera().getyOffset() + my) / Tile.TILEHEIGHT);
		}
		
		if ((x < 0) || (y < 0) || (x >= handler.getWidth()) || (y >= handler.getHeight())) {
			return;
		}	
		
		if ((lastFindX != mouseTileX) || (lastFindY != mouseTileY)) {
			lastFindX = x;
			lastFindY = y;
			path = finder.findPath((int) handler.getWorld().getEntityManager().getPlayer().getxTile(), 
					(int) handler.getWorld().getEntityManager().getPlayer().getyTile(), mouseTileX, mouseTileY);
			handler.setPath(path);					
		}
		
	}
	
}
