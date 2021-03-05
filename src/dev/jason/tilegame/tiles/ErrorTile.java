package dev.jason.tilegame.tiles;

import dev.jason.tilegame.gfx.Assets;

public class ErrorTile extends Tile{
	public ErrorTile(int id){
		super(Assets.error, id);
	}
	
	@Override
	public boolean isSolid(){
		return true;
	}
}
