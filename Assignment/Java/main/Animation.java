/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.marianoalipi.balloonbattle;

import java.awt.image.BufferedImage;

/**
*
* @author MarianoAlipi
*/
public class Animation {
    private int speed;  // the speed of every frame
    private int index;  // the index of the next frame to render
    private long lastTime;  // the previous time of the animation
    private long timer;     // to accumulate the time of the animation
    private BufferedImage[] frames; // to store every image/frame

    /**
     * Creating the animation with all the frames and the speed for each
     * @param frames an <code>array</code> of images
     * @param speed  an <code>int</code> value for the speed of every frame
     */
    public Animation(BufferedImage[] frames, int speed) {
        this.frames = frames;
        this.speed = speed;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }
    
    /**
     * To update the animation to get the right index of the frame to render.
     */
    public void tick() {
        // Accumulating time from the previous tick to this one.
        timer += System.currentTimeMillis() - lastTime;
        // Updating the lastTime for the next tick
        lastTime = System.currentTimeMillis();
        // Check the timer to increase the index
        if (timer > speed) {
            timer = 0;
            
            index++;
            if (index >= frames.length) {
                index = 0;
            }
        }
    }
    
    /**
     * Get the current frame to render
     * @return the <code>BufferedImage</code> to the corresponding frame to render
     */
    public BufferedImage getCurrentFrame() {
        return frames[index];
    }
    
    public BufferedImage[] getFrames() {
		return frames;
	}

}
