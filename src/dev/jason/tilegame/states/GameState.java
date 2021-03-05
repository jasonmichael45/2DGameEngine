package dev.jason.tilegame.states;

import java.awt.Graphics;

import dev.jason.tilegame.Handler;
import dev.jason.tilegame.entities.creatures.Player;
import dev.jason.tilegame.entities.statics.Tree;
import dev.jason.tilegame.worlds.World;

public class GameState extends State{
	
	private World world;
	
	public GameState(Handler handler){
		super(handler);	
		world = new World(handler, "res/worlds/world1.txt");
		//world = new World(handler, "res/worlds/blank.txt");
		handler.setWorld(world);
	}
	
	@Override
	public void tick(){
		world.tick();
	}
	
	@Override
	public void render(Graphics g){
		world.render(g);
	}
}
