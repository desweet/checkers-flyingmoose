package checkers;

import java.util.ArrayList;
import java.util.Random;

public class AI {

	Checkers theGame;
	Move[] possible;
	int color;
	Move bestMove;
	int bestValue;
	
	public class MoveNode{
		Move m;
		MoveNode one;
		MoveNode two;
		MoveNode three;
		MoveNode parent;
		Checkers c;
		MoveNode(Move m, Checkers c,MoveNode Parent)
		{
			this.m = new Move(m);
			this.c = new Checkers(c);
			this.c.move(m, null);
			this.parent = Parent;
		}
	}
	
	public AI(Checkers c, Move[] p, int col)
	{
		theGame = new Checkers(c);
		possible = p;
		color = col;
	}
	/**
	 *  This will attempt to calculate a score based on the current board configuration, a higher score represents a better
	 *  configuration for the player passed in
	 * @param b  : the board to be checked
	 * @param color : scoring for this player
	 * @return the score of the board based on pieces and locations
	 */
	public int Score(Board b, int color)
	{
		int score = 0;
		Random generator = new Random();
	
		for(int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				if (b.getPiece(new coordinate(i,j)) != null && b.getPiece(new coordinate(i,j)).getColor() == color )
				{
					score += 10;
					if (color == b.BLACK && i == 0)
						score += 2;
					if (color == b.RED && i == 7)
						score += 2;
					
					if (j == 0 || j == 7)
						score += generator.nextInt() % 2;
					if (b.getPiece(new coordinate(i,j)).isKing())
						score += 11;
				}
			}
		}
		return score;
	}
	/**
	 * This will factor in the number of available moves that a player has, a high value represents a good score
	 *  for black while a low value represents a good score for red
	 * @param b the board to be checked
	 * @return the total score of the board
	 */
	public int boardScore(Board b)
	{
		boolean redJump = false;
		Checkers temp = new Checkers(b,1);
		temp.setTurn(-1);
		int redMoves = 0;
		if (temp.calcforcedMove() != null)
			redJump = true;
		while(temp.calcPossibleMove()[redMoves] != null)
			redMoves++;
		temp.setTurn(1);
		int blackMoves = 0;
		if (temp.calcforcedMove() != null)
			blackMoves = redMoves-1;
		else
			while(temp.calcPossibleMove()[blackMoves] != null)
				blackMoves++;
		if (redJump)
			blackMoves = redMoves - 1;
		int base = (blackMoves - redMoves) * 3;
		int bScore = Score(b,b.BLACK);
		int rScore = Score(b,b.RED);
		return (base + bScore - rScore);
	}
	
