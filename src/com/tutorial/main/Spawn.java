package com.tutorial.main;

import java.util.Random;

public class Spawn {
	private Handler handler;
	private HUD hud;
	
	private int scoreKeep = 0;
	private Random r = new Random();
	private Game game;
	
	public Spawn(Handler handler, HUD hud, Game game) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
	}
	
	public void tick() {
		scoreKeep++;
		
		if (scoreKeep >= 70) {
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			handler.addObject(new FlyingEnemy(-40, Game.HEIGHT, ID.FlyingEnemy, handler));
			
			if (game.diff == 0) {
				if (hud.getLevel() % 2 == 0 && hud.getLevel() < 20 && hud.getLevel() % 5 != 0) {
					// Every even level
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 200), r.nextInt(Game.HEIGHT - 200), ID.BasicEnemy, handler));
				} else if (hud.getLevel() % 3 == 0 && hud.getLevel() < 20) {
					// Every third level
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 200), r.nextInt(Game.HEIGHT - 200), ID.FastEnemy, handler));
				} else if (hud.getLevel() % 5 == 0 && hud.getLevel() < 20) {
					// Every fifth level
					handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 200), r.nextInt(Game.HEIGHT - 200), ID.SmartEnemy, handler));
				} else if (hud.getLevel() == 20) {
					// Level 20 is boss level
					handler.clearEnemies();
					handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
					handler.addObject(new BossEnemy((Game.WIDTH / 2) - 48, -120, ID.BossEnemy, handler));
				}
			} else {
				if (hud.getLevel() % 2 == 0 && hud.getLevel() < 20 && hud.getLevel() % 5 != 0) {
					// Every even level
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 200), r.nextInt(Game.HEIGHT - 200), ID.BasicEnemy, handler));
				} else if (hud.getLevel() % 3 == 0 && hud.getLevel() < 20) {
					// Every third level
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 200), r.nextInt(Game.HEIGHT - 200), ID.FastEnemy, handler));
				} else if (hud.getLevel() % 5 == 0 && hud.getLevel() < 20) {
					// Every fifth level
					handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 200), r.nextInt(Game.HEIGHT - 200), ID.SmartEnemy, handler));
				} else if (hud.getLevel() == 20) {
					// Level 20 is boss level
					handler.clearEnemies();
					handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
					handler.addObject(new BossEnemy((Game.WIDTH / 2) - 48, -120, ID.BossEnemy, handler));
				}
			}
		}
	}
	
}
