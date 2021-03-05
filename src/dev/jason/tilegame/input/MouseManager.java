package dev.jason.tilegame.input;

//import AStarPathFinder;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.event.MouseInputListener;

import dev.jason.tilegame.Handler;
import dev.jason.tilegame.entities.EntityManager;
import dev.jason.tilegame.entities.Projectile;
import dev.jason.tilegame.entities.Reticle;
import dev.jason.tilegame.input.pathfinder.PathTest;
import dev.jason.tilegame.tiles.Tile;

public class MouseManager implements MouseInputListener{
	private Handler handler;
	private Rectangle playerRect;
	private PathTest pathTest;
	private PathTest pathTest2;
	private boolean unlocked = false;
	private int counter = 0;
	private int index = 0;
	private String[] directions;
	private String activeDirection = "none";
	private boolean shoot;
	private int mx, my;
	private Reticle r;

	public MouseManager(Handler handler){
		this.handler = handler;
		this.pathTest = new PathTest(handler);
		//this.pathTest2 = new PathTest(handler);
		r = new Reticle(this.handler, 300, 200, 1, 1);
		handler.getWorld().getEntityManager().addEntity(r);
	}
	
	PointerInfo a;
	Point m;
	int speed = 50;
	long lastTime = System.currentTimeMillis(), timer;
	
	public void tick(){
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer > speed){
			r.press(300, 200);
			timer = 0;
		}

		if(unlocked){
			if(index < directions.length){
				if(counter < (32 / handler.getWorld().getEntityManager().getPlayer().getSpeed()) - 1){
					setActiveDirection(directions[index]);
					counter++;
				}else{
					index++;
					counter = 0;
				}
			}else{
				index = 0;
				directions = null;
				unlocked = false;
				setActiveDirection("none");
			}
		}
	}
	
	public void setActiveDirection(String s){
		activeDirection = s;
	}

	public String getActiveDirection(){
		return activeDirection;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {	
		pathTest.handleMouseMoved(e.getX(), e.getY());
		handler.getWorld().clearVisited();
		try {
			directions = handler.getPath().getDirections();
			index = 0;
			counter = 0;
			unlocked = true;
		} catch (Exception e2) {
			//Tile is solid
		}		
		
		mx = e.getX();
		my = e.getY();
		shoot(mx, my);
		
		//r.press(e.getX(), e.getY());
	}
	
	public void shoot(int mx, int my){
			double dx = mx - 600 / 2; // 600 is window width
			double dy = 400 / 2 - my; // 400 is window height
			double dirRad = Math.atan2(dy, dx);
			double dirDeg = dirRad * (180 / Math.PI);
			//System.out.println("Angle "+dirDeg);
			//Projectile p = new Projectile(handler, mx, my, 1, 1, (int) dirRad);
			//handler.getWorld().getEntityManager().addEntity(p);
			
			//handler.getWorld().getEntityManager().addEntity(r);
		
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		r.setMousePosition(e.getX(), e.getY());
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		r.release();
	}

	@Override
	public void mouseEntered(MouseEvent e) {	

	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

}
