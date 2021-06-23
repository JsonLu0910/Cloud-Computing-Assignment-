package dev.marianoalipi.balloonbattle.entities;

import java.awt.Graphics;

import dev.marianoalipi.balloonbattle.Animation;
import dev.marianoalipi.balloonbattle.Assets;
import dev.marianoalipi.balloonbattle.Game;
import dev.marianoalipi.balloonbattle.Sound;

public class Balloon extends Entity {

	private Entity owner;
	private int balloonsAmount;
	public enum BalloonColor {RED, PINK, GREEN, YELLOW};
	private BalloonColor balloonColor;
	private Animation balloonsTwoAnim, balloonsOneAnim;
	private boolean invincible, parachute;
	private byte invincibleFrames;
	
	public Balloon () {
		super();
	}
	
	public Balloon (int x, int y, int width, int height, int balloonsAmount, BalloonColor balloonColor, Game game, Entity owner) {
		super(x, y, width, height, game);
		this.owner = owner;
		this.sprite = null;
		this.setBalloonColor(balloonColor);
		this.balloonsTwoAnim = new Animation(Assets.balloonsTwo.get(getBalloonColor().toString()), 400);
		this.balloonsOneAnim = new Animation(Assets.balloonsOne.get(getBalloonColor().toString()), 400);
		this.animation = balloonsTwoAnim;
		this.direction = Direction.LEFT;
		this.balloonsAmount = balloonsAmount;
		this.invincible = false;
		this.setParachute(false);
		this.invincibleFrames = (owner.getClass() == Player.class ? (byte)30 : (byte)10);
	}
	
	@Override
	public void tick() {
		
		if (owner instanceof Player) {
			
			// Adjust a little horizontal offset for when the player is facing right
			if (owner.getDirection() == Direction.RIGHT)
				if (owner.isGrounded())
					setX(owner.getX());
				else
					setX(owner.getX() + 4);
			else
				setX(owner.getX());
			// Adjust a little vertical offset for when the player is walking
			if (owner.getAnimation() == Player.walkLeftAnim || owner.getAnimation() == Player.walkRightAnim)
				setY(owner.getY() - getHeight() + 3);
			else
				setY(owner.getY() - getHeight());
			
			// Remove invincibility frames
			if (isInvincible()) {
				if (--invincibleFrames <= 0) {
					setInvincible(false);
					invincibleFrames = 30;
				}
			}
			
		} else if (owner instanceof Enemy) {
			
			if (!isInvincible()) {
				// Check for collision with player
				Player player = game.getPlayer(); 
				if (getHitbox().intersects(player.getHitbox())) {
					
					// Play sound
					Sound.hit.play();
					
					// Remove one balloon
					if (getBalloonsAmount() > 0) {
						setBalloonsAmount(getBalloonsAmount() - 1);
						setInvincible(true);
						
						if (getBalloonsAmount() <= 0) {
							setParachute(true);
							setInvincible(true);
						}	
					} else {
						if (isParachute()) {
							setParachute(false);
							owner.setySpeed(5);
							owner.setDying(true);
						}
					}
					
					// Make the player bounce a little
					double hitboxCenterX = getHitbox().getX() + getHitbox().getWidth() / 2,
						   playerCenterX = player.getHitbox().getX() + player.getHitbox().getWidth() / 2;
					if (playerCenterX <= hitboxCenterX)
						player.setxSpeed(Math.abs(player.getxSpeed()) * -1);
					else
						player.setxSpeed(Math.abs(player.getxSpeed()));
					
					if (player.getY() + player.getHitbox().getHeight() >= getHitbox().getY())
						player.setySpeed(0.5 * Math.abs(player.getySpeed()));
				}
			}

			// Remove invincibility frames
			if (isInvincible())
				if (--invincibleFrames <= 0) {
					setInvincible(false);
					invincibleFrames = 30;
				}
			
			// Adjust a little horizontal offset for when the enemy is facing right
			if (owner.getDirection() == Direction.RIGHT)
				setX(owner.getX());
			else
				setX(owner.getX());
			
			setY(owner.getY() - getHeight() + 3);
		}
		
		// Relocate hitbox and adjust size
		if (getBalloonsAmount() == 2) {
			getHitbox().setLocation(getX(), getY());
			getHitbox().setSize((int)(Game.SCALE * 16), (int)(Game.SCALE * 12));
		} else if (getBalloonsAmount() == 1) {
			getHitbox().setLocation(getX() + (int)(Game.SCALE * 4), getY());
			getHitbox().setSize((int)(Game.SCALE * 8), (int)(Game.SCALE * 12));
		} else if (isParachute()) {
			getHitbox().setLocation(getX(), getY());
			getHitbox().setSize((int)(Game.SCALE * 16), (int)(Game.SCALE * 12));
		} else {
			getHitbox().setLocation(owner.getX() + (int)(owner.getWidth() / 2), owner.getY() + (int)(owner.getHeight() / 2));
			getHitbox().setSize(0, 0);
		}
		
		// Tick animation and update sprite
		if (owner.isGrounded()) {
			if (getAnimation() != null) {
				getAnimation().tick();
				setSprite(getAnimation().getCurrentFrame());
			} else {
				setSprite(null);
			}
		} else {
			if (getAnimation() != null)
				setSprite(getAnimation().getFrames()[0]);
			else if (isParachute())
				setSprite(Assets.parachute);
			else
				setSprite(null);
		}
		
		
		setAnimation(getBalloonsAmount() == 2 ? balloonsTwoAnim : (getBalloonsAmount() == 1 ? balloonsOneAnim : null));
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getSprite(), getX(), getY(), getWidth(), getHeight(), null);
		
		// Draw hitbox (for debugging)
		//g.setColor(Color.red);
		//g.drawRect((int)hitbox.getX(), (int)hitbox.getY(), (int)hitbox.getWidth(), (int)hitbox.getHeight());
	}

	/**
	 * @return the balloonsAmount
	 */
	public int getBalloonsAmount() {
		return balloonsAmount;
	}

	public boolean isInvincible() {
		return invincible;
	}
	
	/**
	 * @param balloonsAmount the balloonsAmount to set
	 */
	public void setBalloonsAmount(int balloonsAmount) {
		this.balloonsAmount = balloonsAmount;
	}

	/**
	 * @return the balloonColor
	 */
	public BalloonColor getBalloonColor() {
		return balloonColor;
	}

	/**
	 * @param balloonColor the balloonColor to set
	 */
	public void setBalloonColor(BalloonColor balloonColor) {
		this.balloonColor = balloonColor;
	}
	
	public void setInvincible(boolean invincible) {
		this.invincible = invincible;
	}

	/**
	 * @return the parachute
	 */
	public boolean isParachute() {
		return parachute;
	}

	/**
	 * @param parachute the parachute to set
	 */
	public void setParachute(boolean parachute) {
		this.parachute = parachute;
	}

}
