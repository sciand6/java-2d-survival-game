package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

/**
 * This is a tutorial game I'm making to learn Java 2D game programming.
 */

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1550691097823471818L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private boolean running = false;
	public static boolean paused = false;
	public int diff = 0;
	// 0 = normal
	// 1 = hard
	
	private Handler handler;
	private Spawn spawner;
	private HUD hud;
	private Random r;
	private Menu menu;
	
	public enum STATE {
		Menu,
		Select,
		Help,
		Game,
		End,
		Win
	}
	
	public static STATE gameState = STATE.Menu;
	
	public Game() {
		new Window(WIDTH, HEIGHT, "Avoid The Enemy Squares!", this);
		hud = new HUD();
		r = new Random();
		handler = new Handler();
		spawner = new Spawn(handler, hud, this);
		this.addKeyListener(new KeyInput(handler, this));
		menu = new Menu(this, handler, hud);
		this.addMouseListener(menu);
		// Add game objects
		if (gameState == STATE.Game) {
			handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 100), r.nextInt(Game.HEIGHT - 100), ID.BasicEnemy, handler));
		} else {
			for (int i = 0; i < 10; i++) {
				handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH - 100), r.nextInt(Game.HEIGHT - 100), ID.MenuParticle, handler));
			}
		}
	} 

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running) {
				render();
			}
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		if (gameState == STATE.Game) {
			if (!paused) {
				hud.tick();
				spawner.tick();
				handler.tick();
				
				// Losing condition
				if (hud.HEALTH <= 0) {
					hud.HEALTH = 100;
					gameState = STATE.End;
					handler.clearEnemies();
				}
				
				// Winning condition
				if (hud.getLevel() == 30 && hud.HEALTH > 0) {
					hud.HEALTH = 100;
					gameState = STATE.Win;
					handler.clearEnemies();
				}
			}
		} else if (gameState == STATE.Select || gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Win) {
			menu.tick();
			handler.tick();
		} 
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		handler.render(g);
		if (paused) {
			g.setColor(Color.red);
			g.drawString("PAUSED", 100, 100);
		}
		if (gameState == STATE.Game) {
			hud.render(g);
		} else if (gameState == STATE.Select || gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Win) {
			menu.render(g);
		}
		g.dispose();
		bs.show();
	}
	
	public static float clamp(float var, float min, float max) {
		if (var >= max) {
			return var = max;
		} else if (var <= min) {
			return var = min;
		} else {
			return var;
		}
	}
	
	public static void main(String args[]) {
		System.out.println("Game 1 V1 running.");
		new Game();
	}
}
