package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.tutorial.main.Game.STATE;

public class Menu extends MouseAdapter {
	private Game game;
	private Handler handler;
	private Random r = new Random();
	private HUD hud;
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	public void render(Graphics g) {
		if (game.gameState == STATE.Menu) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			g.setColor(Color.yellow);
			g.setFont(fnt);
			g.drawString("Menu", 240, 100);
			g.setFont(fnt2);
			g.drawRect(210, 150, 200, 64);
			g.drawString("Play", 280, 190);
			g.drawRect(210, 250, 200, 64);
			g.drawString("About", 267, 290);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Quit", 280, 390);
		} else if (game.gameState == STATE.Help) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 14);
			g.setColor(Color.yellow);
			g.setFont(fnt);
			g.drawString("About", 240, 100);
			g.setFont(fnt3);
			g.drawString("Game written by Malcolm 2/22/2021", 200, 150);
			g.drawString("WASD to move", 200, 170);
			g.drawString("Move and dodge enemies to win", 200, 190);
			g.drawString("Press esc at any time to quit", 200, 210);
			g.drawString("Thanks for playing!", 200, 230);
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 280, 390);
		} else if (game.gameState == STATE.End) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 14);
			
			g.setColor(Color.yellow);
			g.setFont(fnt);
			g.drawString("GAME OVER", 160, 100);
			
			g.setFont(fnt3);
			g.drawString("You lost with a score of: " + hud.getScore(), 220, 150);
			
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Try Again", 245, 390);
		} else if (game.gameState == STATE.Win) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 14);
			
			g.setColor(Color.yellow);
			g.setFont(fnt);
			g.drawString("WINNER", 200, 100);
			
			g.setFont(fnt3);
			g.drawString("You won with a score of: " + hud.getScore(), 210, 150);
			
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Play Again", 235, 390);
		} else if (game.gameState == STATE.Select) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			g.setColor(Color.yellow);
			g.setFont(fnt);
			g.drawString("SELECT DIFFICULTY", 80, 100);
			g.setFont(fnt2);
			g.drawRect(210, 150, 200, 64);
			g.drawString("Normal", 280, 190);
			g.drawRect(210, 250, 200, 64);
			g.drawString("Hard", 267, 290);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 280, 390);
		}
	}
	
	public void tick() {
		
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if (game.gameState != STATE.Select) {
			if (mouseOver(mx, my, 210, 150, 200, 64) && game.gameState != STATE.Game) {
				// Play button
				//handler.clearEnemies();
				//game.gameState = STATE.Game;
				//handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 100), r.nextInt(Game.HEIGHT - 100), ID.BasicEnemy, handler));
				//handler.addObject(new Player(Game.WIDTH / 2, Game.HEIGHT / 2, ID.Player, handler));
				game.gameState = STATE.Select;
			} 
			
			if (mouseOver(mx, my, 210, 250, 200, 64) && game.gameState != STATE.Game) {
				// About button
				game.gameState = STATE.Help;
			} 
			
			if (mouseOver(mx, my, 210, 350, 200, 64) && game.gameState != STATE.Game) {
				// Quit button
				if (game.gameState == STATE.Menu) {
					System.exit(1);
				}
				// Back button on About screen
				else if (game.gameState == STATE.Help){
					game.gameState = STATE.Menu;
				} else if (game.gameState == STATE.End || game.gameState == STATE.Win) {
					// Try again button
					handler.clearEnemies();
					hud.setScore(0);
					hud.setLevel(1);
					game.gameState = STATE.Menu;
					for (int i = 0; i < 10; i++) {
						handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH - 100), r.nextInt(Game.HEIGHT - 100), ID.MenuParticle, handler));
					}
				}
			} 
		} else {
			if (mouseOver(mx, my, 210, 150, 200, 64) && game.gameState != STATE.Game) {
				// Normal button
				handler.clearEnemies();
				game.gameState = STATE.Game;
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 100), r.nextInt(Game.HEIGHT - 100), ID.BasicEnemy, handler));
				handler.addObject(new Player(Game.WIDTH / 2, Game.HEIGHT / 2, ID.Player, handler));
				game.diff = 0;
			} 
			
			if (mouseOver(mx, my, 210, 250, 200, 64) && game.gameState != STATE.Game) {
				// Hard button
				handler.clearEnemies();
				game.gameState = STATE.Game;
				handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 100), r.nextInt(Game.HEIGHT - 100), ID.BasicEnemy, handler));
				handler.addObject(new Player(Game.WIDTH / 2, Game.HEIGHT / 2, ID.Player, handler));
				game.diff = 1;
			} 
			
			if (mouseOver(mx, my, 210, 350, 200, 64) && game.gameState != STATE.Game) {
				// Quit button
				if (game.gameState == STATE.Menu) {
					System.exit(1);
				}
				// Back button on About screen
				else if (game.gameState == STATE.Help || game.gameState == STATE.Select){
					game.gameState = STATE.Menu;
				} else if (game.gameState == STATE.End || game.gameState == STATE.Win) {
					// Try again button
					handler.clearEnemies();
					hud.setScore(0);
					hud.setLevel(1);
					game.gameState = STATE.Menu;
					for (int i = 0; i < 10; i++) {
						handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH - 100), r.nextInt(Game.HEIGHT - 100), ID.MenuParticle, handler));
					}
				}
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
