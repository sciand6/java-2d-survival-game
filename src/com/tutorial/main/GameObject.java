package com.tutorial.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	// This GameObject is a blueprint for all game objects such as Player, Enemy, etc.
	// (Abstract classes cannot be instantiated, but they can be a base class)
	
	// Can only be accessed by children of this abstract class
	// Private variables wouldn't be able to be accessed by children of this class
	// position
	protected float x, y;
	// type of GameObject
	protected ID id;
	// speed
	protected float velX, velY;
	
	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	// These methods need to be implemented in the derived (children) classes
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	// Getters and setters
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setID(ID id) {
		this.id = id;
	}
	
	public ID getID() {
		return id;
	}
	
	public void setVelX(float velX) {
		this.velX = velX;
	}
	
	public void setVelY(float velY) {
		this.velY = velY;
	}
	
	public float getVelX() {
		return velX;
	}
	
	public float getVelY() {
		return velY;
	}
}
