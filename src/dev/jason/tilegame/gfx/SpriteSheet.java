package dev.jason.tilegame.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	private static final int size = 16;
	private BufferedImage sheet;
	
	public SpriteSheet(BufferedImage sheet){
		this.sheet = sheet;
	}
	
	public BufferedImage crop(int x, int y, int width, int height){
		x = convert(x);
		y = convert(y);
		return sheet.getSubimage(x, y, width, height);
	}
	
	public static int convert(int num){
		return num * size;
	}
}
