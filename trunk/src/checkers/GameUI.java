package checkers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import checkers.Checker;
import javax.swing.*;

public class GameUI extends JPanel{
	Checkers backend;
	MainScreen screen;
	coordinate mouseIn;
	JFrame frame;
	JMenuBar menuBar;
	JMenu start, help;
	JMenuItem newGame, exit, detail;
	boolean cpu;
	JLayeredPane lpane;
	JPanel bg, panel, piece, west, east, north, south, blank;
	JLabel clock,mes;
	Font font;
	Cursor cursRed,cursBlack,cursRedKing,cursBlackKing;
	Checker checkerPiece, p1, p2, p3;
	ArrayList<Checker> blacks, reds;
	CheckerBoard checkerboard;
	Color background, checker;
	
	int pieceIndex, turn;
	boolean clicked, red;		//If red = false => black was chosen
	
	public GameUI(){
		backend = new Checkers();
		turn = 1;
		
		frame = new JFrame("FUN GAME!!!!");
		pieceIndex = -1;
		clicked = false;
		
		menuBar = new JMenuBar();
		
		//Creating the start menu
		start = new JMenu("Start");
		start.setMnemonic(KeyEvent.VK_S);
		start.getAccessibleContext().setAccessibleDescription(
		        "Start new games and stuff.");
		menuBar.add(start);
		
		//Creating the New Game start menu option
		newGame = new JMenuItem("New Game", KeyEvent.VK_N);
		newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		newGame.getAccessibleContext().setAccessibleDescription("Start a new game");
		newGame.addActionListener(new NewGameListener());
		start.add(newGame);
		
		//Creates the Exit start menu option to exit the program
		exit = new JMenuItem("Exit", KeyEvent.VK_X);
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		exit.getAccessibleContext().setAccessibleDescription("Exit the game");
		exit.addActionListener(new ExitListener());
		start.add(exit);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();  
		  
		//Load an image for the cursor  
		Image image1 = toolkit.getImage("checkred.gif");  
		Image image2 = toolkit.getImage("checkblack.gif");  
		Image image3 = toolkit.getImage("crownred.gif");  
		Image image4 = toolkit.getImage("crownblack.gif");  
		
		Point hotSpot = new Point(13,13);
		cursRed =  toolkit.createCustomCursor(image1,hotSpot,"red");
		cursRedKing =  toolkit.createCustomCursor(image3,hotSpot,"red");
		cursBlack =  toolkit.createCustomCursor(image2,hotSpot,"red");
		cursBlackKing =  toolkit.createCustomCursor(image4,hotSpot,"red");
		
		cpu = false;
		//Creates the Help menu
		help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_H);
		help.getAccessibleContext().setAccessibleDescription("Get Help HERE!");
		menuBar.add(help);
		
		//Creates the Details option for the Help menu
		detail = new JMenuItem("Details", KeyEvent.VK_D);
		detail.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		detail.getAccessibleContext().setAccessibleDescription("Details of this program");
		detail.addActionListener(new DetailListener());
		help.add(detail);
		
		//Add the menu bar to the game frame
		frame.setJMenuBar(menuBar);
		//frame.setCursor(new Cursor());
		//Set Background
		background = new Color(0, 255, 0, 250).darker().darker();		
		setBackground(background);
		setPreferredSize(new Dimension(700, 700));
		setLayout(new BorderLayout());		//Set up to make spaces around the gameboard
		
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
		
		//Set up a clock in the top panel to keep track of game time
		clock = new JLabel("00:00:00");
		font = new Font(Font.MONOSPACED, 0, 36);
		clock.setFont(font);
		north.add(clock);
		
		//Instantiate the black and red checker sets
		blacks = new ArrayList<Checker>();
		reds = new ArrayList<Checker>();
		
		//Fill the checker array with initialized position values
		for(int j = 0; j < 3; j++)
		{
			for(int i = 0; i < 8; i++)
			{
				//Makes sure the pieces are only added to white squares
				if((j%2 == 0 && i%2 == 0) || (j%2 == 1 && i%2 == 1))
					reds.add(new Checker(i, j+5, Color.RED));

				if((j%2 == 0 && i%2 == 1) || (j%2 == 1 && i%2 == 0))
					blacks.add(new Checker(i, j, Color.BLACK));
			}
		}
		
		//Setting up the checker board. Uses a GridBagLayout to allow easy movement
		//for the checkers around the board
		checkerboard = new CheckerBoard();
		checkerboard.setPreferredSize(new Dimension(496, 496));
		checkerboard.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.CENTER;

