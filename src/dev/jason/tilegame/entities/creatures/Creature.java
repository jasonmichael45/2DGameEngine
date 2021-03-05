package dev.jason.tilegame.entities.creatures;

import dev.jason.tilegame.Game;
import dev.jason.tilegame.Handler;
import dev.jason.tilegame.entities.Entity;
import dev.jason.tilegame.states.GameState;
import dev.jason.tilegame.tiles.Tile;

public abstract class Creature extends Entity{
	public static final int DEFAULT_HEALTH = 10;
	public static final float DEFAULT_SPEED = 2.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 32, DEFAULT_CREATURE_HEIGHT = 32;
	
	protected int health;
	protected float speed;
	protected float xMove, yMove;
	
	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		health = DEFAULT_HEALTH;
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}
	
	public void move(){
		//Collision detection
		//moveX();
		//moveY();
		x += xMove;
		y += yMove;
	}
	
	public void moveX(){
		if(xMove > 0){ //Moving right
			int tx = (int)(x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
			
			if(!tileCollision(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT)
				&& !tileCollision(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
				x += xMove;
			}else{
				x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
			}
		}else if(xMove < 0){ //Moving left
			int tx = (int)(x + xMove + bounds.x) / Tile.TILEWIDTH;
			
			if(!tileCollision(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT)
				&& !tileCollision(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
				x += xMove;
			}else{
				x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x - 1;
			}
		}
	}
	
	public void moveY(){
		if(yMove < 0){ //Up
			int ty = (int)(y + yMove + bounds.y) / Tile.TILEHEIGHT;
			
			if(!tileCollision((int)(x + bounds.x) / Tile.TILEWIDTH, ty)
				&& !tileCollision((int)(x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
				y += yMove;
			}else{
				y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
			}
		}else if(yMove > 0){ //Down
			int ty = (int)(y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
			
			if(!tileCollision((int)(x + bounds.x) / Tile.TILEWIDTH, ty)
				&& !tileCollision((int)(x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
				y += yMove;
			}else{
				y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height;
			}
		}
	}
	
	protected boolean tileCollision(int x, int y){
		return handler.getWorld().getTile(x, y).isSolid();
	}
	
	// Getters and Setters

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
}
