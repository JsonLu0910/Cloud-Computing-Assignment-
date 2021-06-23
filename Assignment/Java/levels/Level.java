package dev.marianoalipi.balloonbattle.levels;

import java.awt.Graphics;
import java.util.ArrayList;

import dev.marianoalipi.balloonbattle.Game;

public class Level {

	private byte id;
	private ArrayList<Platform> platforms;
	private boolean playerOnPlatform;
	private Game game;
	
	public Level() {
		this.id = -1;
		this.platforms = new ArrayList<Platform>();
		this.playerOnPlatform = false;
		this.game = null;
	}
	
	public Level(byte id, Game game) {
		this.id = id;
		this.platforms = new ArrayList<Platform>();
		this.playerOnPlatform = false;
		this.game = game;
	}
	
	public void tick() {
		setPlayerOnPlatform(false);
		for (Platform platform : platforms)
			platform.tick();
		game.getPlayer().setOnPlatform(isPlayerOnPlatform());
	}
	
	public void render(Graphics g) {
		for (Platform platform : platforms) {
			platform.render(g);
		}
	}
	
	public byte getId() {
		return id;
	}
	
	public ArrayList<Platform> getPlatforms() {
		return platforms;
	}
	
	public boolean isPlayerOnPlatform() {
		return playerOnPlatform;
	}
	
	public void setId(byte id) {
		this.id = id;
	}
	
	public void setPlayerOnPlatform(boolean playerOnPlatform) {
		this.playerOnPlatform = playerOnPlatform;
	}
}