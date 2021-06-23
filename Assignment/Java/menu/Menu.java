package dev.marianoalipi.balloonbattle.menu;

import java.awt.Graphics;

import dev.marianoalipi.balloonbattle.Game;
import dev.marianoalipi.balloonbattle.InputHandler;

/**
 * 
 * @author MarianoAlipi
 *
 */

public class Menu {

	protected Game game;
	protected InputHandler inputHandler;
	
	public Menu (Game game, InputHandler inputHandler) {
		this.game = game;
		this.inputHandler = inputHandler;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
	}
	
}
