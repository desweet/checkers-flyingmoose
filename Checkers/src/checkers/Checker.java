package checkers;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Checker extends JPanel {
	private int posX, posY, xSpot, ySpot;
	private Color color;
	private boolean king;
	ImageIcon checker;
	
	public Checker(int x, int y, Color c){
		posX = x;
		posY = y;
		xSpot = (posX * 62)+4;
		ySpot = (posY * 62)+1;
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
		return posX;
	}
	
	public int getPosY(){
		return posY;
	}
	
	public int getXSpot(){
		return xSpot;
	}
	
	public int getYSpot(){
		return ySpot;
	}
	
	public void setCoor(int x, int y){
		posX = x;
		posY = y;
		kingMe();
	}
	
	public void paintComponent(Graphics page) { 
         //creates the string that will be drawn on the screen 
         checker.paintIcon(this, page, 6, 6); 
    }
	
	public void kingMe(){
		if(color.equals(Color.RED)){
			if(posY == 7)
			{
				king = true;
				checker = new ImageIcon("crownred.gif", "a red king");
			}
			else if(king);
			else
				king = false;
		}else{
			if(posY == 0)
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
	
	public String toString(){
		return posX + " " + posY + " " + color.toString();
	}
	
/*	public void paint(Graphics g)
    {
        super.paint(g);
        //Draws the line
        g.drawOval(xSpot, ySpot, DIAMETER, DIAMETER);

        //draws filled circle
        g.setColor(color); 
        g.fillOval(xSpot, ySpot, DIAMETER, DIAMETER);
        if(king){
        	g.drawImage(checker, xSpot, ySpot, null);
        }
    }*/
	
	public static void main(String[] args){
		JFrame check = new JFrame();
		
		check.add(new Checker(0, 0, Color.BLACK));
		check.add(new Checker(2, 2, Color.RED));
		check.setPreferredSize(new Dimension(500, 500));
		check.pack();
		check.setVisible(true);
	}
}
