package dev.jason.tilegame.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.jason.tilegame.Game;
import dev.jason.tilegame.Handler;
import dev.jason.tilegame.states.GameState;
import dev.jason.tilegame.tiles.Tile;

public abstract class Entity {
	protected Handler handler;
	protected float x, y; // position
	protected int width, height; // size
	protected Rectangle bounds;
	protected int xTile, yTile; 
	
	public Entity(Handler handler, float x, float y, int width, int height){
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.xTile = (int) (x / Tile.TILEWIDTH);
		this.yTile = (int) (y / Tile.TILEHEIGHT);
		bounds = new Rectangle(0, 0, width, height);
	}
	
	public int getxTile() {
		return (int) (x / Tile.TILEWIDTH);
	}

	public void setxTile(int xTile) {
		this.xTile = xTile;
	}

	public int getyTile() {
		return (int) (y / Tile.TILEHEIGHT);
	}

	public void setyTile(int yTile) {
		this.yTile = yTile;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public abstract void tick();
	public abstract void render(Graphics g);
}
