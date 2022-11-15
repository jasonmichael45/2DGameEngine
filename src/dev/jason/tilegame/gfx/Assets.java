package dev.jason.tilegame.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	private static final int width = 16, height = 16;
	
	public static BufferedImage player, dirt, grass, stone, tree, error;
	public static BufferedImage[] player_down, player_up, player_left, player_right,
								player_downL, player_downR, player_upL, player_upR;
	
	public static void init(){
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/Sprite_Sheet_Clean-2.png"));

		player_down = new BufferedImage[2];
		player_up = new BufferedImage[2];
		player_left = new BufferedImage[2];
		player_right = new BufferedImage[2];
		player_downL = new BufferedImage[2];
		player_downR = new BufferedImage[2];
		player_upL = new BufferedImage[2];
		player_upR = new BufferedImage[2];
		
		player_down[0] = sheet.crop(5, 0, width, height);
		player_down[1] = sheet.crop(6, 0, width, height);
		player_up[0] = sheet.crop(7, 0, width, height);
		player_up[1] = sheet.crop(8, 0, width, height);
		player_upL[0] = sheet.crop(9, 0, width, height);
		player_upL[1] = sheet.crop(10, 0, width, height);
		player_upR[0] = sheet.crop(11, 0, width, height);
		player_upR[1] = sheet.crop(12, 0, width, height);
		player_downL[0] = sheet.crop(13, 0, width, height);
		player_downL[1] = sheet.crop(14, 0, width, height);
		player_downR[0] = sheet.crop(15, 0, width, height);
		player_downR[1] = sheet.crop(16, 0, width, height);
		player_left[0] = sheet.crop(17, 0, width, height);
		player_left[1] = sheet.crop(18, 0, width, height);
		player_right[0] = sheet.crop(19, 0, width, height);
		player_right[1] = sheet.crop(20, 0, width, height);
		
		grass = sheet.crop(0, 0, width, height);
		dirt = sheet.crop(2, 0, width, height);
		tree = sheet.crop(3, 0, width, height);
		stone = sheet.crop(4, 0, width, height);
		error = sheet.crop(0, 1, width, height);
	}
}
