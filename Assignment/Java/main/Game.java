package dev.marianoalipi.balloonbattle;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import dev.marianoalipi.balloonbattle.entities.Enemy;
import dev.marianoalipi.balloonbattle.entities.Player;
import dev.marianoalipi.balloonbattle.levels.Level;
import dev.marianoalipi.balloonbattle.levels.Platform;
import dev.marianoalipi.balloonbattle.menu.MainMenu;
import dev.marianoalipi.balloonbattle.menu.Menu;

/**
 * 
 * @author MarianoAlipi
 *
 */
public class Game implements Runnable {

	private BufferStrategy bs;
    private Graphics g;
    private Graphics2D g2d;
    private Display display;
    String title;
    private int width;
    private int height;
    public final static double SCALE = 3;	// the resizing scale to multiply the values
    private int splashFrames = 70; 			// the duration of the splash screen fade effect
    private Thread thread;
    private boolean running;        		// sets up the game
    private boolean splashScreenDisplayed;  // whether the splash screen has been displayed
    private boolean showSplash = false;		// whether or not to show the splash screen
    private boolean paused;         		// to pause the game
    private Menu menu;						// to set the current menu or no menu
    private ArrayList<Level> levels;		// ArrayList with all the levels
    private Level currentLevel;				// the current level
    public enum GameState {MENU, GAME, WON, LOST};
    private GameState gameState;
	private InputHandler inputHandler;
	
	// Game entities
	private Player player;
	private ArrayList<Enemy> enemies, toRemove;
    
