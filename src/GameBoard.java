// code skeleton from http://ashikuzzaman.wordpress.com

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class GameBoard extends JFrame{

	public static final int NUM_ROWS = 8;
	public static final int NUM_COLS = 8;
	
	public static Color gameColor = Color.BLACK;
	
	public static JPanel gameboard = new JPanel();
	
	public GameBoard()
	{
		initComponents();
	}
	
	private void initComponents()
	{
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int boardSize = 0;
		int width = dim.width;
		int height = dim.height;
		if(width >= height)
		{
			boardSize = (int) (height / 1.5);
		}
		else
		{
			boardSize = (int) (width / 1.5) ;
		}
		
		createSquares();
		addPieces();
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Checkers");
		//setIconImage(new ImageIcon("board.jpg").getImage());
		getContentPane().add(gameboard, BorderLayout.CENTER);
		setVisible(true);
		
		Dimension preferredSize = new Dimension();
		preferredSize.width = boardSize;
		preferredSize.height = boardSize;
		setPreferredSize(preferredSize);
		setBounds(boardSize / 4, boardSize/4, boardSize, boardSize);
		pack();
	}
		
	private static void createSquares()
	{
		LayoutManager layout = new GridLayout(NUM_ROWS, NUM_COLS);
		gameboard.setLayout(layout);
		
		for(int i = NUM_ROWS; i > 0; i--)
		{
			for(int j = 1; j <= NUM_COLS; j++)
			{
				GameSquare square;
				if((i + j) % 2 == 0)
				{
					square = new GameSquare(i, j, true);
					square.setBackground(Color.BLACK);
				}
				else
				{
					square = new GameSquare(i, j, false);
					square.setBackground(Color.WHITE);
				}
				
				gameboard.add(square);
			}			
		}
	}
	
	private static void addPieces()
	{
		for(Component square : gameboard.getComponents())
		{
			((GameSquare) square).setPiece(new GamePiece("redPiece.gif"));
		}
				
			
	}
	


	public static void main(String args[])
	{
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() { new GameBoard(); }
		});
	}
		
}