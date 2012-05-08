package checkers;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CheckerBoard extends JPanel{
	private final int XDIM = 496, YDIM = 496;
	ImageIcon checkerboard;
	
	public CheckerBoard(){
		setPreferredSize(new Dimension(XDIM, YDIM));
		checkerboard = new ImageIcon("checkerboard.gif", "a checkered board");
	}
	
	public void paintComponent(Graphics page) { 
        //creates the string that will be drawn on the screen 
        checkerboard.paintIcon(this, page, 0, 0); 
   }
}