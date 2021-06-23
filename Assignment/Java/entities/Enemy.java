package dev.marianoalipi.balloonbattle.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.marianoalipi.balloonbattle.Animation;
import dev.marianoalipi.balloonbattle.Assets;
import dev.marianoalipi.balloonbattle.Game;
import dev.marianoalipi.balloonbattle.Sound;

public class Enemy extends Entity {

	public static enum EnemyColor {PINK, GREEN, YELLOW};
	private EnemyColor color;
	private Balloon balloons;
	protected static Animation flapLeftAnim, flapRightAnim, fallingAnim, inflateLeftAnim, inflateRightAnim;
	private int framesCounter;
	private boolean inflating;
	
	public Enemy() {
		super();
	}
	
	public Enemy(int x, int y, int width, int height, Game game, EnemyColor color) {
		super(x, y, width, height, game);
		this.color = color;
		this.hitbox = new Rectangle(x, y, (int)(getWidth() * 0.9), getHeight());
		this.framesCounter = 0;
		this.setInflating(false);
		
		this.balloons = new Balloon(getX(), (int)(getY() - Game.SCALE * 12), (int)(Game.SCALE * 16), (int)(Game.SCALE * 12), 1, Balloon.BalloonColor.PINK, game, this);
		flapLeftAnim = new Animation(Assets.enemyFlapLeft, 80);
		flapRightAnim = new Animation(Assets.enemyFlapRight, 80);
		fallingAnim = new Animation(Assets.enemyFalling, 50);
		inflateLeftAnim = new Animation(Assets.enemyInflateLeft, 160);
		inflateRightAnim = new Animation(Assets.enemyInflateRight, 160);
	}
	
