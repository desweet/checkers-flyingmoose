/***********************************
 * Checker.java
 * Authors:   Corey Rausch
 * Team:	  Treadstone
 * Project:   Flying Moose
 * 
 * The Checker class is the object for each checkerpiece in
 * the game. Each checker has a color, an x and y position on
 * the checkerboard, and if it's a king or not. 
 */

package checkers;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Checker extends JPanel {
	private coordinate pos;
	private Color color;
	private boolean king;
	ImageIcon checker;
	
	public Checker(int x, int y, Color c){
		pos = new coordinate(x, y);
		color = c;
		kingMe();
		setPreferredSize(new Dimension(62, 62));
		setBackground(new Color(255, 255, 255));
		
		if (c.equals(Color.RED)){
			if (king)
				checker = new ImageIcon("crownred.gif", "a red king");
			else
				checker = new ImageIcon("checkred.gif", "a red checker");
		}else{
			if (king)
				checker = new ImageIcon("crownblack.gif", "a black king");
			else
				checker = new ImageIcon("checkblack.gif", "a black checker");
		}
	}
	
	public int getPosX(){
		return pos.getX();
	}
	
	public int getPosY(){
		return pos.getY();
	}
	
	//Sets the new coordinate and checks if it's a king now
	public void setCoor(int x, int y){
		pos.set(x, y);
		kingMe();
	}
	
	public void paintComponent(Graphics page) { 
        //creates the string that will be drawn on the screen 
		//Positions the checker in the center of a 62x62 pix square
        checker.paintIcon(this, page, 6, 6); 
    }
	
	//Kings the checker piece when it reaches the other end of the board
	public void kingMe(){
		if(color.equals(Color.RED)){
			if(pos.getY() == 0)
			{
				king = true;
				checker = new ImageIcon("crownred.gif", "a red king");
			}
			else if(king);
			else
				king = false;
		}else{
			if(pos.getY() == 7)
			{
				king = true;				
				checker = new ImageIcon("crownblack.gif", "a black king");
			}
			else if(king);
			else
				king = false;
		}
		repaint();
	}
	
	public void select(){
		if(king){
			if(color.equals(Color.RED))
				checker = new ImageIcon("crownred_select.gif", "a red selected king");
			else
				checker = new ImageIcon("crownblack_select.gif", "a black selected king");
		}else{
			if(color.equals(Color.RED))
				checker = new ImageIcon("checkred_select.gif", "a red selected");
			else
				checker = new ImageIcon("checkblack_select.gif", "a black selected");
		}
	}
	
	public String toString(){
		return pos + " " + king + " " + color.toString();
	}
}
