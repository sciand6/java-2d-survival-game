package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossEnemy extends GameObject {
	
	private int width = 96;
	private int height = 96;
	private Handler handler;
	private int speedX = 0;
	private int speedY = 2;
	private int timer = 80;
	private int timer2 = 50;
	private Random r = new Random();

	public BossEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		setVelX(speedX);
		setVelY(speedY);
		this.handler = handler;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, width, height);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		if (timer <= 0) {
			setVelY(0);
		} else {
			timer--;
		}
		
		if (timer <= 0) {
			timer2--;
		}
		
		if (timer2 <= 0) {
			if (velX == 0) {
				setVelX(5);
			}
			if (velX < 0) {
				setVelX((float) getVelX() - 0.01f);
			} else if (velX > 0) {
				setVelX((float) getVelX() + 0.01f);
			}
			
			velX = Game.clamp(velX, -10, 10);
			
			int spawn = r.nextInt(10);
			if (spawn == 0) {
				handler.addObject(new BossEnemyBullet((int) x, (int) y, ID.BasicEnemy, handler));
			}
		}
		
		if (x <= 0 || x >= Game.WIDTH - 96) {
			velX *= -1;
		}
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.red, width, height, 0.09f, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int) x, (int) y, width, height);
	}
}
