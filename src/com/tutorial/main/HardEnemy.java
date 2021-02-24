package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class HardEnemy extends GameObject {
	
	private int width = 20;
	private int height = 20;
	private Handler handler;
	private int speedX = 5;
	private int speedY = -5;
	private Random r = new Random();

	public HardEnemy(int x, int y, ID id, Handler handler) {
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
		if (y <= 0 || y >= Game.HEIGHT - 55) {
			if (velY < 0) {
				velY = -(r.nextInt(7) + 1) * -1;
			} else {
				velY = (r.nextInt(7) + 1) * -1;
			}
		}
		if (x <= 0 || x >= Game.WIDTH - 35) {
			if (velX < 0) {
				velX = -(r.nextInt(7) + 1) * -1;
			} else {
				velX = (r.nextInt(7) + 1) * -1;
			}
		}
		handler.addObject(new Trail(x, y, ID.Trail, Color.yellow, width, height, 0.06f, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int) x, (int) y, 20, 20);
	}
}
