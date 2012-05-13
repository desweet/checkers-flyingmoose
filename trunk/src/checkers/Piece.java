package checkers;
/**
 * A class for each piece, contains the color and whether or not it is a king
 */
public class Piece {
	private int color;
	private boolean king;
	
	
	/**
	 * Initialization constructor
	 * @param color	The color for the piece
	 * @param king The king status of the piece
	 */
	public Piece(int color,  boolean king) {
		super();
		this.color = color;
		this.king = king;
	}
	/**
	 * Copy constructor
	 * @param p the piece to copy
	 */
	public Piece(Piece p)
	{
		this.color = p.getColor();
		this.king = p.isKing();
	}
	/**
	 * Gets the color of the piece
	 * @return the color of the piece
	 */
	public int getColor() {
		return color;
	}
	/**
	 * Sets the color of the piece
	 * @param color the color to be set to
	 */
	public void setColor(int color) {
		this.color = color;
	}
/*	public coordinate getPosition() {
		return position;
	}
	public void setPosition(coordinate position) {
		this.position = position;
	}*/
	/**
	 * Is this piece a king?
	 * @return the king status of the piece
	 */
	public boolean isKing() {
		return king;
	}
	/**
	 * Sets the king status of the piece
	 * @param king the value to be set too
	 */
	public void setKing(boolean king) {
		this.king = king;
	}
	
	
	
	
}
