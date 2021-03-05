package dev.jason.tilegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

import dev.jason.tilegame.display.Display;
import dev.jason.tilegame.gfx.Assets;
import dev.jason.tilegame.gfx.GameCamera;
import dev.jason.tilegame.input.KeyManager;
import dev.jason.tilegame.input.MouseManager;
import dev.jason.tilegame.states.GameState;
import dev.jason.tilegame.states.MenuState;
import dev.jason.tilegame.states.SettingState;
import dev.jason.tilegame.states.State;

public class Game implements Runnable{
	
	private Display display;

	private int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//States
	private State gameState;
	private State menuState;
	private State settingState;
	
	//Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	private boolean useKeys = true;
	private boolean useMouse = true;
	
	//Camera
	private GameCamera gameCamera;
	
	//Handler
	private Handler handler;
	
	public Game(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
	}
	
	private void init(){
		display = new Display(title, width, height);
		handler = new Handler(this);
		
		if(useKeys){
			keyManager = new KeyManager(handler);
			display.getFrame().addKeyListener(keyManager);	
		}
		
		Assets.init();
		
		gameCamera = new GameCamera(handler, 0, 0);
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		State.setState(gameState);
		
		if(useMouse){
			mouseManager = new MouseManager(handler);
			display.getCanvas().addMouseListener(mouseManager);
			display.getCanvas().addMouseMotionListener(mouseManager);
		}
	}
	
	private boolean actionTick = true;
	private void tick(){
		/*System.out.print("Tick ");
		if(actionTick){
			keyManager.tick();
			mouseManager.tick();
			System.out.println("and subtick");
			actionTick = false;
		}else{
			actionTick = true;
			System.out.println("");
		}*/
		
		if(useKeys){
			keyManager.tick();
		}
		if(useMouse){
			mouseManager.tick();
		}
	
		if(State.getState() != null){
			State.getState().tick();
		}
	}
	
	private int fpsTracker = 0;
	
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		
		//Clear screen
		g.clearRect(0, 0, width, height);
		
		// Draw here	
		if(State.getState() != null){
			State.getState().render(g);
			//g.setColor(Color.black);
			g.drawString(fpsTracker+"", 8, 16);
		}
		
		// End drawing
		bs.show();
		g.dispose();
	}
	
	public void run(){
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				tick();
				render();
				ticks++;
				delta--;
			}
			if(timer >= 1000000000){
				fpsTracker = ticks;
				ticks = 0;
				timer = 0;
			}
		}	
		stop();
	}
	
	public KeyManager getKeyManager(){
		return keyManager;
	}
	
	public MouseManager getMouseManager(){
		return mouseManager;
	}
	
	public boolean keysActive() {
		return useKeys;
	}

	public void setKeyActivity(boolean useKeys) {
		this.useKeys = useKeys;
	}

	public boolean mouseActive() {
		return useMouse;
	}

	public void setMouseActivity(boolean useMouse) {
		this.useMouse = useMouse;
	}
	
	public GameCamera getGameCamera(){
		return gameCamera;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public Graphics getGraphics(){
		return g;
	}
	
	public Display getDisplay() {
		return display;
	}
	
	public synchronized void start(){
		//if(running) return;
		running = true;
		thread = new Thread(this);
		thread.start(); // Calls run()
	}
	
	public synchronized void stop(){
		if(!running) return;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
