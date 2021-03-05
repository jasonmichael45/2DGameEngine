package dev.jason.tilegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import dev.jason.tilegame.Handler;
import dev.jason.tilegame.tiles.Tile;

public class KeyManager implements KeyListener{
	private boolean[] keys;
	public boolean up, down, left, right,
				   upL, upR, downL, downR;
	private int num = 0;
	private boolean locked = false;
	private Handler handler;
	
	public KeyManager(Handler handler) {
		keys = new boolean[256];
		this.handler = handler;
	}
	
	public void tick(){
		if(!locked){
			if(keys[KeyEvent.VK_W] && keys[KeyEvent.VK_A]){
				upL = true;
				locked = true;
			}else if(keys[KeyEvent.VK_W] && keys[KeyEvent.VK_D]){
				upR = true;
				locked = true;
			}else if(keys[KeyEvent.VK_S] && keys[KeyEvent.VK_A]){
				downL = true;
				locked = true;
			}else if(keys[KeyEvent.VK_S] && keys[KeyEvent.VK_D]){
				downR = true;
				locked = true;
			}else if(keys[KeyEvent.VK_W]){
				up = true;
				locked = true;
			}else if(keys[KeyEvent.VK_S]){
				down = true;
				locked = true;
			}else if(keys[KeyEvent.VK_A]){
				left = true;
				locked = true;
			}else if(keys[KeyEvent.VK_D]){
				right = true;
				locked = true;
			}
		}else{	
			if(num < 7){ // ((tile width / creature speed) - 1)
				num++;
			}else if(num == 7){
				locked = false;
				upL = false;
				upR = false;
				downL = false;
				downR = false;
				up = false;
				down = false;
				left = false;
				right = false;
				num = 0;
			}
		}		
	}
	

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true; // key is being pressed
		//System.out.println("Pressed");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false; // key is not being pressed
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
}
