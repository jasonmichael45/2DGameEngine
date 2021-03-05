package dev.jason.tilegame;

import dev.jason.tilegame.display.Display;

public class Launcher {
	public static void main(String[] args) {
		Game game = new Game("Game Prototype", 600, 400);
		game.start();
	}
}
