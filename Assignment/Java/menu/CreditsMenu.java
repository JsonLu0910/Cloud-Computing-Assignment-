package dev.marianoalipi.balloonbattle.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import dev.marianoalipi.balloonbattle.Assets;
import dev.marianoalipi.balloonbattle.Game;
import dev.marianoalipi.balloonbattle.InputHandler;

public class CreditsMenu extends Menu {

	private ArrayList<String> options;
	
	public CreditsMenu(Game game, InputHandler inputHandler) {
		super(game, inputHandler);
		
		options = new ArrayList<String>();
		options.add("Go back");	
	}
	
	public void tick() {
		
		if (inputHandler.enter.clicked || inputHandler.z.clicked || inputHandler.x.clicked) {
			//inputHandler.enter.clicked = false;
			//inputHandler.z.clicked = false;
			//inputHandler.x.clicked = false;
			game.setMenu(new MainMenu(game, game.getInputHandler()));
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.black);
        g.fillRect(0, 0, game.getWidth(), game.getHeight());

        g.drawImage(Assets.githubLogo, 181, game.getHeight() - 49, 29, 29, null);
        g.setColor(Color.white);
        g.setFont(Assets.gameFont28);
        g.drawString("github.com/marianoalipi", 225, game.getHeight() - 19);
        
        g.setColor(Color.white);
        g.setFont(Assets.gameFont15B);
        g.drawString("Fonts:", 60, game.getHeight() / 10);
        g.setFont(Assets.gameFont15);
        g.drawString("\"Roses Are FF0000\" by AJ Paglia", 60, game.getHeight() / 10 + 20);
        g.drawString("www.ajpaglia.com", 60, game.getHeight() / 10 + 40);
        
        g.drawString("\"Bubble Butt\" by Iconian Fonts - Daniel Zadorozny", 60, game.getHeight() / 10 + 80);
        g.drawString("www.iconian.com", 60, game.getHeight() / 10 + 100);
        
        g.setFont(Assets.gameFont15B);
        g.drawString("Sounds:", 60, game.getHeight() / 2);
        g.setFont(Assets.gameFont15);
        g.drawString("\"Retro 8-bit game, pickup or collect object 00\"", 60, game.getHeight() / 2 + 30);
        g.drawString("\"Retro 8-bit game, hit 02\"", 60, game.getHeight() / 2 + 50);
        g.drawString("\"Retro 8-bit game, menu navigate 00\"", 60, game.getHeight() / 2 + 70);
        g.drawString("by Little Robot Sound Factory", 60, game.getHeight() / 2 + 90);
        g.drawString("Under CC Attribution 4.0 License", 60, game.getHeight() / 2 + 110);
        g.drawString("https://creativecommons.org/licenses/by/4.0/", 60, game.getHeight() / 2 + 130);
        
        int index = 2;
        g.drawImage(Assets.mainMenuOptions[index], 350, game.getHeight() - 80 - Assets.textScale * Assets.mainMenuOptions[index].getHeight(), Assets.textScale * Assets.mainMenuOptions[index].getWidth(), Assets.textScale * Assets.mainMenuOptions[index].getHeight(),  null);
        
        // Draw a balloon to indicate the cursor
        g.drawImage(Assets.balloonsOne.get("RED")[0], 300, game.getHeight() - 80 - 32, (int)(Game.SCALE * 16), (int)(Game.SCALE * 12), null);
	}	
}
