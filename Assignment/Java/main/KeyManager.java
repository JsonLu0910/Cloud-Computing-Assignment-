package dev.marianoalipi.balloonbattle;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author MarianoAlipi
 */
public class KeyManager implements KeyListener{
    public boolean up;    //flag to move up the player
    public boolean down;  //flag to move down the player
    public boolean left;  //flag to move left the player
    public boolean right; //flag to move right the player
    public boolean z;	  // A button
    public boolean x; 	  // B button
    public boolean enter; // START button
    
    public boolean keys[]; //stores all the flags for every key
    
    public KeyManager() {
      keys = new boolean[256];
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
       keys[e.getKeyCode()] = true;
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
    
    public void tick(){
      up = keys[KeyEvent.VK_UP];
      down = keys[KeyEvent.VK_DOWN];
      left = keys[KeyEvent.VK_LEFT];
      right = keys[KeyEvent.VK_RIGHT];
      z = keys[KeyEvent.VK_Z];
      x = keys[KeyEvent.VK_X];
      enter = keys[KeyEvent.VK_ENTER];
    }
}
