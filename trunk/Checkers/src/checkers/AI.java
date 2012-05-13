package checkers;

import java.util.Random;

public class AI {

	Checkers theGame;
	Move[] possible;
	int color;
	Move bestMove;
	int bestValue;
	
	public AI(Checkers c, Move[] p, int col)
	{
		theGame = new Checkers(c);
		possible = p;
		color = col;
	}
	
	public int Score(Board b, int color)
	{
		int score = 0;
		for(int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				if (b.getPiece(new coordinate(i,j)) != null && b.getPiece(new coordinate(i,j)).getColor() == color )
				{
					score += 4;
					if (color == b.BLACK && i == 0)
						score += 2;
					if (color == b.RED && i == 7)
						score += 2;
					
					if (j == 0 || j == 7)
						score += 1;
					if (b.getPiece(new coordinate(i,j)).isKing())
						score += 5;
				}
			}
		}
		return score;
	}
	public int boardScore(Board b)
	{
		return (Score(b,b.BLACK) - Score(b,b.RED));
	}
	public Move getBestMove()
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
		*/
		System.out.println(MaxScore);
		return possible[maxIndex];
	/*	Random generator = new Random();
		int r = Math.abs(generator.nextInt() % counter);
		theGame.move(possible[r],null);
		System.out.println(boardScore(theGame.b));
		return possible[r];
		*/
	}
	
	
}
