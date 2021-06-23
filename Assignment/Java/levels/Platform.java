package dev.marianoalipi.balloonbattle.levels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import dev.marianoalipi.balloonbattle.Game;
import dev.marianoalipi.balloonbattle.entities.Enemy;
import dev.marianoalipi.balloonbattle.entities.Player;

public class Platform {

	private int x, y, width, height;
	private byte level;
	private Rectangle hitbox;
	private BufferedImage sprite;
	private boolean touching; 
	private Game game;
	private Player player;
	private ArrayList<Enemy> enemies;

	public Platform() {
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
		this.level = -1;
		this.hitbox = new Rectangle(x, y, width, height);
		this.sprite = null;
		this.touching = false;
		this.game = null;
		this.player = null;
		this.enemies = null;
	}

	public Platform(int x, int y, int width, int height, byte level, Game game) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.level = level;
		this.hitbox = new Rectangle(x, y, width, height);
		this.sprite = null;
		this.touching = false;
		this.game = game;
		this.player = game.getPlayer();
		this.enemies = game.getEnemies();
	}

	public void tick() {

		this.player = game.getPlayer();
		this.enemies = game.getEnemies();

		// Check player collision.
		if (getHitbox().intersects(player.getHitbox()) || getHitbox().intersects(player.getBalloons().getHitbox())) {
			// Touching from above
			if (player.getY() + player.getHeight() < getY() + getHeight() / 2) {
				player.setY(getY() - player.getHeight() + 1);
				player.setGrounded(true);
				//player.setOnPlatform(true);
				game.getLevels().get(level).setPlayerOnPlatform(true);
				
			}
			if (!touching) {
				touching = true;
				player.setySpeed(0);
			}
			
			// Other cases
			if (!player.isGrounded()) {
				// Touching the player's balloons (probably from below).
				if (getHitbox().intersects(player.getBalloons().getHitbox())) {
					if (player.getBalloons().getY() > getY() + getHeight() / 2) {
						player.setY(getY() + getHeight() + player.getBalloons().getHeight() + 1);
						player.setySpeed(Math.abs(player.getySpeed()) * -0.5);
					}
				}
				// Touching from right side
				if (player.getX() > getX() + getWidth() - 20) {
					if (player.getxSpeed() < 0)
						player.setxSpeed(player.getxSpeed() * -1);
					player.setX(player.getX() + 5);
				// Touching from left side
				} else if (player.getX() < getX() + 20) {
					if (player.getxSpeed() > 0)
						player.setxSpeed(player.getxSpeed() * -1);
					player.setX(player.getX() - 5);
				}
				
			}
		} else {
			touching = false;
			//player.setOnPlatform(false);
			
		}
		
		// Check collision for each enemy.
		for (Enemy enemy : enemies) {
			if (getHitbox().intersects(enemy.getHitbox()) && !enemy.isDying()) {
				
				// Touching from above
				if (enemy.getY() + enemy.getHeight() < getY() + getHeight() / 2) {
					enemy.setY(getY() - enemy.getHeight() + 1);
					enemy.setGrounded(true);
					
				}
				if (!touching) {
					enemy.setySpeed(0);
				}
				
				// Other cases
				if (!enemy.isGrounded()) {
					// Touching the enemy's balloons (probably from below).
					if (getHitbox().intersects(enemy.getBalloons().getHitbox())) {
						if (enemy.getBalloons().getY() > getY() + getHeight() / 2) {
							enemy.setY(getY() + getHeight() + enemy.getBalloons().getHeight() + 1);
							enemy.setySpeed(Math.abs(enemy.getySpeed()) * -0.5);
						}
					}
					// Touching from right side
					if (enemy.getX() > getX() + getWidth() - 20) {
						if (enemy.getxSpeed() < 0)
							enemy.setxSpeed(enemy.getxSpeed() * -1);
						enemy.setX(enemy.getX() + 5);
					// Touching from left side
					} else if (enemy.getX() < getX() + 20) {
						if (enemy.getxSpeed() > 0)
							enemy.setxSpeed(enemy.getxSpeed() * -1);
						enemy.setX(enemy.getX() - 5);
					}
					
				}
				
			}
		}
	}

	public void render(Graphics g) {
		//g.drawImage(getSprite(), getX(), getY(), getWidth(), getHeight(), null);

		// Placeholder renderer
		g.setColor(Color.green);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Rectangle getHitbox() {
		return hitbox;
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}
}