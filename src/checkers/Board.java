package checkers;
/**
 * A basic board class that is a 2 dimensional array of pieces
 */
public class Board {
	private Piece[][] board;
	
	final int BLACK = 1;
	final int RED = -1;
	/**
	 * Default constructor
	 */
	public Board()
	{
	//	int redCount=0;
	//	int blackCount=0;
	//	red = new Piece[12];
	///	black = new Piece[12];
		board = new Piece[8][8];
		for(int i = 0; i < 12; i++)
		{
			if (i/4 != 1)
			{
			//	board[i / 4][(i*2)%8] = new Piece(BLACK, new coordinate(i / 4,(i*2)%8),false);
				board[i / 4][(i*2+1)%8] = new Piece(BLACK,false);
				board[7 - i / 4][(i*2)%8] = new Piece(RED,false);
			}
			else
			{
				board[i / 4][(i*2)%8] = new Piece(BLACK,false);
			//	board[i / 4][(i*2+1)%8] = new Piece(BLACK, new coordinate(i / 4,(i*2+1)%8),false);
			
				board[7 - i / 4][(i*2+1)%8] = new Piece(RED,false);

			}
			
		}
	}
	public Board(Board b)
	{
		board = new Piece[8][8];
		for(int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				if (b.getPiece(new coordinate(i,j))!= null)
					this.board[i][j] = new Piece(b.getPiece(new coordinate(i,j)));
				else
					this.board[i][j] = null;
			}
		}
	}
	/**
	 * Upgrade a piece to a king
	 * @param c the coordinate of which the piece is going to be upgraded
	 */
	public void kingPiece(coordinate c)
	{
		board[c.getX()][c.getY()].setKing(true);
	}
	/**
	 * Gets the piece at a current coordinate and returns it
	 * @param c the coordinate of the piece you want
	 * @return the Piece object at the specified coordinate or null if no piece is there
	 */
	public Piece getPiece(coordinate c)
	{
		if (board[c.getX()][c.getY()] == null)
			return null;
		return new Piece(board[c.getX()][c.getY()]);
		
	}
	/**
	 * Removes a piece at a specified location
	 * @param i  the number down
	 * @param j  the numbe right
	 * @return true if there was a piece there which is now removed or false if there was no piece there
	 */
	public boolean remove(int i, int j)
	{
		if (board[i][j] == null)
			return false;
		else
			board[i][j] = null;
		return true;
	}
	/**
	 * Moves two pieces on the board
	 * @param m the move attempting to execute
	 * @return true if the move is possible, false if impossible
	 */
	public boolean move(Move m)
	{
		if (board[m.getSrc().getX()][m.getSrc().getY()] == null)
			return false;
		if (board[m.getDst().getX()][m.getDst().getY()] != null)
			return false;
		board[m.getDst().getX()][m.getDst().getY()] = new Piece(board[m.getSrc().getX()][m.getSrc().getY()]);
		board[m.getSrc().getX()][m.getSrc().getY()] = null;
		return true;	
	}
	/**
	 * Re
	 * @param loc
	 * @return
	 */
/*	public boolean redHere(coordinate loc)
	{
		if (board[loc.getX()][loc.getY()].getColor() == RED)
			return true;
		return false;
	}*/
	/**
	 * Determines if the piece is red at this location (i down, j right) 0 based
	 * @param i 0 based coordinate down
	 * @param j 0 based coordinate right
	 * @return true if this piece is RED
	 */
	public boolean redHere(int i, int j)
	{
		if (board[i][j] == null)
			return false;
		if (board[i][j].getColor() == RED)
			return true;
		return false;
	}
	
	public boolean blackHere(coordinate loc)
	{
		if (board[loc.getX()][loc.getY()].getColor() == BLACK)
			return true;
		return false;
	}
	/**
	 * Determines if the piece is king at this location (i down, j right) 0 based
	 * @param i 0 based coordinate down
	 * @param j 0 based coordinate right
	 * @return true if this piece is BLACK
	 */
	public boolean blackHere(int i, int j)
	{
		if (board[i][j] == null)
			return false;
		if (board[i][j].getColor() == BLACK)
			return true;
		return false;
	}
	public boolean kingHere(coordinate loc)
	{
		return board[loc.getX()][loc.getY()].isKing();
	}
	/**
	 * Determines if the piece is king at this location (i down, j right) 0 based
	 * @param i 0 based coordinate down
	 * @param j 0 based coordinate right
	 * @return true if this piece is a king
	 */
	public boolean kingHere(int i, int j)
	{
		return board[i][j].isKing();
	}
	/**
	 * Returns a string of the current board configuration
	 */
	public String toString()
	{
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				
				if (blackHere(i, j))
				{	
					if (kingHere(i,j))
						System.out.print("B");
					else
						System.out.print("b");
				}
				else if (redHere(i,j))
					if (kingHere(i,j))
					System.out.print("R");
					else
						System.out.print("r");
				else
					System.out.print("-");
			System.out.print(" ");
			}
		System.out.println("\n");
		}
		return null;
	}
	
	public static void main(String[] args)
	{
		Board b = new Board();
		b.toString();
		System.out.println(b.move(new Move(new coordinate(2,0),new coordinate(3,1))));
		System.out.println();
		b.toString();
	}
	
}
