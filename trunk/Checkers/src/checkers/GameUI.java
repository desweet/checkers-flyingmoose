package checkers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import checkers.Checker;
import javax.swing.*;

public class GameUI extends JPanel{
	JFrame frame;
	JMenuBar menuBar;
	JMenu start, help;
	JMenuItem newGame, exit, detail;
	
	JLayeredPane lpane;
	JPanel bg, panel, piece, west, east, north, south, blank;
	JLabel clock;
	Font font;
	
	Checker checkerPiece, p1, p2, p3;
	ArrayList<Checker> blacks, reds;
	CheckerBoard checkerboard;
	Color background, checker;
	
	int pieceIndex;
	boolean clicked, red;		//red = false => black was chosen
	
	public GameUI(){
		frame = new JFrame("FUN GAME!!!!");
		pieceIndex = -1;
		clicked = false;
		
		menuBar = new JMenuBar();
		start = new JMenu("Start");
		start.setMnemonic(KeyEvent.VK_S);
		start.getAccessibleContext().setAccessibleDescription(
		        "Start new games and stuff.");
		menuBar.add(start);
		
		newGame = new JMenuItem("New Game", KeyEvent.VK_N);
		newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		newGame.getAccessibleContext().setAccessibleDescription("Start a new game");
		newGame.addActionListener(new NewGameListener());
		start.add(newGame);
		
		exit = new JMenuItem("Exit", KeyEvent.VK_X);
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		exit.getAccessibleContext().setAccessibleDescription("Exit the game");
		exit.addActionListener(new ExitListener());
		start.add(exit);
		
		help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_H);
		help.getAccessibleContext().setAccessibleDescription("Get Help HERE!");
		menuBar.add(help);
		
		detail = new JMenuItem("Details", KeyEvent.VK_D);
		detail.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		detail.getAccessibleContext().setAccessibleDescription("Details of this program");
		detail.addActionListener(new DetailListener());
		help.add(detail);
		
		frame.setJMenuBar(menuBar);

		//Set Background
		background = new Color(0, 255, 0, 250).darker().darker();		
		setBackground(background);
		setPreferredSize(new Dimension(700, 700));
		setLayout(new BorderLayout());
		
		north = new JPanel();
		west = new JPanel();
		east = new JPanel();
		south = new JPanel();
		north.setPreferredSize(new Dimension(450, 100));
		north.setBackground(background);
		west.setPreferredSize(new Dimension(100, 750));
		west.setBackground(background);
		east.setPreferredSize(new Dimension(100, 750));
		east.setBackground(background);
		south.setPreferredSize(new Dimension(450, 100));
		south.setBackground(background);
		
		clock = new JLabel("00:00:00");
		font = new Font(Font.MONOSPACED, 0, 36);
		clock.setFont(font);
		north.add(clock);
		
		blacks = new ArrayList<Checker>();
		reds = new ArrayList<Checker>();
		
		//Fill the checker array with initialized position values
		for(int j = 0; j < 3; j++)
		{
			for(int i = 0; i < 8; i++)
			{
				//Makes sure the pieces are only added to white squares
				if((j%2 == 0 && i%2 == 1) || (j%2 == 1 && i%2 == 0))
					reds.add(new Checker(i, j, Color.RED));

				if((j%2 == 0 && i%2 == 0) || (j%2 == 1 && i%2 == 1))
					blacks.add(new Checker(i, j+5, Color.BLACK));
			}
		}
		
		checkerboard = new CheckerBoard();
		checkerboard.setPreferredSize(new Dimension(496, 496));
		checkerboard.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.CENTER;

		for(int i = 0; i < 12; i++)
		{
			c.weightx = 0.1;
			c.gridx = reds.get(i).getPosX();
			c.gridy = reds.get(i).getPosY();
			checkerboard.add(reds.get(i), c);
			
			c.weightx = 0.1;
			c.gridx = blacks.get(i).getPosX();
			c.gridy = blacks.get(i).getPosY();
			checkerboard.add(blacks.get(i), c);
		}
		
