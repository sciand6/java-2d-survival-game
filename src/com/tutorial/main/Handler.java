package com.tutorial.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	// Store all of our GameObjects
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	// Tick each game object
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	// Render each game object
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
						
			tempObject.render(g);
		}
	}
	
	// Add GameObject
	public void addObject(GameObject obj) {
		this.object.add(obj);
	}
	
	// Remove GameObject
	public void removeObject(GameObject obj) {
		this.object.remove(obj);
	}
	
	// Remove all GameObjects
	public void clearEnemies() {
		this.object.clear();
	}
}
