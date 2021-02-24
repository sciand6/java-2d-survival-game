package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.concurrent.ThreadLocalRandom;

public class FlyingEnemy extends GameObject {
	
	private int width = 20;
	private int height = 20;
	private Handler handler;
	private int speedX = 5;
	private int speedY = -5;
	private int timer = 0;
	private double min = 0.3;
	private double max = 0.6;
	private double yOffset = 0.1;

	public FlyingEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		setVelX(speedX);
		setVelY(speedY);
		yOffset = ThreadLocalRandom.current().nextDouble(min, max);
		this.handler = handler;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, width, height);
	}
	
	public void tick() {
		x += velX;
		timer++;
		if (timer >= 5) {
			timer = 0;
			velY += yOffset;
		}
		y += velY * 2;
		if (y >= Game.HEIGHT || x >= Game.WIDTH) {
			handler.removeObject(this);
		}
		handler.addObject(new Trail(x, y, ID.Trail, Color.red, width, height, 0.06f, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int) x, (int) y, 20, 20);
	}
}
