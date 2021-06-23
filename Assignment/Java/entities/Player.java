package dev.marianoalipi.balloonbattle.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.marianoalipi.balloonbattle.Animation;
import dev.marianoalipi.balloonbattle.Assets;
import dev.marianoalipi.balloonbattle.Game;
import dev.marianoalipi.balloonbattle.InputHandler;
import dev.marianoalipi.balloonbattle.Sound;

public class Player extends Entity {

	private InputHandler inputHandler;
	private boolean flapKeyReleased;
	private int framesBetweenFlaps = 8, framesCounter = 8, initialDelayCounter = 0;
	private Balloon balloons;
	private boolean initialDelayDone;
	protected static Animation walkLeftAnim, walkRightAnim, fallingAnim, flapLeftAnim, flapRightAnim;

	public Player() {
		super();
	}

	public Player(int x, int y, int width, int height, Game game, InputHandler inputHandler) {
		super(x, y, width, height, game);
		this.inputHandler = inputHandler;
		this.flapKeyReleased = true;
		this.framesCounter = 0;
		this.sprite = Assets.playerFly[0];
		this.direction = Direction.LEFT;
		this.hitbox = new Rectangle(x, y, (int)(getWidth() * 0.65), getHeight());
		this.initialDelayDone = false;
		this.dead = false;

		this.balloons = new Balloon(getX(), (int)(getY() - Game.SCALE * 12), (int)(Game.SCALE * 16), (int)(Game.SCALE * 12), 2, Balloon.BalloonColor.RED, game, this);
		walkLeftAnim = new Animation(Assets.playerWalkLeft, 100);
		walkRightAnim = new Animation(Assets.playerWalkRight, 100);
		fallingAnim = new Animation(Assets.playerFalling, 50);
		flapLeftAnim = new Animation(Assets.playerFlapLeft, 80);
		flapRightAnim = new Animation(Assets.playerFlapRight, 80);
	}

	@Override
	public void tick() {
		
		// Ignore input for some frames at the start of the game.
		if (!initialDelayDone) {
			inputHandler.z.down = false;
			inputHandler.x.down = false;
			inputHandler.left.down = false;
			inputHandler.right.down = false;
			if (++initialDelayCounter > 5) {
				initialDelayDone = true;
			}
		}
		
		// If the player has balloons...
		if (balloons.getBalloonsAmount() > 0) {
			// Check for a single flap (Z key = A button)
			if (inputHandler.z.down && isFlapKeyReleased()) {
				setySpeed(getySpeed() + 5);
				setFlapKeyReleased(false);
				setAnimation(null);
				setSprite(getDirection() == Direction.LEFT ? Assets.playerFlapLeft[1] : Assets.playerFlapRight[1]);
	
				Sound.flap.play();
				
				if (inputHandler.left.down)
					setxSpeed(getxSpeed() - 2);
				if (inputHandler.right.down)
					setxSpeed(getxSpeed() + 2);
				
				inputHandler.x.down = false;
			}
	
			// To check if Z key has been released.
			if (!inputHandler.z.down) {
				setFlapKeyReleased(true);
				setAnimation(null);
				setSprite(getDirection() == Direction.LEFT ? Assets.playerFlapLeft[0] : Assets.playerFlapRight[0]);
			}
	
			// Check for constant flapping (X key = B button)
			framesCounter++;
			if (inputHandler.x.down) {
				setAnimation(getDirection() == Direction.LEFT ? flapLeftAnim : flapRightAnim);
				if (framesCounter > framesBetweenFlaps) {
					setySpeed(getySpeed() + 5);
					framesCounter = 0;
					
					Sound.flap.play();
	
					if (inputHandler.left.down)
						setxSpeed(getxSpeed() - 3);
					if (inputHandler.right.down)
						setxSpeed(getxSpeed() + 3);
					
					inputHandler.z.down = false;
				}
			}
		// If no balloons...
		} else {
			if (!isGrounded()) {
				// No balloons: player is falling.
				setAnimation(fallingAnim);
				setxSpeed(0);
			}
		}

		// Move left on the ground
		if (inputHandler.left.down) {
			setDirection(Direction.LEFT);
			if (isGrounded()) {
				setxSpeed(getxSpeed() - 1);
				setAnimation(walkLeftAnim);
			}
		}

		// Move right on the ground
		if (inputHandler.right.down) {
			setDirection(Direction.RIGHT);
			if (isGrounded()) {
				setxSpeed(getxSpeed() + 1);
				setAnimation(walkRightAnim);
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
				setxSpeed((getxSpeed() > 0 ? 1 : -1) * (Math.abs(getxSpeed()) - 0.25));
			else {
				setxSpeed(0);
			}
		}

		// Idle animation
		if (isGrounded() && getxSpeed() == 0) {
			setSprite( Assets.playerIdle[(getDirection() == Direction.LEFT ? 0 : 1)]);
			setAnimation(null);
		}

		// Tick animation and get sprite to render.
		if (getAnimation() != null) {
			getAnimation().tick();
			setSprite(getAnimation().getCurrentFrame());
		}
		
		// Move the player
		setX((int)Math.floor(getX() + getxSpeed()));
		setY((int)Math.floor(getY() - getySpeed()));

		System.out.printf("xSpeed = %.3f, ySpeed = %.3f, grounded = %s%n", getxSpeed(), getySpeed(), isGrounded());

		// Go to the other side if the limit is crossed
		if (getX() <= -1 * getWidth() / 2) {
			setX(game.getWidth() - getWidth() / 2 - 1);
		} else if (getX() >= game.getWidth() - getWidth() / 2) {
			setX(-1 * getWidth() / 2 + 1);
		}

		// Prevent the player from going above or below the screen
		if (getY() < -1 * getHeight() / 3) {
			// Ceiling
			setY(-1 * getHeight() / 3);
			setySpeed(getySpeed() * -0.3);
		} else if (getY() > game.getHeight() - getHeight()) {
			// Ground
			setY(game.getHeight() - getHeight());
			setySpeed(0);
			setGrounded(true);
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

			// If the player is just above the ground limit, mark it as grounded and set ySpeed = 0.
			if (getY() >= game.getHeight() - getHeight()) {
				setGrounded(true);
				setySpeed(0);
			}			
		}

		// Relocate hitbox
		if (isDying()) {
			getHitbox().setSize(0, 0);
			getHitbox().setLocation(getX() + (int)(Game.SCALE * (getWidth() / 2)), getY() + (int)(Game.SCALE * (getHeight() / 2)));
		} else {
			getHitbox().setLocation(getX() + (int)(Game.SCALE * 3), getY());
		}

		// Tick balloons
		balloons.tick();
	}

	@Override
	public void render(Graphics g) {
		if (isVisible()) {
			g.drawImage(getSprite(), getX(), getY(), getWidth(), getHeight(), null);

			// Render balloons
			balloons.render(g);

			// Draw hitbox (for debugging)
			//g.setColor(Color.red);
			//g.drawRect((int)hitbox.getX(), (int)hitbox.getY(), (int)hitbox.getWidth(), (int)hitbox.getHeight());
		}
	}

	/**
	 * @return the flapKeyReleased
	 */
	public boolean isFlapKeyReleased() {
		return flapKeyReleased;
	}

	/**
	 * @param flapKeyReleased the flapKeyReleased to set
	 */
	public void setFlapKeyReleased(boolean flapKeyReleased) {
		this.flapKeyReleased = flapKeyReleased;
	}
	
	public Balloon getBalloons() {
		return balloons;
	}
}
