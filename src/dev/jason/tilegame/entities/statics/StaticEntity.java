package dev.jason.tilegame.entities.statics;

import dev.jason.tilegame.Handler;
import dev.jason.tilegame.entities.Entity;

public abstract class StaticEntity extends Entity{
	public StaticEntity(Handler handler, float x, float y, int width, int height){
		super(handler, x, y, width, height);
	}
}
