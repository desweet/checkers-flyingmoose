package checkers;

import java.util.ArrayList;

public class MoveList {

	ArrayList<Move> moves = new ArrayList<Move>();
	Checkers curBoard;
	int startTurn;
	int choice = -1;
	
	MoveList(Checkers c)
	{
		this.curBoard = new Checkers(c);
	}
	MoveList(Checkers c, ArrayList<Move> m, Move newest,int choice)
	{
		this.curBoard = new Checkers(c);
		if (m == null)
			moves = new ArrayList<Move>();

		else
			moves = new ArrayList<Move>(m);
		moves.add(newest);
		curBoard.move(newest,null);
		if (this.choice != -2)
			this.choice = choice;
		
	//	this.moves.add(newest);
	}
	/**
	 * Adds a move to the current move list
	 * @param m the move to be added
	 * @return true if the move to be added is not null, else false
	 */
	public boolean addMove(Move m)
	{
		if (m == null)
			return false;
		curBoard.move(m, null);
		moves.add(new Move(m));
		return true;
	}
	
	public String toString()
	{
		String toReturn = "";
		toReturn = "Player choice? " + choice + "\n";
		for(int i = 0; i < moves.size()-1; i++)
		{
			toReturn += moves.get(i).toString() + " -> ";
		}
		toReturn += moves.get(moves.size()-1).toString();
		
	//	toReturn += curBoard.b.toString();
		return toReturn;
	}
	
}