		for(int l = 0; l < 8; l++)
		{
			for(int j = 0; j < 4; j++)
			{
				c.weightx = 0.1;
				c.gridx = (l%2) + j * 2;
				c.gridy = l;
				checkerboard.add(Box.createVerticalStrut(62), c);
			}
		}
		
		/*c.weighty = 0.1;
		c.weightx = 0.1;
		c.gridx = 1;
		c.gridy = 3;
		checkerboard.add(spacer, c);
		
		c.weighty = 0.1;
		c.weightx = 0.1;
		c.gridy = 4;
		c.gridx = 0;
		checkerboard.add(spacer, c);*/
		
		checkerboard.addMouseListener(new ClickListener());
		
		add(east, BorderLayout.EAST);
		add(north, BorderLayout.NORTH);
		add(checkerboard, BorderLayout.CENTER);
		add(west, BorderLayout.WEST);
		add(south, BorderLayout.SOUTH);
		
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void boardUpdate()
	{
		GridBagConstraints c = new GridBagConstraints();
		if(red)
		{
			c.gridx = reds.get(pieceIndex).getPosX();
			c.gridy = reds.get(pieceIndex).getPosY();
		}else
		{
			c.gridx = blacks.get(pieceIndex).getPosX();
			c.gridy = blacks.get(pieceIndex).getPosY();
		}
		c.weighty = 0.1;
		c.weightx = 0.1;
		if(red)
			checkerboard.add(reds.get(pieceIndex), c);
		else
			checkerboard.add(blacks.get(pieceIndex), c);
		checkerboard.revalidate();
		frame.repaint();
	}
	
	//Creates listener for the details menu item
	public class ClickListener implements MouseListener 
	{
		public void mouseClicked(MouseEvent click) 
		{
			int x = click.getX();
			int y = click.getY();
			int xCoor = (int)Math.floor(x/62);
			int yCoor = (int)Math.floor(y/62);
			
			if(!clicked)
			{
				for(Checker piece : reds)
				{
					if(piece.getPosX() == xCoor && piece.getPosY() == yCoor)
					{
						pieceIndex = reds.indexOf(piece);
						clicked = true;
						red = true;
					}
				}for(Checker piece : blacks)
				{
					if(piece.getPosX() == xCoor && piece.getPosY() == yCoor)
					{
						pieceIndex = blacks.indexOf(piece);
						clicked = true;
						red = false;
					}
				}
				if(!clicked)
					JOptionPane.showMessageDialog(checkerboard, "Improper CLICK!!!!");
			}else
			{
				if(!((xCoor % 2 + yCoor % 2)  == 0 || (xCoor % 2 + yCoor % 2) == 2))
				{
					if(red)
						reds.get(pieceIndex).setCoor(xCoor, yCoor);
					else
						blacks.get(pieceIndex).setCoor(xCoor, yCoor);
					clicked = false;
					boardUpdate();
				}else
					JOptionPane.showMessageDialog(checkerboard, "YOU FAIL!");
			}
			System.out.println("X = " + xCoor + " and Y = " + yCoor + " Clicked  " + clicked + " Piece Index = " + pieceIndex);
		}//end mouseClicked class

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent click) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}//end listener
	
	//Creates listener for the exit call
	public class ExitListener implements ActionListener 
	{
	    public void actionPerformed(ActionEvent event) 
	    {
	        System.exit(0);
	    }//end actionPerformed class
	    
	}//end listener
	
	//Creates listener for the details menu item
	public class DetailListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			JOptionPane.showMessageDialog(bg, "Details and stuff\n");
		}//end actionPerformed class
		
	}//end listener
	
	//Creates listener for the details menu item
	public class NewGameListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
	
		}//end actionPerformed class
	
	}//end listener
	
	public static void main(String[] args) {
		/*JFrame frame = new JFrame("BOARD");
		//GameUI check = new GameUI();
		frame.getContentPane().add(new GameUI());
		frame.pack();
		frame.setVisible(true);*/
		GameUI gnd = new GameUI();
	}
}
