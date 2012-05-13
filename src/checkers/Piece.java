package checkers;

public class Piece {
	private int color;
	private coordinate position;
	private boolean king;
	
	
	
	public Piece(int color, coordinate position, boolean king) {
		super();
		this.color = color;
		this.position = position;
		this.king = king;
	}
	
	public Piece(Piece p)
	{
		this.color = p.getColor();
		this.position = p.getPosition();
		this.king = p.isKing();
	}
	
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public coordinate getPosition() {
		return position;
	}
	public void setPosition(coordinate position) {
		this.position = position;
	}
	public boolean isKing() {
		return king;
	}
	public void setKing(boolean king) {
		this.king = king;
	}
	
	
	
	
}
