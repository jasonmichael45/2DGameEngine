package dev.jason.tilegame.entities;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import org.omg.CORBA.TCKind;

import dev.jason.tilegame.Handler;

public class Projectile extends Entity{
	private final int xOrigin = 600 / 2, yOrigin = 400 / 2;
	private double angle;
	private double nx, ny;
	private double speed;
	private double rof; 
	private double range;
	private double damage;
	private double accuracy;
	
	public Projectile(Handler handler, float x, float y, int width, int height, int dir){
		super(handler, x, y, width, height);
		range = 200;
		speed = 3;
		damage = 20;
		rof = 3;
		angle = dir;
		accuracy = 5;
		
		java.util.Random rand = new java.util.Random();
		int num = rand.nextInt(1 + 4) - 4;
		
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
		
		ny -= num;
		
		//System.out.println(nx+" "+ny);
	}

	@Override
	public void tick() {
		move();
	}
	
	public void move() {
		x += nx;
		y += ny;
	}
	
	double rofCounter = 0;
	
	double t1 = 50;
	double t2 = 50;
	
	@Override
	public void render(Graphics g) {
		if(rofCounter >= 5){
			g.setColor(Color.white);
			g.fillRect((int) x, (int) y, width, height);
			rofCounter = 0;
		}else{
			rofCounter += 1;
			//System.out.println(rofCounter);
		}
		/*
		g.fillRect((int) t1, 50, 6, 6);
		t1 += 1;
		g.fillRect((int) t2, 65, 6, 6);
		t2 += .5;*/
	}

}
