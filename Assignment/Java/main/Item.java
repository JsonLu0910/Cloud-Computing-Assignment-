package dev.marianoalipi.balloonbattle;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author MarianoAlipi
 */
public abstract class Item {
	protected int x; // x position
	protected int y; // y position
	protected int width;
	protected int height;
	protected Rectangle hitbox;

	/**
	 * Set initial values to create the item
	 * 
	 * @param x
	 *            <b>x</b> position of the object
	 * @param y
	 *            <b>y</b> position of the object
	 */
	public Item(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Item(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
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

	/**
	 * To update the behavior of the item for every tick
	 */
	public abstract void tick();

	/**
	 * To paint the item
	 * 
	 * @param g
	 *            <b>Graphics</b> object to paint the item
	 */
	public abstract void render(Graphics g);
}
