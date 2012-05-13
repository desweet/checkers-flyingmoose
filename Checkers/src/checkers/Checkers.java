package checkers;

import java.util.Scanner;

/**
 * The primary class for playing the game of checkers.
 * 
 *
 */
public class Checkers {
	public Board b;
	private coordinate jumped = null;
	final int BLACK = 1;
	final int RED = -1;
	boolean cpu = false;
	
	private int turn = BLACK;
	/**
	 * Default constructor
	 */
	public Checkers()
	{
		b = new Board();
	}
	public Checkers(Checkers c)
	{
		this.b = new Board(c.b);
		if (jumped != null)
			this.jumped = new coordinate(jumped.getX(),jumped.getY());
		this.turn = c.turn;
	}
	/**
	 * Gets the current turn
	 * @return the turn of the current player
	 */
	public int getTurn()
	{
		return turn;
	}
	/**
	 * Sets the turn to the specified color (-1 for RED, 1 for BLACK)
	 * @param a the color of the turn to change to
	 */
	public void setTurn(int a)
	{
		if (a == RED)
			turn = RED;
		else
			turn = BLACK;
	}
	/**
	 * Determines whether or not the current player has ended their turn
	 * @return the color of the turn for the next move
	 */
	public int determineTurn()
	{
		
		if (jumped != null && calcforcedMove() != null)
		{
			Move[] temp = new Move[4];
			temp = calcforcedMove();
			for(int i = 0; i < 4; i++)
			{
				if (temp[i] == null)
					break;
				if (temp[i].getSrc().equals(jumped))
					return turn;
			}
		}
		else
			turn *= -1;
		return turn;
	}
	/**
	 * Moves the pieces on the board as well as removing any piece that is jumped
	 * @param m the specified move
	 * @param force	a set of moves that must be taken
	 * @return true if the move is valid and executed, or false if the move is invalid and not executed
	 */
	public boolean move(Move m, Move[] force)
	{
		if (m.getSrc().getX() > 7 || m.getSrc().getX()<0
				|| m.getSrc().getY() > 7 || m.getSrc().getY()<0)
			return false;
		if (b.getPiece(m.getSrc()) == null)
			return false;
		
		if (b.getPiece(m.getSrc()).getColor() != turn)
			return false;
		
		if (force != null)
		{
			boolean isaforce = false;
		
		for(int i = 0; i < force.length; i ++)
		{
			if (force[i]==null)
				break;
			if (force[i].equals(m))
				isaforce = true;
		}
		if (!isaforce)
			return false;
		}
		if (b.getPiece(m.getDst()) != null)
			return false;
		if (b.getPiece(m.getSrc()).isKing())
		{
			if ((Math.abs(m.getSrc().getX() - m.getDst().getX()) == 2)
				&& (Math.abs(m.getSrc().getY() - m.getDst().getY()) == 2))
			{
				
				
				int i, j;
				if (m.getSrc().getX() - m.getDst().getX() < 0)
					i = m.getSrc().getX() + 1;
				else
					i = m.getSrc().getX() - 1;
				if (m.getSrc().getY() - m.getDst().getY() < 0)
					j = m.getSrc().getY() + 1;
				else
					j = m.getSrc().getY() - 1;
				if (b.getPiece(new coordinate(i, j)).getColor() == -turn)
					{
						jumped = new coordinate(m.getDst().getX(),m.getDst().getY());
						b.remove(i, j);
						b.move(m);
					}
					
			}
			else if ((Math.abs(m.getSrc().getX() - m.getDst().getX()) != 1)
				|| (Math.abs(m.getSrc().getY() - m.getDst().getY()) != 1))
			{
				return false;
			}
			else
			{
				jumped = null;
				b.move(m);
			}
		}
		else if (b.getPiece(m.getSrc()).getColor() == RED)
		{
			if ((m.getSrc().getX() - m.getDst().getX() == 2)
					&& (Math.abs(m.getSrc().getY() - m.getDst().getY()) == 2))
				{
					
					
					int i, j;
					i = m.getSrc().getX() - 1;
					if (m.getSrc().getY() - m.getDst().getY() < 0)
						j = m.getSrc().getY() + 1;
					else
						j = m.getSrc().getY() - 1;
					if (b.getPiece(new coordinate(i,j)).getColor() == BLACK)
					{
						jumped = new coordinate(m.getDst().getX(),m.getDst().getY());
						b.move(m);
						if (m.getDst().getX() == 0)
							b.kingPiece(m.getDst());
						b.remove(i, j);
					}
					else
						return false;
				}
			
			else if ((m.getSrc().getX() - m.getDst().getX() != 1)
					|| ((Math.abs(m.getSrc().getY() - m.getDst().getY())) != 1)
					|| (turn != RED))
				return false;
			else
			{
				b.move(m);
				jumped = null;
				if (m.getDst().getX() == 0)
					b.kingPiece(m.getDst());
			}
		}
		else if (b.getPiece(m.getSrc()).getColor() == BLACK)
		{
			if ((m.getSrc().getX() - m.getDst().getX() == -2)
					&& (Math.abs(m.getSrc().getY() - m.getDst().getY()) == 2))
			{
				
				
				int i, j;
				i = m.getSrc().getX() + 1;
				if (m.getSrc().getY() - m.getDst().getY() < 0)
					j = m.getSrc().getY() + 1;
				else
					j = m.getSrc().getY() - 1;
				if (b.getPiece(new coordinate(i,j)).getColor() == RED)
				{
					jumped = new coordinate(m.getDst().getX(),m.getDst().getY());
					b.move(m);
					b.remove(i, j);
					if (m.getDst().getX() == 7)
						b.kingPiece(m.getDst());
				}
				else
					return false;
			}
			else if ((Math.abs(m.getSrc().getY() - m.getDst().getY()) != 1)
					|| (m.getSrc().getX() - m.getDst().getX() != -1)
					|| (turn != BLACK))
				return false;
			else
			{
				jumped = null;
				b.move(m);
				if (m.getDst().getX() == 7)
					b.kingPiece(m.getDst());
			}
		}
		determineTurn();
		return true;
		
	}
	/**
	 * Determines all possible moves for the current player based on the current board configuration
	 * @return an array of all possible moves for the current player
	 */
	public Move[] calcPossibleMove()
	{
		Move[] possible = calcforcedMove();
		if (possible != null)
			return possible;
		int posCount = 0;
		possible = new Move[25];
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				if (b.getPiece(new coordinate(i, j)) != null)
				{
					if (b.getPiece(new coordinate(i, j)).getColor() == turn)
					{
						
					if (b.getPiece(new coordinate(i, j)).isKing())
					{
						if (i > 0 && j >0)
							if (b.getPiece(new coordinate(i-1, j-1)) == null)			
								possible[posCount++] = new Move(new coordinate(i,j),new coordinate(i-1,j-1));
							
						if (i < 7 && j > 0)
							if (b.getPiece(new coordinate(i+1, j-1)) == null)
								possible[posCount++] = new Move(new coordinate(i,j),new coordinate(i+1,j-1));
						if (i > 0 && j <7)
							if (b.getPiece(new coordinate(i-1, j+1)) == null)
								possible[posCount++] = new Move(new coordinate(i,j),new coordinate(i-1,j+1));
						
						if (i < 7 && j <7)
							if (b.getPiece(new coordinate(i+1, j+1)) == null)
								possible[posCount++] = new Move(new coordinate(i,j),new coordinate(i+1,j+1));
								

						
						
					}
					else if (turn == RED)
						{
							if (i > 0 && j >0){
								if (b.getPiece(new coordinate(i-1, j-1)) == null)
								{
									possible[posCount++] = new Move(new coordinate(i,j),new coordinate(i-1,j-1));
								}

							}
							//red not king
							//right
							if (i >0 && j <7){
								if (b.getPiece(new coordinate(i-1, j+1)) == null)
								{
									possible[posCount++] = new Move(new coordinate(i,j),new coordinate(i-1,j+1));
								}

							}
						}
						else if (turn == BLACK)
						{
							if (i < 7 && j > 0){
								if (b.getPiece(new coordinate(i+1, j-1)) == null)
								{
									possible[posCount++] = new Move(new coordinate(i,j),new coordinate(i+1,j-1));
								}

							}
							//red not king
							//right
							if (i < 7 && j <7){
								if (b.getPiece(new coordinate(i+1, j+1)) == null)
								{
									possible[posCount++] = new Move(new coordinate(i,j),new coordinate(i+1,j+1));
								}

							}
						}
						
					}
				}
			}
		}

		return possible;
	}
	/**
	 * Generates a list of force jumps where one of which must be taken if any exist
	 * @return the array of Moves of forced jumps
	 */
	public Move[] calcforcedMove()
	{
		Move[] must = new Move[4];
		int count = 0;

		for(int i = 0; i < 8;i++)	
		{
			for(int j = 0; j < 8; j++)
			{
				if (b.getPiece(new coordinate(i,j)) != null)
				{


					if (turn == RED && b.getPiece(new coordinate(i, j)).getColor() == RED)
					{
				
						//left
						if (j > 1 && i > 1)
							if (b.getPiece(new coordinate(i-1, j-1)) !=  null)
							{

							if ((b.getPiece(new coordinate(i-1, j-1)).getColor() == BLACK) &&
									b.getPiece(new coordinate(i-2, j-2))== null)
							{
								must[count++] = new Move(new coordinate(i,j),new coordinate(i-2,j-2));
							}
							}
						//right
						if (j < 6 && i > 1)
							if (b.getPiece(new coordinate(i-1, j+1)) !=  null)
							{


								if ((b.getPiece(new coordinate(i-1, j+1)).getColor() == BLACK) &&
										b.getPiece(new coordinate(i-2, j+2))==  null)
								{
									must[count++] = new Move(new coordinate(i,j),new coordinate(i-2,j+2));
								}
							}
						if (b.kingHere(new coordinate(i,j)))
						{
							if (j > 0 && i < 7)
							{
								if (b.getPiece(new coordinate(i+1, j-1)) !=  null)
								{

									if (j > 1 && i < 6)
										if ((b.getPiece(new coordinate(i+1, j-1)).getColor() == BLACK) &&
												b.getPiece(new coordinate(i+2, j-2))== null)
										{
											must[count++] = new Move(new coordinate(i,j),new coordinate(i+2,j-2));
										}
								}
							}
							//right
							if (j < 6 && i < 6)
								if (b.getPiece(new coordinate(i+1, j+1)) !=  null)
								{

									if ((b.getPiece(new coordinate(i+1, j+1)).getColor() == BLACK) &&
											b.getPiece(new coordinate(i+2, j+2))== null)
									{
										must[count++] = new Move(new coordinate(i,j),new coordinate(i+2,j+2));
									}	
								}
						}
					}
					else if(turn == BLACK && b.getPiece(new coordinate(i, j)).getColor() == BLACK)
					{
						//left
						if (j > 1 && i < 6)
							if (b.getPiece(new coordinate(i+1, j-1)) !=  null)
							{

							if ((b.getPiece(new coordinate(i+1, j-1)).getColor() == RED) &&
									b.getPiece(new coordinate(i+2, j-2))== null)
							{
								must[count++] = new Move(new coordinate(i,j),new coordinate(i+2,j-2));
							}
							}
						//right
						if (j < 6 && i < 6)
							if (b.getPiece(new coordinate(i+1, j+1)) !=  null)
							{

							if ((b.getPiece(new coordinate(i+1, j+1)).getColor() == RED) &&
									b.getPiece(new coordinate(i+2, j+2))== null)
							{
								must[count++] = new Move(new coordinate(i,j),new coordinate(i+2,j+2));
							}	
							}

						if (b.kingHere(new coordinate(i,j)))		
						{
							if (j > 1 && i > 1)
							{	
								if (b.getPiece(new coordinate(i-1, j-1)) !=  null)
								{

									if ((b.getPiece(new coordinate(i-1, j-1)).getColor() == RED) &&
											b.getPiece(new coordinate(i-2, j-2))== null)
									{
										must[count++] = new Move(new coordinate(i,j),new coordinate(i-2,j-2));
									}
								}
							//right
							}
							if (j < 6 && i > 1)
							{
								if (b.getPiece(new coordinate(i-1, j+1)) !=  null)
								{

									if ((b.getPiece(new coordinate(i-1, j+1)).getColor() == RED) &&
											b.getPiece(new coordinate(i-2, j+2))== null)
									{
										must[count++] = new Move(new coordinate(i,j),new coordinate(i-2,j+2));
									}		
								}
							}
						}
					}

				}
			}
		}
		if (count == 0)
			return null;
	
		return must;

	}
	
