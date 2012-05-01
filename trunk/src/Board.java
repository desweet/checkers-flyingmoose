package checkers;

public class Board {
//	private Piece[] red;
//	private Piece[] black;
	private Piece[][] board;
	
	final int BLACK = 1;
	final int RED = -1;
	
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
				board[i / 4][(i*2+1)%8] = new Piece(BLACK, new coordinate(i / 4,(i*2+1)%8),false);
				board[7 - i / 4][(i*2)%8] = new Piece(RED,new coordinate(7 - i / 4,(i*2)%8),false);
			}
			else
			{
				board[i / 4][(i*2)%8] = new Piece(BLACK, new coordinate(i / 4,(i*2)%8),false);
			//	board[i / 4][(i*2+1)%8] = new Piece(BLACK, new coordinate(i / 4,(i*2+1)%8),false);
			
				board[7 - i / 4][(i*2+1)%8] = new Piece(RED,new coordinate(7 - i / 4,(i*2+1)%8),false);

			}
			
		}
	}
	public void kingPiece(coordinate c)
	{
		board[c.getX()][c.getY()].setKing(true);
	}
	public Piece getPiece(coordinate c)
	{
		if (board[c.getX()][c.getY()] == null)
			return null;
		return new Piece(board[c.getX()][c.getY()]);
		
	}
	public boolean remove(int i, int j)
	{
		if (board[i][j] == null)
			return false;
		else
			board[i][j] = null;
		return true;
	}
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
	public boolean redHere(coordinate loc)
	{
		if (board[loc.getX()][loc.getY()].getColor() == RED)
			return true;
		return false;
	}
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
	public boolean kingHere(int i, int j)
	{
		return board[i][j].isKing();
	}
	
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
