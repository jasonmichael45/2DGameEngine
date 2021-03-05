package dev.jason.tilegame;

import dev.jason.tilegame.entities.creatures.Player;
import dev.jason.tilegame.gfx.GameCamera;
import dev.jason.tilegame.input.KeyManager;
import dev.jason.tilegame.input.pathfinder.Path;
import dev.jason.tilegame.worlds.World;

public class Handler {
	private Game game;
	private World world;
	private Path path;
	
	public Handler(Game game){
		this.game = game;
	}
	
	public GameCamera getGameCamera(){
		return game.getGameCamera();
	}
	
	public KeyManager getKeyManager(){
		return game.getKeyManager();
	}
	
	public int getWidth(){
		return game.getWidth();
	}

	public int getHeight(){
		return game.getHeight();
	}
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
	
	public void setPath(Path path){
		this.path = path;
	}
	
	public Path getPath(){
		return path;
	}

}