/*	public int handleInput(Move src, Move dst)
	{
		
	}*/
	
	public boolean validMove(Move [] possible, Move m)
	{
		for(int i = 0; possible[i] != null; i++){
			if(m.equals(possible[i]))
				return true;
		}
		return false;
	}
	
	public static void main(String[] args)
	{
		final int BLACK = 1;
		final int RED = -1;
		Scanner s = new Scanner(System.in);
		Checkers game = new Checkers();
		Move move = null;
		coordinate src;
		coordinate dst;
		Move[] force;
		boolean validmove = false;
		int x, y;
		boolean GameOver = false;
		while(!GameOver)
		{
			game.b.toString();
			System.out.println();
			if (game.getTurn() == RED)
				System.out.println("Red Turn");
			else
			{
				System.out.println("Black Turn");
			}
			
			force = new Move[4];
			int forceCount = 0;
			force = game.calcforcedMove();
			Move[] possible = new Move[25];
			possible = game.calcPossibleMove();
			if (possible[0] == null){
				System.out.println("Game over");
				if (game.turn == RED)
				{
					System.out.println("Black wins");
					GameOver = true;
					break;
				}
				else
				{
					System.out.println("Red Wins");
					GameOver = true;
					break;
				}
			}
			if (force == null && GameOver == false)
			{
			System.out.println("No forced moves");
			System.out.println("Possible Moves");
			
			for(int i = 0; i < 25; i++)
			{
				if (possible[i] == null)
					break;
				System.out.println(possible[i].toString());
			}
			}
			else if (GameOver != true)
			{	for(int i = 0; i < 4; i++)
				{
					if (force[i] == null)
						break;
					System.out.println(force[i].toString());
					forceCount++;
				}
			
			}
			System.out.println();
			while (!validmove && GameOver == false)
			{
				if (game.cpu && game.turn == BLACK)
				{
					move = new AI(game,possible,BLACK).getBestMove();
					validmove = true;
					System.out.println(game.move(move, null));
				}
				else
				{
					System.out.println("Enter src coordinate #down #right");

					x = s.nextInt();
					y = s.nextInt();
					src = new coordinate(x,y);
					System.out.println("Enter dst coordinate #down #right");
					x = s.nextInt();
					y = s.nextInt();
					dst = new coordinate(x,y);
					move = new Move(src,dst);

					for (int i = 0; i < 25; i++)
					{
						if (possible[i] == null)
							break;
						if (move.equals(possible[i]))
						{
							validmove = true;
							game.move(move, null);
							break;
						}
					}
				}
				
			}
		
		
			
			GameOver = false;
			validmove = false;
		}	
	}

}
		
		/*
		
		System.out.println(game.b.move(new Move(new coordinate(2,1),new coordinate(3,2))));
		System.out.println();
		Move[] force = new Move[4];
		force = game.calcforcedMove();
		if (force == null)
		{
			System.out.println("No forced moves");
		}
		else
			for(int i = 0; i < 4; i++)
			{
				System.out.println(force[i].toString());
			}
		System.out.println();
		game.b.toString();
		
		System.out.println(game.b.move(new Move(new coordinate(3,2),new coordinate(4,1))));
		System.out.println();
		force = game.calcforcedMove();
		if (force == null)
		{
			System.out.println("No forced moves");
		}
		else
			for(int i = 0; i < force.length; i++)
			{
				if (force[i] == null)
				{
					break;
				}
				System.out.println(force[i].toString());
			}
		System.out.println();
		game.b.toString();
		/*
		System.out.println(game.b.move(new Move(new coordinate(2,0),new coordinate(3,1))));
		System.out.println();
		force = game.calcforcedMove();
		if (force == null)
		{
			System.out.println("No forced moves");
		}
		else
			for(int i = 0; i < 4; i++)
			{
				System.out.println(force[i].toString());
			}
		System.out.println();
		game.b.toString();*/
		
