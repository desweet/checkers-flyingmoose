package checkers;
/**
 * Class that creates a coordinate
 * 
 */
public class coordinate {
	private int x;
	private int y;
	
	/**
	 * Default constructor
	 */
	coordinate()
	{
		x = 0;
		y = 0;
	}
	
	/**
	 * Initialization constructor
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	coordinate(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gets the X coordinate
	 * @return the X coordinate
	 */
	int getX()
	{
		return x;
	}
	
	/**
	 * Gets the Y coordinate
	 * @return the Y coordinate
	 */
	int getY()
	{
		return y;
	}
	
	/**
	 * Set the value of this coordinate to a new coordinate
	 * @param x the x value of the new coordinate
	 * @param y the y value of the new coordinate
	 */
	void set(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Set the X coordinate
	 * @param x the value to be set
	 */
	void setX(int x){
		this.x = x;
	}
	
	/**
	 * Set the Y coordinate
	 * @param y the value to be set
	 */
	void setY(int y){
		this.y =y;
	}
	
	/**
	 * Returns the string representing this coordinate
	 */
	public String toString()
	{
		return "(" + x + ", " + y + ")";
	}
	
	/**
	 * Determines if two coordinates are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		coordinate other = (coordinate) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	
}
