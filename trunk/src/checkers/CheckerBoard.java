/***************************************************************
 * Checkerboard.java
 * Author:  Corey Rausch
 * Team:    Treadstone
 * Project: Flying Moose
 * 
 * The checkerboard JPanel class provides the background board
 * for the game on which checkers will be placed
 * 
 **************************************************************/

package checkers;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CheckerBoard extends JPanel{
	//The checkerboard will fit in 496x496 pixel square containing an
	//8x8 array of evenly sized squares at 62x62 pix
	private final int XDIM = 496, YDIM = 496;
	ImageIcon checkerboard;
	
	public CheckerBoard(){
		setPreferredSize(new Dimension(XDIM, YDIM));
		//Uses a pre-drawn GIF for the board background
		checkerboard = new ImageIcon("checkerboard.gif", "a checkered board");
	}
	
	public void paintComponent(Graphics page) { 
        //paints the  checkerboard onto the jpanel
        checkerboard.paintIcon(this, page, 0, 0); 
   }
}