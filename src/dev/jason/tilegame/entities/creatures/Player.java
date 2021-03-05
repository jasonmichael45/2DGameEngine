package dev.jason.tilegame.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.security.auth.x500.X500Principal;
import javax.swing.event.MouseInputListener;

import dev.jason.tilegame.Handler;
import dev.jason.tilegame.gfx.Animation;
import dev.jason.tilegame.gfx.Assets;
import dev.jason.tilegame.tiles.Tile;

public class Player extends Creature{

	//Animations
	private Animation currentAnimation;
	private Animation animDown, animUp, animLeft, animRight,
					animUpL, animUpR, animDownL, animDownR;
	public int occupiedXTile, occupiedYTile;
	public boolean isMoving = false;
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = 32;
		bounds.height = 32;
		
		//Animations
		animDown = new Animation(500, Assets.player_down);
		animUp = new Animation(500, Assets.player_up);
		animLeft = new Animation(500, Assets.player_left);
		animRight = new Animation(500, Assets.player_right);
		animUpL = new Animation(500, Assets.player_upL);
		animUpR = new Animation(500, Assets.player_upR);
		animDownL = new Animation(500, Assets.player_downL);
		animDownR = new Animation(500, Assets.player_downR);
	}
	
	@Override
	public void tick() {
		handler.getWorld().setOccupied((int) x / Tile.TILEWIDTH, (int) y / Tile.TILEWIDTH, false);
		occupiedXTile = (int) x / Tile.TILEWIDTH;
		occupiedYTile = (int) y / Tile.TILEWIDTH;
		
		if(currentAnimation != null){
			currentAnimation.tick();
		}
		
		//Movement
		if(handler.getGame().keysActive()){
			getKeyInput();
		}
		if(handler.getGame().mouseActive()){
			getMouseInput();
		}
		move();
		
		handler.getWorld().setOccupied((int) x / Tile.TILEWIDTH, (int) y / Tile.TILEWIDTH, true);	
		handler.getGameCamera().centerOnEntity(this);
		
		
	}
	
	int movecount = 0;
	private void getMouseInput(){
		xMove = 0;
		yMove = 0;
		
		if(!handler.getGame().getMouseManager().getActiveDirection().equals("none")){
			String activeDirection = handler.getGame().getMouseManager().getActiveDirection();
		
			switch(activeDirection){
			case "U":
				if(y > 0){
					yMove = -speed;
				}
				break;
			case "UR":
				if(y > 0 && (int) x < (handler.getWorld().getWidth() * Tile.TILEWIDTH - Tile.TILEWIDTH)){
					yMove = -speed;
					xMove = speed;
				}
				break;
			case "R":
				if((int) x < (handler.getWorld().getWidth() * Tile.TILEWIDTH - Tile.TILEWIDTH)){
					xMove = speed;
				}
				break;
			case "DR":
				if((int) y < (handler.getWorld().getHeight() * Tile.TILEHEIGHT - Tile.TILEHEIGHT) && (int) x < (handler.getWorld().getWidth() * Tile.TILEWIDTH - Tile.TILEWIDTH)){
					yMove = speed;
					xMove = speed;
				}
				break;
			case "D":
				if((int) y < (handler.getWorld().getHeight() * Tile.TILEHEIGHT - Tile.TILEHEIGHT)){
					yMove = speed;
				}
				break;
			case "DL":
				if((int) y < (handler.getWorld().getHeight() * Tile.TILEHEIGHT - Tile.TILEHEIGHT) && x > 0){
					yMove = speed;
					xMove = -speed;
				}
				break;
			case "L":
				if(x > 0){
					xMove = -speed;
				}
				break;
			case "UL":
				if(y > 0 && x > 0){
					yMove = -speed;
					xMove = -speed;
				}
				break;
			}
		}
	}
	
	private void getKeyInput(){
		xMove = 0;
		yMove = 0;
			
		if(handler.getKeyManager().upL){
			if(y > 0 && x > 0){
				yMove = -speed;
				xMove = -speed;
			}
		}else if(handler.getKeyManager().upR ){
			if(y > 0 && (int) x < (handler.getWorld().getWidth() * Tile.TILEWIDTH - Tile.TILEWIDTH)){
				yMove = -speed;
				xMove = speed;
			}
		}else if(handler.getKeyManager().downL){
			if((int) y < (handler.getWorld().getHeight() * Tile.TILEHEIGHT - Tile.TILEHEIGHT) && x > 0){
				yMove = speed;
				xMove = -speed;
			}
		}else if(handler.getKeyManager().downR){
			if((int) y < (handler.getWorld().getHeight() * Tile.TILEHEIGHT - Tile.TILEHEIGHT) && (int) x < (handler.getWorld().getWidth() * Tile.TILEWIDTH - Tile.TILEWIDTH)){
				yMove = speed;
				xMove = speed;
			}
		}else if(handler.getKeyManager().up){
			if(y > 0){
				yMove = -speed;
			}	
		}else if(handler.getKeyManager().down){
			if((int) y < (handler.getWorld().getHeight() * Tile.TILEHEIGHT - Tile.TILEHEIGHT)){
				yMove = speed;
			}
		}else if(handler.getKeyManager().left){
			if(x > 0){
				xMove = -speed;
			}
		}else if(handler.getKeyManager().right){
			if((int) x < (handler.getWorld().getWidth() * Tile.TILEWIDTH - Tile.TILEWIDTH)){
				xMove = speed;
			}
		}			
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		
		/*g.setColor(Color.red);
		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), 
				(int) (y + bounds.y - handler.getGameCamera().getyOffset()), 
				bounds.width, bounds.height);*/	
	}
	
	private BufferedImage getCurrentAnimationFrame(){
		if(!handler.getGame().getMouseManager().getActiveDirection().equals("none")){
			String activeDirection = handler.getGame().getMouseManager().getActiveDirection();
		
			switch(activeDirection){
			case "U":
				if(y > 0){
					currentAnimation = animUp;
				}
				break;
			case "UR":
				if(y > 0 && (int) x < (handler.getWorld().getWidth() * Tile.TILEWIDTH - Tile.TILEWIDTH)){
					currentAnimation = animUpR;
				}
				break;
			case "R":
				if((int) x < (handler.getWorld().getWidth() * Tile.TILEWIDTH - Tile.TILEWIDTH)){
					currentAnimation = animRight;
				}
				break;
			case "DR":
				currentAnimation = animDownR;
				break;
			case "D":
				if((int) y < (handler.getWorld().getHeight() * Tile.TILEHEIGHT - Tile.TILEHEIGHT)){
					currentAnimation = animDown;
				}
				break;
			case "DL":
				if((int) y < (handler.getWorld().getHeight() * Tile.TILEHEIGHT - Tile.TILEHEIGHT) && x > 0){
					currentAnimation = animDownL;
				}
				break;
			case "L":
				if(x > 0){
					currentAnimation = animLeft;
				}
				break;
			case "UL":
				if(y > 0 && x > 0){
					currentAnimation = animUpL;
				}
				break;
			}
		}else if(currentAnimation != null){
			return currentAnimation.getCurrentFrame();
		}else{
			currentAnimation = animDown;
		}
		
		return currentAnimation.getCurrentFrame();
		
	}
}