/*	public Move getBestMove()
	{
		int counter = 0;
		for(int i = 0; possible[i] != null; i++)
			counter++;
		int MaxScore = Integer.MIN_VALUE;
		int maxIndex = 0;
		for(int i = 0; i < counter; i++)
		{
			Checkers temp = new Checkers(theGame);
			theGame.move(possible[i],null);
			if (boardScore(theGame.b) > MaxScore)
			{
				MaxScore = boardScore(theGame.b);
				maxIndex = i;
			}
		}
			/*	if (temp.getTurn() == theGame.getTurn())
			{
				for(int j = 0; j < temp.calcPossibleMove().length; j++)
				{
					possible[counter] = new Move(temp.calcPossibleMove()[j].getSrc(),temp.calcPossibleMove()[j].getDst());
				}
				
			}
			
		}
		//return null;
		
		System.out.println(MaxScore);
		return possible[maxIndex];
		Random generator = new Random();
		int r = Math.abs(generator.nextInt() % counter);
		theGame.move(possible[r],null);
		System.out.println(boardScore(theGame.b));
		return possible[r];
		
	}*/
	public boolean dupilicateMove(Move[]one, Move[] two)
	{
		return true;
	}
	/**
	 * This function will calculate all of the possible playouts of a passed 
	 * move and return the score of the most likely result of this move taking into account all possible forcejump scenarios.
	 * @param c : an instance of a game of Checkers
	 * @param m : a Move
	 * @param turn : the current player turn of the move
	 * @return the most likely scenario score
	 */
	public int getLikely (Checkers c, Move m,int turn)
	{
		ArrayList<MoveList> toReturn = new ArrayList<MoveList>();
		toReturn.add(new MoveList(new Checkers(c),null,m,-1));
		//ArrayList<Move> each = new ArrayList<Move>();
		//each.add(m);
	//	toReturn.add(new MoveList(c,null,m));
		
		Checkers iter = new Checkers(c);
		int index = 0;
		boolean allNull = false;
		System.out.println(toReturn.get(0).toString());
		int loop = 0;
		int turns = 0;
		while (!allNull)
		{	loop = toReturn.size();
			allNull = true;
			turns++;
			for(int i = 0; i < loop; i++)
			{
				
				if (toReturn.get(i).curBoard.calcforcedMove() != null)
				{
				//	loop = toReturn.get(i).curBoard.calcforcedMove().length;
					for(int j = 0; toReturn.get(i).curBoard.calcforcedMove()[j] != null; j++)
					{
						if (toReturn.get(i).curBoard.getTurn() == turn)
						{
							toReturn.add(new MoveList(new Checkers(toReturn.get(i).curBoard)
									,toReturn.get(i).moves,new Move(toReturn.get(i).curBoard.calcforcedMove()[j]),toReturn.get(i).choice));
						}
						else
						{
							toReturn.add(new MoveList(new Checkers(toReturn.get(i).curBoard)
							,toReturn.get(i).moves,new Move(toReturn.get(i).curBoard.calcforcedMove()[j]),toReturn.get(i).choice));
						}
						System.out.println(toReturn.get(toReturn.size()-1).toString());
						if (j > 0 && toReturn.get(i).curBoard.getTurn() == turn && toReturn.get(i).choice != -2)
						{
							toReturn.get(toReturn.size()-1).choice = turns;
							toReturn.get(toReturn.size()-2).choice = turns;
							
						}
						if (j > 0 && toReturn.get(i).curBoard.getTurn() != turn)
						{
							toReturn.get(toReturn.size()-1).choice = -2;
							toReturn.get(toReturn.size()-2).choice = -2;
							
						}
						
					}
					toReturn.remove(i);
					allNull = false;
					//System.out.println(toReturn.get(i).toString());
				}
			}
		}
		
		int minScore = Integer.MAX_VALUE;
		int move = 0;
		int bestScore = Integer.MIN_VALUE;
		int move2 = 0;
		for(int i = 0; i < toReturn.size();i++)
		{
			if (toReturn.get(i).choice > -1)
			{
				if (boardScore(toReturn.get(i).curBoard.b) > bestScore)
				{
					bestScore = boardScore(toReturn.get(i).curBoard.b);
					move2 = i;
				}
			}
			if (boardScore(toReturn.get(i).curBoard.b) < minScore)
			{
				minScore  =boardScore(toReturn.get(i).curBoard.b);
				move = i;
			}
		}
	
		if (bestScore != Integer.MIN_VALUE)
		{
	//		System.out.println(toReturn.get(move2).toString());
	//		System.out.println(toReturn.get(move2).curBoard.b.toString());
			return bestScore;
		}
	//	System.out.println(toReturn.get(move).toString());
	//	System.out.println(toReturn.get(move).curBoard.b.toString());
		return minScore;
	//	System.out.println(toReturn.get(move).toString());
	//	System.out.println(toReturn.get(move).curBoard.b.toString());
	//	return toReturn.get(move).moves.get(0);
	}
	public static void main(String[] args)
	{
		Checkers test = new Checkers("-b-b-b-----------b---b-b-----------r------b---r--r------r-r---r-|b|25");
		System.out.println(test.b.toString());
		AI move = new AI(test,test.calcPossibleMove(),test.getTurn());
		//AI.MoveNode root = move.new MoveNode(test.calcPossibleMove()[0],new Checkers(test),null);
		int temp;
		int max = Integer.MIN_VALUE;
		int num = 0;
		
		for(int i = 0; test.calcPossibleMove()[i] != null; i++)
		{	
			Checkers iter = new Checkers(test);
			System.out.println("___");
			temp = move.getLikely(new Checkers(test),new Move(test.calcPossibleMove()[i]),iter.getTurn());
			iter.move(test.calcPossibleMove()[i], null);
			
			System.out.println("Move: " + test.calcPossibleMove()[i].toString() + " score: " + temp);
			if (temp > max)
			{
				max = temp;
				num = i;
			}
		}
		System.out.println("Best Move: " + test.calcPossibleMove()[num].toString() + " score: " + max);
	}
	
	
}
