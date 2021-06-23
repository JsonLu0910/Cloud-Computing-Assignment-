package dev.marianoalipi.balloonbattle;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author MarianoAlipi
 */
public class Assets {

	public static Font gameFont, gameFont15, gameFont15B, gameFont25, gameFont25B,
								 gameFont28, gameFont28B,
								 gameFont30, gameFont30B, gameFont35, gameFont35B;
	
	public static BufferedImage background, splash, title, github, githubLogo, icon, balloon, parachute, controls;
	
	private static SpriteSheet mainMenuOptionsSS, balloonsSS, 
								  playerFlySS,
								  playerIdleSS, playerWalkSS, playerFallingSS,
								  enemyFlySS,
								  enemyIdleSS, enemyFallingSS, enemyInflateSS;
	public static BufferedImage[] mainMenuOptions,
								  playerFly, playerFlapLeft, playerFlapRight,
								  playerIdle, playerWalkLeft, playerWalkRight, playerFalling,
								  enemyFly, enemyFlapLeft, enemyFlapRight,
								  enemyIdle, enemyFalling,
								  enemyInflateLeft, enemyInflateRight;
	// Hashmap with the different color balloons' sprites for one and two balloons.
	public static HashMap<String, BufferedImage[]> balloonsTwo, balloonsOne;
	
	public static int textScale = 4;