    // Specialized single-use variables
	private int splashCounter = 0; // for splash effects
	private byte splashStage = -1; // 0 fade-in, 1 stay, 2 fade-out
    private float alpha = 0f; // the transparency of the rendered images

    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.running = false;
        this.splashScreenDisplayed = false;
        this.paused = false;
        this.gameState = GameState.MENU;
        this.inputHandler = new InputHandler();
        if (!showSplash) {
        	splashScreenDisplayed = true;
        	setMenu(new MainMenu(this, getInputHandler()));
        	setGameState(GameState.MENU);
        }
    }
    
    private void init() {
    	Assets.init();
    	
    	display = new Display(title, getWidth(), getHeight());
        display.getJframe().pack();
        
        // Play a silent sound to prevent a delayed start of the following sounds.
        Sound.silence.play();
        
        // Create player.
        player = new Player(getWidth() / 2 - 20, getHeight() / 2 - 20, (int)(SCALE * 16), (int)(SCALE * 12), this, getInputHandler());
        player.setVisible(false);
        player.setY(getHeight() - player.getHeight() - 50);
        
        // Create enemies.
        enemies = new ArrayList<Enemy>();
        enemies.add(new Enemy((int)(getWidth() * 0.3), getHeight() / 2, (int)(SCALE * 16), (int)(SCALE *12), this, Enemy.EnemyColor.PINK));
        enemies.add(new Enemy((int)(getWidth() * 0.6), getHeight() / 2, (int)(SCALE * 16), (int)(SCALE *12), this, Enemy.EnemyColor.PINK));
        toRemove = new ArrayList<Enemy>();
        
        // Create levels.
        levels = new ArrayList<Level>();
        levels.add(new Level((byte)0, this));
        levels.get(0).getPlatforms().add(new Platform(-100, getHeight() - 20, getWidth() + 200, 20, (byte)0, this));
        levels.get(0).getPlatforms().add(new Platform(getWidth() / 8, getHeight() / 2, getWidth() / 4, 20, (byte)0, this));
        currentLevel = levels.get(0);
        
        //starts to listen the keyboard input
        display.getJframe().addKeyListener(inputHandler);
    }

    @Override
    public void run() {
        init();
        //frames per second
        int fps = 60;
        double timeTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        
        while (running) {
            now = System.nanoTime();
            //accumulates times in delta
            delta += (now - lastTime) / timeTick;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                delta--;
            }
        }
        stop();
    }
    
    private void tick() {

        // Get keyboard input
    	inputHandler.tick();
        
    	switch (getGameState()) {
    		case GAME:
    			
    			if (inputHandler.enter.clicked) {
    				setPaused(!isPaused());
    			}
    			
    			if (!isPaused()) {
    				if (!player.isDead())
    					player.tick();
    				else
    					player = new Player(getWidth() / 2 - 20, getHeight() / 2 - 20, (int)(SCALE * 16), (int)(SCALE * 12), this, getInputHandler());
	    			
    				for (Enemy enemy : enemies) {
    					if (!enemy.isDead())
    						enemy.tick();
    					else
    						toRemove.add(enemy);
    					/*
	    				if (enemy.isDying() && enemy.isGrounded()) {
	    					toRemove.add(enemy);
	    				}
	    				*/
	    			}
	    			if (toRemove.size() != 0) {
	    				enemies.removeAll(toRemove);
	    				toRemove.clear();
	    			}
	    			
	    			currentLevel.tick();
    			}
    			break;
    		default:
    			break;
    	}
    
    	
        if (getMenu() != null) {
        	getMenu().tick();
        }

    }

    private void render() {
        //get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /*if its null, we define one with 3 buffers to display images of the game but 
        after clearing the Rectangle, getting the grapic object from the buffer 
        strategy element. show the graphic and dispose it to the trash system.
         */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        } else {
        	
        	g = bs.getDrawGraphics();
        	
        	if (!splashScreenDisplayed) {
                
            	g2d = (Graphics2D) g;
            	g2d.setColor(Color.black);
            	
            	g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        		g2d.fillRect(0, 0, width, height);
            	
        		if (splashStage == -1) { // Initial delay
        			if (splashCounter <= 40) {
        				splashCounter++;
        			} else {
        				splashCounter = 0;
        				splashStage++;
        			}
        		} else if (splashStage == 0) { // Fade-in
        			if (splashCounter < splashFrames) {
        				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        				g2d.drawImage(Assets.splash, 179, height / 3, 443, 143, null);
        				alpha = (alpha + 0.02f >= 1f) ? 1f : alpha + 0.02f;
        				splashCounter++;
        			} else {
        				alpha = 1f;
        				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        				g2d.drawImage(Assets.splash, 179, height / 3, 443, 143, null);
        				splashCounter = 0;
        				splashStage++;
        			}
        		} else if (splashStage == 1) { // Stay
        			if (splashCounter <= 120) {
        				g2d.drawImage(Assets.splash, 179, height / 3, 443, 143, null);
        				splashCounter++;
        			} else {
        				g2d.drawImage(Assets.splash, 179, height / 3, 443, 143, null);
        				splashCounter = 0;
        				splashStage++;
        			}
        		} else if (splashStage == 2) { // Fade-out
        			if (splashCounter < splashFrames) {
        				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        				g2d.drawImage(Assets.splash, 179, height / 3, 443, 143, null);
        				alpha = (alpha - 0.02f <= 0f) ? 0f : alpha - 0.02f;
        				splashCounter++;
        			} else {
        				alpha = 1f;
        				splashCounter = 0;
        				splashStage++;
        				
        				g.setColor(Color.black);
        				g.fillRect(0, 0, width, height);
        				
        				splashScreenDisplayed = true;
        				setMenu(new MainMenu(this, getInputHandler()));
        			}
        		}
            	
        	} else {
    			// Splash screen already displayed
        		switch (getGameState()) {
        			case GAME:
        				
        				g.setColor(Color.black);
        				g.fillRect(0, 0, getWidth(), getHeight());
        				
        				currentLevel.render(g);
        				
        				for (Enemy enemy : enemies) {
        					enemy.render(g);
        				}

        				player.render(g);
        				
        				break;
    				
        			default:
    					break;
        		}
        	}
        	
        	if (getMenu() != null) {
        		getMenu().render(g);
        	}
        	
	        // Fixes stutter on Linux.
	        Toolkit.getDefaultToolkit().sync();
	        bs.show();
	        g.dispose();
        }
    }

    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                //waits till thread dies
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    /**
	 * @return the paused
	 */
	public boolean isPaused() {
		return paused;
	}
	
	/**
	 * @return the running
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * @return the menu
	 */
	public Menu getMenu() {
		return menu;
	}

	public InputHandler getInputHandler() {
		return inputHandler;
	}

	/**
	 * 
	 * @return gameState
	 */
	public GameState getGameState() {
		return gameState;
	}
	
	/**
	 * 
	 * @return player
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * 
	 * @return enemies
	 */
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
	
	public ArrayList<Level> getLevels() {
		return levels;
	}
	
	public void setInputHandler(InputHandler inputHandler) {
		this.inputHandler = inputHandler;
	}

	/**
	 * @param menu the menu to set
	 */
	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	/**
	 * @param paused the paused to set
	 */
	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	/**
	 * @param running the running to set
	 */
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	/**
	 * 
	 * @param gameState the gameState to set
	 */
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
	
	/**
	 * 
	 * @param player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
}
