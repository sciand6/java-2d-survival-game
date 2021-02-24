package com.tutorial.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	
	public KeyInput(Handler handler) {
		this.handler = handler;
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
	}
	
	// Implemented abstract methods
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject curObj = handler.object.get(i);
			
			if (curObj.getID() == ID.Player) {
				// Key events for player 1
				if (key == KeyEvent.VK_W) {
					curObj.setVelY(-5);
					keyDown[0] = true;
				}
				if (key == KeyEvent.VK_S) {
					curObj.setVelY(5);
					keyDown[1] = true;
				}
				if (key == KeyEvent.VK_D) {
					curObj.setVelX(5);
					keyDown[2] = true;
				}
				if (key == KeyEvent.VK_A) {
					curObj.setVelX(-5);
					keyDown[3] = true;
				}
			}
		}
		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject curObj = handler.object.get(i);
			
			if (curObj.getID() == ID.Player) {
				// Key events for player 1
				if (key == KeyEvent.VK_W) {
					keyDown[0] = false;
				}
				if (key == KeyEvent.VK_S) {
					keyDown[1] = false;
				}
				if (key == KeyEvent.VK_D) {
					keyDown[2] = false;
				}
				if (key == KeyEvent.VK_A) {
					keyDown[3] = false;
				}
				
				// Vertical movement
				if (!keyDown[0] && !keyDown[1]) {
					curObj.setVelY(0);
				}
				
				// Horizontal movement
				if (!keyDown[2] && !keyDown[3]) {
					curObj.setVelX(0);
				}
			} 
		}
	}
}
