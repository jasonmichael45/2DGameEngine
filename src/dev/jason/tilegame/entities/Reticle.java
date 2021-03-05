package dev.jason.tilegame.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.event.MouseInputListener;

import dev.jason.tilegame.Handler;

public class Reticle extends Entity{
	private Random rnd;
	private boolean mousePressed;
	public boolean xfiring, yfiring;
	boolean xCentered = true, yCentered;
	boolean xtargetReached, ytargetReached;
	int mouseX, mouseY;
	int xOrigin, yOrigin;
	int vpw, vph;
	int frameWidth = 100, frameHeight = 100;
	int xTarget, yTarget;
	int count = 0, county = 0;
	double velocity = 32;
	int xfp, yfp; // x and y firing origin
	int blah = 100;
	
	public Reticle(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		xOrigin = (int) x;
		yOrigin = (int) y;
		vpw = width;
		vph = height;
		rnd = new Random();
	}
	
	double move = 200;
	
	@Override
	public void tick() {
		System.out.println("xfp: " + xfp + ", xTarget: " + xTarget + " | yfp: " + yfp + ", yTarget " + yTarget);
		
		if(xfiring){
			updateX();
		}	
	}
boolean reached, reachedy;
	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int) xOrigin, (int) yOrigin, vpw, vph);
		
		g.setColor(Color.YELLOW);
		g.fillRect(xTarget, yTarget, 1, 1);
		/*g.fillRect(count, county,1 , 1 );	
		if(!reached){
			count += 48;
			if(count > 150){
				reached = true;
			}
		}else{
			count -= 12;
			if(count < 20){
				reached = false;
			}
		}
		if(!reachedy){
			county += 48;
			if(county > 120){
				reachedy = true;
			}
		}else{
			county -= 12;
			if(county < 20){
				reachedy = false;
			}
		}*/
		
		g.setColor(Color.GRAY);
		g.fillRect((int) mouseX, (int) mouseY, 1, 1);
		g.drawRect(mouseX - (frameWidth / 2), mouseY - (frameHeight / 2), frameWidth, frameHeight);
	}
	
	public void press(int x, int y){
		xOrigin = x;
		yOrigin = y;
        xfp = x;
        yfp = y;
		//xTarget = (rnd.nextInt(400) - 200);
		xTarget = (rnd.nextInt(frameWidth) - (frameWidth / 2)) + xfp;
		yTarget = (rnd.nextInt(yfp - (yfp - (frameHeight / 2))) + (yfp - (frameHeight / 2)));
		xfiring = true;
		yfiring = true;
		xtargetReached = false;
		ytargetReached = false;
	}
	
	public void release(){
		mousePressed = false;
	}
	
	
	public void updateX(){
		if(xtargetReached){
			
		}else{
			
		}
	}
	
	public void setOrigins(int x, int y){
		xOrigin = x;
		yOrigin = y;
	}
	
	public void setMousePosition(int x, int y){
		mouseX = x;
		mouseY = y;
	}
	
}

class Animation {
	private int speed, index;
	private long lastTime, timer;
	
	public Animation(int speed){
		this.speed = speed;
		index = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();
	}
	
	public void tick(){
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer > speed){
			//index++;
			timer = 0;
		}
	}
}