    public static void init() {
    	
    	// Custom fonts
    	// "Roses Are FF0000"
    	try {
    	     GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    	     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, Assets.class.getClassLoader().getResourceAsStream("dev/marianoalipi/balloonbattle/assets/fonts/RosesAreFF0000.ttf")));
    	     gameFont = new Font("Roses Are FF0000", Font.PLAIN, 25);
    	} catch (Exception e) {
    		e.printStackTrace();
    		System.out.println("Using Arial font instead.");
    		gameFont = new Font("Arial", Font.PLAIN, 25);
    	}
		
    	// Make derived fonts.
    	gameFont15	= gameFont.deriveFont(Font.PLAIN, 15);
		gameFont15B	= gameFont.deriveFont(Font.BOLD, 15);
		gameFont25	= gameFont.deriveFont(Font.PLAIN, 25);
		gameFont25B	= gameFont.deriveFont(Font.BOLD, 25);
		gameFont28	= gameFont.deriveFont(Font.PLAIN, 28);
		gameFont28B	= gameFont.deriveFont(Font.BOLD, 28);
		gameFont30	= gameFont.deriveFont(Font.PLAIN, 30);
		gameFont30B	= gameFont.deriveFont(Font.BOLD, 30);
		gameFont35	= gameFont.deriveFont(Font.PLAIN, 35);
		gameFont35B = gameFont.deriveFont(Font.BOLD, 35);
    	
        background	= ImageLoader.loadImage("assets/images/background.png");
        splash		= ImageLoader.loadImage("assets/images/splash.png");
        title		= ImageLoader.loadImage("assets/images/title_pixel.png");
        github		= ImageLoader.loadImage("assets/images/github.png");
        githubLogo	= ImageLoader.loadImage("assets/images/githubLogo.png");
        icon		= ImageLoader.loadImage("assets/images/icon.png");
        balloon		= ImageLoader.loadImage("assets/images/balloon.png");
        
        // Main menu options
        mainMenuOptionsSS = new SpriteSheet(ImageLoader.loadImage("assets/images/menu_options.png"));
        mainMenuOptions = new BufferedImage[3];
        
        for (int i = 0; i < mainMenuOptions.length; i++)
        	mainMenuOptions[i] = mainMenuOptionsSS.crop(0, i * 7, mainMenuOptionsSS.getSheet().getWidth(), 7);
        
        balloonsSS = new SpriteSheet(ImageLoader.loadImage("assets/images/balloons_colors.png"));
        
        String[] colors = {"RED", "PINK", "GREEN", "YELLOW"};
        balloonsTwo = new HashMap<String, BufferedImage[]>();
        balloonsOne = new HashMap<String, BufferedImage[]>();
        
        int verticalOffset = 0;
        for (String color : colors) {
        	// Two balloons sprites
        	balloonsTwo.put(color, new BufferedImage[6]);
        	for (int i = 0; i < 4; i++)
        		balloonsTwo.get(color)[i] = balloonsSS.crop(i * 16, verticalOffset, 16, 12);
        	// Repeat some frames to create a looping animation.
        	balloonsTwo.get(color)[4] = balloonsTwo.get(color)[1];
        	balloonsTwo.get(color)[5] = balloonsTwo.get(color)[2];
        	
        	// Single balloon sprites
        	balloonsOne.put(color, new BufferedImage[2]);
        	for (int i = 0; i < 2; i++)
        		balloonsOne.get(color)[i] = balloonsSS.crop((4 * 16) + i * 16, verticalOffset, 16, 12);
        	
        	verticalOffset += 12;
        }
        
        
        // Player flying animation
        playerFlySS = new SpriteSheet(ImageLoader.loadImage("assets/images/playerFly.png"));
        playerFly = new BufferedImage[4];
        for (int i = 0; i < playerFly.length; i++)
        	playerFly[i] = playerFlySS.crop(i * 16, 0, 16, 12);
        playerFlapLeft = Arrays.copyOfRange(playerFly, 0, 2);
        playerFlapRight = Arrays.copyOfRange(playerFly, 2, 4);
        
        // Player idle sprite
        playerIdleSS = new SpriteSheet(ImageLoader.loadImage("assets/images/playerIdle.png"));
        playerIdle = new BufferedImage[2];
        for (int i = 0; i < playerIdle.length; i++)
        	playerIdle[i] = playerIdleSS.crop(i * 16, 0, 16, 12);
        
        // Player walking animation
        playerWalkSS = new SpriteSheet(ImageLoader.loadImage("assets/images/playerWalk.png"));
        playerWalkLeft = new BufferedImage[4];
        playerWalkRight = new BufferedImage[4];
        
        for (int i = 0; i < playerWalkLeft.length; i++) {
        	playerWalkLeft[i] = playerWalkSS.crop(i * 16, 0, 16, 12);
        	playerWalkRight[i] = playerWalkSS.crop(i * 16, 12, 16, 12);
    	} 
        
        // Player falling animation
        playerFallingSS = new SpriteSheet(ImageLoader.loadImage("assets/images/playerFalling.png"));
        playerFalling = new BufferedImage[4];
        for (int i = 0; i < playerFalling.length; i++)
        	playerFalling[i] = playerFallingSS.crop(i * 16, 0, 16, 12);
        
        // Enemy flying animation
        enemyFlySS = new SpriteSheet(ImageLoader.loadImage("assets/images/enemyFly.png"));
        enemyFly = new BufferedImage[4];
        for (int i = 0; i < enemyFly.length; i++)
        	enemyFly[i] = enemyFlySS.crop(i * 16, 0, 16, 12);
        enemyFlapLeft = Arrays.copyOfRange(enemyFly, 0, 2);
        enemyFlapRight = Arrays.copyOfRange(enemyFly, 2, 4);
        
        // Enemy idle
        enemyIdleSS = new SpriteSheet(ImageLoader.loadImage("assets/images/enemyIdle.png"));
        enemyIdle = new BufferedImage[2];
        for (int i = 0; i < enemyIdle.length; i++)
        	enemyIdle[i] = enemyIdleSS.crop(i * 16, 0, 16, 12);
        
        // Enemy falling animation
        enemyFallingSS = new SpriteSheet(ImageLoader.loadImage("assets/images/enemyFalling.png"));
        enemyFalling = new BufferedImage[4];
        for (int i = 0; i < enemyFalling.length; i++)
        	enemyFalling[i] = enemyFallingSS.crop(i * 16, 0, 16, 12);
        
        // Enemy inflate animation
        enemyInflateSS = new SpriteSheet(ImageLoader.loadImage("assets/images/enemyInflate.png"));
        enemyInflateLeft = new BufferedImage[2];
        enemyInflateRight = new BufferedImage[2];
        for(int i = 0; i < enemyInflateLeft.length; i++) {
        	enemyInflateLeft[i] = enemyInflateSS.crop(i * 16, 0, 16, 12);
        	enemyInflateRight[i] = enemyInflateSS.crop((2 * 16) + i * 16, 0, 16, 12);
        }
        
        // Parachute
        parachute = ImageLoader.loadImage("assets/images/parachute.png");
        
        // Controls (32 * 40)
        controls = ImageLoader.loadImage("assets/images/controls.png");
    }
}