	@Override
	public void tick() {
		
		if (isOnPlatform())
			setGrounded(true);
		
		if (balloons.getBalloonsAmount() > 0) {
			
			Balloon pBalloons = game.getPlayer().getBalloons();
			// Check for collision with player's balloons
			if (getHitbox().intersects(pBalloons.getHitbox())) {
				// Remove one balloon
				if (!pBalloons.isInvincible()) {
					pBalloons.setBalloonsAmount(pBalloons.getBalloonsAmount() - 1);
					
					// Play a sound
					Sound.hit.play();
				}
				
				// Make the enemy bounce a little
				double hitboxCenterX = getHitbox().getX() + getHitbox().getWidth() / 2,
						pBalloonsCenter = pBalloons.getHitbox().getX() + pBalloons.getHitbox().getWidth() / 2;
				if (pBalloonsCenter <= hitboxCenterX) {
					setxSpeed(Math.abs(getxSpeed()) * -1);
					if (!pBalloons.isInvincible())
						game.getPlayer().setxSpeed(Math.abs(game.getPlayer().getxSpeed()));
				}
				else {
					setxSpeed(Math.abs(getxSpeed()));
					if (!pBalloons.isInvincible())
						game.getPlayer().setxSpeed(-1 * Math.abs(game.getPlayer().getxSpeed()));
				}
				
				setySpeed(0.5 * Math.abs(getySpeed()));

				if (pBalloons.getBalloonsAmount() > 0) {
					game.getPlayer().setySpeed(-5);
				} else {
					game.getPlayer().setySpeed(10);
					game.getPlayer().setDying(true);
				}
				
				pBalloons.setInvincible(true);
			}
			
			// Make the enemy flap towards the middle of the screen.
			if (getY() > game.getHeight() * 0.4) {
				setySpeed(getySpeed() + 1);
				setAnimation( getDirection() == Direction.LEFT ? flapLeftAnim : flapRightAnim );
			} else if (getY() < game.getHeight() * 0.33) {
				setSprite(getDirection() == Direction.LEFT ? Assets.enemyFlapLeft[0] : Assets.enemyFlapRight[0]);
			} else {
				setSprite(getDirection() == Direction.LEFT ? Assets.enemyFlapLeft[0] : Assets.enemyFlapRight[0]);
			}
		} else {
			// No balloons
			
			if (!isGrounded()) {
				// Enemy is falling.
				if (balloons.isParachute()) {
					// Falling with parachute
					setAnimation(fallingAnim);
					setySpeed(-2);
					setxSpeed(Math.floor(Math.random() * 2 - 1) * Math.random() + 0.3);
				}
			} else {
				// If on the ground
				balloons.setParachute(false);
				// If two seconds have passed on the ground, start inflating a balloon.
				if (++framesCounter < 120 && !isInflating()) {
					setAnimation(null);
					setSprite(Assets.enemyIdle[getDirection() == Direction.LEFT ? 0 : 1]);
				} else if (framesCounter > 120 && !isInflating()) {
					framesCounter = 0;
					setInflating(true);
					setAnimation(getDirection() == Direction.LEFT ? inflateLeftAnim : inflateRightAnim);
				} else if (framesCounter > 240 && isInflating()) {
					// Inflate a balloon and then start flying again.
					balloons.setBalloonsAmount(1);
					setySpeed(0.5);
					setY(getY() - getHeight() / 8);
					framesCounter = 0;
					setInflating(false);
				}
				
				// If the player hits the enemy on the ground
				if (getHitbox().intersects(game.getPlayer().getHitbox())) {
					// Play sound
					Sound.hit.play();
					
					setySpeed(5);
					setY(getY() - getHeight() / 2);
					setAnimation(fallingAnim);
					setDying(true);
				}
			}
		}
		
		// Gravity pull and friction.
		if (!isGrounded()) {
			setySpeed(getySpeed() - GRAVITY);
			// Air friction
			if (getxSpeed() > 0.3 || getxSpeed() < -0.3)
				setxSpeed((getxSpeed() > 0 ? 1 : -1) * (Math.abs(getxSpeed()) - 0.0025));
			else
				setxSpeed(0);
		} else {
			// Ground friction
			if (getxSpeed() > 0.3 || getxSpeed() < -0.3)
				setxSpeed((getxSpeed() > 0 ? 1 : -1) * (Math.abs(getxSpeed()) - 0.1));
			else {
				setxSpeed(0);
			}
		}
		
		// Go to the other side if the limit is crossed
		if (getX() <= -1 * getWidth() / 2) {
			setX(game.getWidth() - getWidth() / 2 - 1);
		} else if (getX() >= game.getWidth() - getWidth() / 2) {
			setX(-1 * getWidth() / 2 + 1);
		}
		
		// Prevent the enemy  from going above or below the screen
		if (getY() < -1 * getHeight() / 3) {
			// Ceiling
			setY(-1 * getHeight() / 3);
			setySpeed(getySpeed() * -0.3);
		} else if (getY() > game.getHeight() - getHeight() - 20) {
			// Ground
			setY(game.getHeight() - getHeight());
			setySpeed(0);
			if (isDying()) {
				setDead(true);
				setDying(false);
			}
		} else {
			// Mid-air
			if (isOnPlatform())
				setGrounded(true);
			else
				setGrounded(false);
			
			// If the enemy is just above the ground limit, mark it as grounded and set ySpeed = 0.
			if (getY() >= game.getHeight() - getHeight()) {
				setGrounded(true);
				setySpeed(0);
			}			
		}
		
		// Move the enemies
		setX((int)Math.floor(getX() + getxSpeed()));
		setY((int)Math.floor(getY() - getySpeed()));
	
		// Relocate hitbox
		getHitbox().setLocation(getX(), getY());
		
		// Tick balloons
		balloons.tick();
		
		// Tick the animation and get the current sprite.
		if (getAnimation() != null) {
			getAnimation().tick();
			setSprite(getAnimation().getCurrentFrame());
		}
		
	}

	@Override
	public void render(Graphics g) {
		if (isVisible()) {
			g.drawImage(getSprite(), getX(), getY(), getWidth(), getHeight(), null);
		
			// Render balloons
			balloons.render(g);
		}
		
		// Draw hitbox (for debugging)
		//g.setColor(Color.red);
		//g.drawRect((int)hitbox.getX(), (int)hitbox.getY(), (int)hitbox.getWidth(), (int)hitbox.getHeight());

	}

	/**
	 * @return the color
	 */
	public EnemyColor getColor() {
		return color;
	}

	/**
	 * @return the balloons
	 */
	public Balloon getBalloons() {
		return balloons;
	}
	
	/**
	 * @return the inflating
	 */
	public boolean isInflating() {
		return inflating;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(EnemyColor color) {
		this.color = color;
	}

	/**
	 * @param inflating the inflating to set
	 */
	public void setInflating(boolean inflating) {
		this.inflating = inflating;
	}

}