//	}
	
//}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/*int[][] board = new int[8][8];
	final int BLACK = 1;
	final int RED = -1;
	final int MUST = 2;
	final int FREE = 3;
	
	Checkers()
	{
		for(int i = 0; i < 12; i++)
		{
			if (i/4 == 1)
			{
				board[i / 4][(i*2)%8] = BLACK;
				board[7 - i / 4][(i*2)%8] = RED;
			}
			else
			{
			board[i / 4][(i*2+1)%8] = BLACK;
			board[7 - i / 4][(i*2+1)%8] = RED;
			}
			
		}
	}
	
	//CALC AVAILABLE MOVES
	
	public int valid(int player)
	{
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				if (board[i][j] == player)
					if ((i == 0) || (i == 7)) //top or bottom border
							;
					else if ((j == 0) || (j == 7)) //left or right border
							;
					else 
						{if (board[i+player][j+1] == 0)
						board[i+player][j+1] = FREE;
						if (board[i+player][j-1] == 0)
						board[i+player][j-1] = FREE;
						}
					
						
			}
		}
		return 0;
	}
	public int move(int player, coordinate src, coordinate dst){
		if (board[src.getX()][src.getY()] != player)
			return -1;
		if (board[dst.getX()][dst.getY()] != 0)
			return -2;
		board[src.getX()][src.getY()] = 0;
		board[dst.getX()][dst.getY()] = player;
		return 0;
		
	}
	
	public void DisplayBoard()
	{
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				if (board[i][j] == 0)
					System.out.print("-");
				if (board[i][j] == BLACK)
					System.out.print("B");
				if (board[i][j] == RED)
					System.out.print("R");
				if (board[i][j] == FREE)
					System.out.print("F");
			System.out.print(" ");
			}
		System.out.println("\n");
		}
	}
	
	public static void main(String[] args){
		Checkers game = new Checkers();
	//	game.turn(game.BLACK);
		game.move(game.BLACK, new coordinate(2,1), new coordinate (3,0));
		game.DisplayBoard();
	}
	

}*/
