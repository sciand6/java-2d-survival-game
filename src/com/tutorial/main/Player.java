package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {
	
	private int width = 40;
	private int height = 40;
	private Handler handler;

	public Player(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, width, height);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 54);
		y = Game.clamp(y, 0, Game.HEIGHT - 75);
		
		collision();
	}
	
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject curObj = handler.object.get(i);
			
			if (curObj.getID() == ID.BasicEnemy || curObj.getID() == ID.FastEnemy || curObj.getID() == ID.SmartEnemy || curObj.getID() == ID.BossEnemy || curObj.getID() == ID.FlyingEnemy) {
				if (getBounds().intersects(curObj.getBounds())) {
					HUD.HEALTH -= 2;
				}
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int) x, (int) y, width, height);
	}
	
}