		//Adds checkers to their initial positions on the checker board
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
		
		//Fills the gray squares with vertical struts to ensure proper spacing
		//in every row
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
		
		//The checker board uses a mouse listener to read player mouse clicks
		//in order to determine which piece they want to move
		checkerboard.addMouseListener(new ClickListener());
		
		//adds each panel to its respective position in the frame
		add(east, BorderLayout.EAST);
		add(north, BorderLayout.NORTH);
		add(checkerboard, BorderLayout.CENTER);
		add(west, BorderLayout.WEST);
		add(south, BorderLayout.SOUTH);
		mes = new JLabel("");
		font = new Font(Font.SERIF, 0, 36);
		mes.setFont(font);
		south.add(mes);
		
		clock.setText("Black turn");
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
	}
	public GameUI(MainScreen screen, boolean cpu)
	{
		this(cpu);
		this.screen = screen;
	}
	public GameUI(MainScreen screen){
		this();
		this.screen = screen;
	}
	public GameUI(boolean cpu){
		backend = new Checkers();
		turn = 1;
		cpu = true;
		frame = new JFrame("FUN GAME!!!!");
		pieceIndex = -1;
		clicked = false;
		Toolkit toolkit = Toolkit.getDefaultToolkit();  
		  
		//Load an image for the cursor  
		Image image1 = toolkit.getImage("checkred.gif");  
		Image image2 = toolkit.getImage("checkblack.gif");  
		Image image3 = toolkit.getImage("crownred.gif");  
		Image image4 = toolkit.getImage("crownblack.gif");  
		
		Point hotSpot = new Point(12,12);
		cursRed =  toolkit.createCustomCursor(image1,hotSpot,"red");
		cursRedKing =  toolkit.createCustomCursor(image3,hotSpot,"red");
		cursBlack =  toolkit.createCustomCursor(image2,hotSpot,"red");
		cursBlackKing =  toolkit.createCustomCursor(image4,hotSpot,"red");
		
		menuBar = new JMenuBar();
		
		//Creating the start menu
		start = new JMenu("Start");
		start.setMnemonic(KeyEvent.VK_S);
		start.getAccessibleContext().setAccessibleDescription(
		        "Start new games and stuff.");
		menuBar.add(start);
		
		//Creating the New Game start menu option
		newGame = new JMenuItem("New Game", KeyEvent.VK_N);
		newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		newGame.getAccessibleContext().setAccessibleDescription("Start a new game");
		newGame.addActionListener(new NewGameListener());
		start.add(newGame);
		
		//Creates the Exit start menu option to exit the program
		exit = new JMenuItem("Exit", KeyEvent.VK_X);
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		exit.getAccessibleContext().setAccessibleDescription("Exit the game");
		exit.addActionListener(new ExitListener());
		start.add(exit);
		
		//Creates the Help menu
		help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_H);
		help.getAccessibleContext().setAccessibleDescription("Get Help HERE!");
		menuBar.add(help);
		
		//Creates the Details option for the Help menu
		detail = new JMenuItem("Details", KeyEvent.VK_D);
		detail.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		detail.getAccessibleContext().setAccessibleDescription("Details of this program");
		detail.addActionListener(new DetailListener());
		help.add(detail);
		
		//Add the menu bar to the game frame
		frame.setJMenuBar(menuBar);

		//Set Background
		background = new Color(0, 255, 0, 250).darker().darker();		
		setBackground(background);
		setPreferredSize(new Dimension(700, 700));
		setLayout(new BorderLayout());		//Set up to make spaces around the gameboard
		
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
		
		//Set up a clock in the top panel to keep track of game time
		clock = new JLabel("00:00:00");
		font = new Font(Font.MONOSPACED, 0, 36);
		clock.setFont(font);
		north.add(clock);
		mes = new JLabel("");
		font = new Font(Font.SERIF, 0, 36);
		mes.setFont(font);
		south.add(mes);
		
		//Instantiate the black and red checker sets
		blacks = new ArrayList<Checker>();
		reds = new ArrayList<Checker>();
		
		//Fill the checker array with initialized position values
		for(int j = 0; j < 3; j++)
		{
			for(int i = 0; i < 8; i++)
			{
				//Makes sure the pieces are only added to white squares
				if((j%2 == 0 && i%2 == 0) || (j%2 == 1 && i%2 == 1))
					reds.add(new Checker(i, j+5, Color.RED));

				if((j%2 == 0 && i%2 == 1) || (j%2 == 1 && i%2 == 0))
					blacks.add(new Checker(i, j, Color.BLACK));
			}
		}
		
		//Setting up the checker board. Uses a GridBagLayout to allow easy movement
		//for the checkers around the board
		checkerboard = new CheckerBoard();
		checkerboard.setPreferredSize(new Dimension(496, 496));
		checkerboard.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.CENTER;

		//Adds checkers to their initial positions on the checker board
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
		
		//Fills the gray squares with vertical struts to ensure proper spacing
		//in every row
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
		
		//The checker board uses a mouse listener to read player mouse clicks
		//in order to determine which piece they want to move
		checkerboard.addMouseListener(new ClickListener());
		
		//adds each panel to its respective position in the frame
		add(east, BorderLayout.EAST);
		add(north, BorderLayout.NORTH);
		add(checkerboard, BorderLayout.CENTER);
		add(west, BorderLayout.WEST);
		add(south, BorderLayout.SOUTH);
		
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
		backend.CPUturn();
		if (backend.getTurn() == -1)
		{	clock.setText("Red turn");
			mes.setText("");
		}
		else
		{	clock.setText("Black turn");
			mes.setText("");
		}
		boardUpdate(cpu);
	}
	public void boardUpdate(boolean cpu)
	{
		this.cpu = cpu;
		checkerboard.removeAll();
		GridBagConstraints c = new GridBagConstraints();
		Board b = backend.b;
		Piece p;
		Checker piece;
		//cpu = true;
		reds.clear();
		blacks.clear();
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				p = b.getPiece(new coordinate(i, j));
				if(p != null){
					if(p.getColor() == -1){
						if(p.isKing())
							piece = new Checker(0, 0, Color.RED);
						else
							piece = new Checker(2, 1, Color.RED);
						reds.add(new Checker(j, i, Color.RED));
					}else{
						if(p.isKing())
							piece = new Checker(7, 7, Color.BLACK);
						else
							piece = new Checker(2, 0, Color.BLACK);
						blacks.add(new Checker(j, i, Color.BLACK));
					}
					c.gridx = j;
					c.gridy = i;
					c.weighty = 0.1;
					c.weightx = 0.1;
					checkerboard.add(piece, c);
				}
					
			}
		}
		for(int l = 0; l < 8; l++)
		{
			for(int j = 0; j < 4; j++)
			{
				c.weightx = 0.1;
				c.gridx = (l%2) + j * 2;
				c.gridy = l;
				checkerboard.add(Box.createRigidArea(new Dimension(62, 62)), c);
			}
		}
		checkerboard.revalidate();
		frame.repaint();
		
	}
	
	//Creates listener for Clicking the checker board.
	public class ClickListener implements MouseListener 
	{
		public void mouseClicked(MouseEvent click) 
		{
			
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
			if (click.getButton() != click.BUTTON1)
				;
			else
			{
			int x = click.getX();
			int y = click.getY();
			coordinate pos = new coordinate((int)Math.floor(x/62), (int)Math.floor(y/62));
			mouseIn = pos;
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			if (arg0.getButton() != arg0.BUTTON1)
				{
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				clicked = false;
				return;
				}
			int x = arg0.getX();
			int y = arg0.getY();
			coordinate pos = new coordinate((int)Math.floor(x/62), (int)Math.floor(y/62));
			if (pos.equals(mouseIn))
			{	
				GridBagConstraints c = new GridBagConstraints();

				
				//Divides the cursor mouse position by the number of pixels in each
				//cell to give the x and y coordinate of the square chosen
			//	int x = arg0.getX();
				//int y = click.getY();
			//	coordinate pos = new coordinate((int)Math.floor(x/62), (int)Math.floor(y/62));
				int xCoor = (int)Math.floor(x/62);
				int yCoor = (int)Math.floor(y/62);
				System.out.println(new coordinate(xCoor,yCoor));
				//If a piece was not clicked in the previous time
				//it checks if you clicked a piece, otherwise, it's an improper move
				if(!clicked)
				{
					//Checks through the Red pieces for a click
					if(backend.getTurn() == -1)
					{
						System.out.println("red" + reds.size());
						for(Checker piece : reds)
						{
							if(piece.getPosX() == pos.getX() && piece.getPosY() == pos.getY())
							{
								//If a red piece was clicked, this stores the the index
								//of the piece in the reds ArrayList, and true to the if 
								//there was a piece click and that it was a red piece
								c.gridx = pos.getX();
								c.gridy = pos.getY();
								c.weightx = 0.1;
								c.weighty = 0.1;
								checkerboard.add(piece, c);
								if (backend.b.getPiece(new coordinate(c.gridy,c.gridx)).isKing())
									setCursor(cursRedKing);
								else
									setCursor(cursRed);
								checkerboard.remove(piece);
							//	piece.select();
							//	piece.repaint();
							//	piece.revalidate();
							//	piece = new Checker(piece, true);
								
							//	boardUpdate(cpu);
								checkerboard.revalidate();
								pieceIndex = reds.indexOf(piece);
								clicked = true;
								red = true;
								break;
							}
						}
					}
					//Checks through the Black pieces for a click. (Should never be both)
					else if(backend.getTurn() == 1)
					{
						System.out.println("black"+blacks.size());

						for(Checker piece : blacks)
						{
							if(piece.getPosX() == xCoor && piece.getPosY() == yCoor)
							{
								//If a black piece was clicked, this stores the the index
								//of the piece in the blacks ArrayList, a true to the if 
								//there was a piece click, and false that it was a red piece
								c.gridx = xCoor;
								c.gridy = yCoor;
								c.weightx = 0.1;
								c.weighty = 0.1;
								if (backend.b.getPiece(new coordinate(c.gridy,c.gridx)).isKing())
									setCursor(cursBlackKing);
								else
									setCursor(cursBlack);
								checkerboard.remove(piece);
							//	piece.select();
							//	piece = new Checker(piece,true);
							//	checkerboard.add(piece, c);
								checkerboard.revalidate();
								pieceIndex = blacks.indexOf(piece);
								clicked = true;
								red = false;
							}
						}
					}
					//Improper click if you don't click a checker
					if(!clicked)
						//JOptionPane.showMessageDialog(checkerboard, "Improper CLICK!!!!");
					{	;
					
					
					}
				}else	//This is the piece movement section
				{
					if(red)
					{
						Move[]possible = new Move[25];
						possible = backend.calcPossibleMove();
						Move m = new Move(new coordinate(reds.get(pieceIndex).getPosY(), reds.get(pieceIndex).getPosX()), 
										  new coordinate(yCoor, xCoor));
						if(backend.validMove(backend.calcPossibleMove(), m)){
							backend.move(m, null);
							if (backend.getTurn() == -1)
							{	clock.setText("Red turn");
								mes.setText("");
							}
							else
							{	clock.setText("Black turn");
								mes.setText("");
							}
							boardUpdate(cpu);
							backend.b.toString();
							clicked = false;
							if(backend.calcPossibleMove()[0] == null){
								JOptionPane.showMessageDialog(checkerboard, "Red Wins!\n");
								try{
									BufferedWriter clearsave = new BufferedWriter(new FileWriter("save.txt"));
									clearsave.write("");
									clearsave.close();
								}
								catch(Exception e)
								{
									System.out.println(e.getStackTrace());
								}
								frame.setVisible(false);
								
								
								if (screen!= null)
									screen.setVisible(true);
								try {
									BufferedReader in
									= new BufferedReader(new FileReader("stats.txt"));
									String line1 = in.readLine();
									String line2 = in.readLine();
									int w,l,n;
									int a, b;

									if (cpu)
									{
										a = line2.indexOf(' ');
										b = line2.indexOf(' ',a+1);
										w = Integer.parseInt(line2.substring(0, a).trim())+1;
										l = Integer.parseInt(line2.substring(a,b).trim());
										n = Integer.parseInt(line2.substring(b).trim());
										if (backend.moveCount < n)
											n = backend.moveCount;
										line2 = w + " " + l + " " + n;
									}
									else
									{
										a = line1.indexOf(' ');
										b = line1.indexOf(' ',a+1);
										w = Integer.parseInt(line1.substring(0, a).trim()) + 1;
										l = Integer.parseInt(line1.substring(a,b).trim());
										n = Integer.parseInt(line1.substring(b).trim());
										if (backend.moveCount < n)
											n = backend.moveCount;
										line1 = w + " " + l + " " + n;
									}
									in.close();
									BufferedWriter out;

									out = new BufferedWriter(new FileWriter("stats.txt"));

									out.write(line1);
									out.write("\n");
									out.write(line2);
									out.close();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								boardUpdate(cpu);
							}
							else if (cpu)
							{
								while(backend.getTurn() == 1)
								{
									boardUpdate(cpu);
									long a = System.nanoTime();
									long b = 0;
								long dif = 0;
									while(dif <= 10)//this could potentially be a spacer to actually see the computer moves slower, takes very large value to notice
								{
										b = System.nanoTime();
										//System.out.println(b);
										dif = (b - a);
									}
									System.out.println("dif " + dif);
									backend.CPUturn();
									if (backend.getTurn() == -1)
										clock.setText("Red turn");
									else
										clock.setText("Black turn");
									boardUpdate(cpu);
								}
								boardUpdate(cpu);
								backend.b.toString();
								clicked = false;
								if(backend.calcPossibleMove()[0] == null){
									JOptionPane.showMessageDialog(checkerboard, "Black Wins!\n");
									frame.setVisible(false);
									if (screen != null)
										screen.setVisible(true);
									try {
										BufferedReader in
										= new BufferedReader(new FileReader("stats.txt"));
										String line1 = in.readLine();
										String line2 = in.readLine();
										int w,l,n;
										int a, b;

										if (cpu)
										{
											a = line2.indexOf(' ');
											b = line2.indexOf(' ',a+1);
											w = Integer.parseInt(line2.substring(0, a).trim());
											l = Integer.parseInt(line2.substring(a,b).trim())+1;
											n = Integer.parseInt(line2.substring(b).trim());
											if (backend.moveCount < n)
												n = backend.moveCount;
											line2 = w + " " + l + " " + n;
										}

										in.close();
										BufferedWriter out;

										out = new BufferedWriter(new FileWriter("stats.txt"));

										out.write(line1);
										out.write("\n");
										out.write(line2);
										out.close();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								

							}
						}else
								{
									{
										if (backend.calcforcedMove() == null)
											mes.setText("Not a valid move!");
										else
											mes.setText("Must jump!");
										setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
									}
								}
					}
					else
					{
						Move[]possible = new Move[25];
						possible = backend.calcPossibleMove();
						Move m = new Move(new coordinate(blacks.get(pieceIndex).getPosY(), blacks.get(pieceIndex).getPosX()), 
										  new coordinate(yCoor, xCoor));
						if(backend.validMove(backend.calcPossibleMove(), m)){
							backend.move(m, null);
							if (backend.getTurn() == -1)
							{	clock.setText("Red turn");
								mes.setText("");
							}
							else
							{	clock.setText("Black turn");
								mes.setText("");
							}
							boardUpdate(cpu);
							backend.b.toString();
							clicked = false;
							if(backend.calcPossibleMove()[0] == null){
								JOptionPane.showMessageDialog(checkerboard, "Black Wins!\n");
								frame.setVisible(false);
								screen.setVisible(true);
							}
						}
						else
						{
							if (backend.calcforcedMove() == null)
								mes.setText("Not a valid move!");
							else
								mes.setText("Must jump!");
							setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						
						}
					}
					clicked = false;
					/*
					//Makes sure you click a white square
					if(!((xCoor % 2 + yCoor % 2)  == 0 || (xCoor % 2 + yCoor % 2) == 2))
					{
						if(red)
							reds.get(pieceIndex).setCoor(xCoor, yCoor);
						else
							blacks.get(pieceIndex).setCoor(xCoor, yCoor);
						clicked = false;
						boardUpdate(cpu);		//Update the moved piece
					}else	//Improper move
						JOptionPane.showMessageDialog(checkerboard, "YOU FAIL!");*/
				}
				System.out.println("X = " + xCoor + " and Y = " + yCoor + " Clicked  " + clicked);
				
			}
			else
				System.out.println("Aint no match");
			mouseIn = null;
		}
		
	}//end listener
	
	//Creates listener for the exit call
	public class ExitListener implements ActionListener 
	{
	    public void actionPerformed(ActionEvent event) 
	    {
	    	//Save the game, then exit
	        System.exit(0);
	    }//end actionPerformed class
	    
	}//end listener
	
	//Creates listener for the details menu item
	public class DetailListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			//Shows the details of the game
			JOptionPane.showMessageDialog(checkerboard, "Details and stuff\n");
		}//end actionPerformed class
		
	}//end listener
	
	//Creates listener for the details menu item
	public class NewGameListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			//Some kinda new game function
		}//end actionPerformed class
	
	}//end listener
	
	//Setup the stuff
	public static void main(String[] args) {
		GameUI gnd = new GameUI(true);
	}
}
