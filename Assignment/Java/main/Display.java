package dev.marianoalipi.balloonbattle;

import java.awt.Canvas;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Image;

/**
 *
 * @author MarianoAlipi
 */
public class Display {
	
    private JFrame jframe; 
    private Canvas canvas; 
    private String title; 
    private int width; 
    private int height; 

    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        createDisplay();
    }
    
     public void createDisplay() {
	     jframe = new JFrame(title);
	     jframe.setSize(width, height);
	     
	     jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     jframe.setResizable(false);
	     jframe.setLocationRelativeTo(null);
	     jframe.setIconImage((Image)Assets.icon);
	     jframe.setVisible(true);
	     
	     //create the canvas to paint and setting size
	     canvas = new Canvas();
	     canvas.setPreferredSize(new Dimension(width,height));
	     canvas.setMaximumSize(new Dimension(width, height));
	     canvas.setFocusable(false); //ignore display while keyboard input from jFrame
	     jframe.add(canvas);
	     jframe.pack();	     
     }
     
     /**
      * Gets the JFrame
      * @return jframe
      */
     public JFrame getJframe() {
    	 return jframe;
	 }
     
     /**
      * Gets the canvas
      * @return canvas
      */
     public Canvas getCanvas() {
    	 return canvas;
	 }
     
}
