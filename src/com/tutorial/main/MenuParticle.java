package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject {
	
	private int width = 15;
	private int height = 15;
	private Handler handler;
	Random r = new Random();
	private int red = 0;
	private int green = 0;
	private int blue = 0;
	private Color col;

	public MenuParticle(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		setVelX(r.nextInt(8 - -8) + -8);
		setVelY(r.nextInt(8 - -8) + -8);
		this.handler = handler;
		red = r.nextInt(255);
		green = r.nextInt(255);
		blue = r.nextInt(255);
		col = new Color(red, green, blue);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x,(int) y, width, height);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		if (y <= 0 || y >= Game.HEIGHT - 55) {
			velY *= -1;
		}
		if (x <= 0 || x >= Game.WIDTH - 35) {
			velX *= -1;
		}
	}

	public void render(Graphics g) {
		g.setColor(col);
		g.fillRect((int) x,(int) y, 20, 20);
	}
}
