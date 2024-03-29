package dev.jason.tilegame.entities.statics;

import java.awt.Graphics;

import dev.jason.tilegame.Handler;
import dev.jason.tilegame.gfx.Assets;
import dev.jason.tilegame.tiles.Tile;

public class Tree extends StaticEntity{

	public Tree(Handler handler, float x, float y){
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		
		//skipped entity collision tutorial
		bounds.x = 10;
		bounds.y = (int)(height / 1.5f);
		bounds.width = width - 20;
		bounds.height = (int)(height - height / 1.5f);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tree, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}

}
