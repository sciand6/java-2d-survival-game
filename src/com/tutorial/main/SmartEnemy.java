package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject {
	
	private int width = 20;
	private int height = 20;
	private Handler handler;
	private GameObject player;
	private int speed = 10;

	public SmartEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		setVelX(speed);
		setVelY(speed * -1);
		this.handler = handler;
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getID() == ID.Player) {
				player = handler.object.get(i);
			}
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x,(int) y, width, height);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		// Calculate distance from player
		float diffX = x - player.getX() - 8;
		float diffY = y - player.getY() - 8;
		float distance = (float) Math.sqrt(Math.pow(x - player.getX(), 2) + Math.pow(y - player.getY(), 2));
		
		// Multiply distance by the difference between 
		velX = (float) ((-1.0 / distance) * diffX);
		velY = (float) ((-1.0 / distance) * diffY);
		
		if (y <= 0 || y >= Game.HEIGHT - 55) {
			velY *= -1;
		}
		if (x <= 0 || x >= Game.WIDTH - 35) {
			velX *= -1;
		}
		handler.addObject(new Trail(x, y, ID.Trail, Color.magenta, width, height, 0.06f, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.magenta);
		g.fillRect((int) x,(int) y, 20, 20);
	}
}
