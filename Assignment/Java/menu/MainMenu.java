package dev.marianoalipi.balloonbattle.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import dev.marianoalipi.balloonbattle.Assets;
import dev.marianoalipi.balloonbattle.Game;
import dev.marianoalipi.balloonbattle.InputHandler;
import dev.marianoalipi.balloonbattle.Sound;

public class MainMenu extends Menu {

	private ArrayList<String> options;
	private int selected = 0;
	
	public MainMenu(Game game, InputHandler inputHandler) {
		super(game, inputHandler);
		
		options = new ArrayList<String>();
		options.add("PLAY");
		options.add("CREDITS");
	}
	
	@Override
	public void tick() {
		
		if (inputHandler.enter.clicked || inputHandler.z.clicked) {
			switch (selected) {
				case 0:
					game.setMenu(null);
					game.setGameState(Game.GameState.GAME);
					game.getPlayer().setVisible(true);
					break;
				case 1:					
					game.setMenu(new CreditsMenu(game, game.getInputHandler()));
					break;
				default:
					break;
			}
		}
		
		if (inputHandler.up.clicked) {
			selected--;
			Sound.navigate.play();
		}
		if (inputHandler.down.clicked) {
			selected++;
			Sound.navigate.play();
		}
		
		if (selected < 0)
			selected = options.size() - 1;
		else if (selected  >= options.size())
			selected = 0;

	}
	
	@Override
	public void render (Graphics g) {
		g.setColor(Color.black);
        g.fillRect(0, 0, game.getWidth(), game.getHeight());

        g.drawImage(Assets.title, 100, 20, 600, 250, null);
        
        g.drawImage(Assets.githubLogo, 181, game.getHeight() - 49, 29, 29, null);
        g.setColor(Color.white);
        g.setFont(Assets.gameFont28);
        g.drawString("github.com/marianoalipi", 225, game.getHeight() - 19);
        
        // Draw the menu options
        for (int i = 0; i < options.size(); i++) {
        	g.drawImage(Assets.mainMenuOptions[i], 350, 320 + i * 10 + i * Assets.textScale * Assets.mainMenuOptions[i].getHeight(), Assets.textScale * Assets.mainMenuOptions[i].getWidth(), Assets.textScale * Assets.mainMenuOptions[i].getHeight(),  null);
        }
        
        // Draw the controls
        g.drawImage(Assets.controls, game.getWidth() / 16, game.getHeight() / 2, Assets.textScale * 32, Assets.textScale * 40, null);
        
        // Draw a balloon to indicate the cursor
        g.drawImage(Assets.balloonsOne.get("RED")[0], 300, 320 + selected * 10 + selected * Assets.textScale * Assets.mainMenuOptions[selected].getHeight(), (int)(Game.SCALE * 16), (int)(Game.SCALE * 12), null);
        // Draw balloon's outline (debugging)
        //g.drawRect(300, 320 + selected * 10 + selected * Assets.textScale * Assets.mainMenuOptions[selected].getHeight(), 32, 32);
	}
}
