package checkers;
/**
 * A class for checkers consisting of two coordinates, src and dst
 *
 */
public class Move {
	private coordinate src;
	private coordinate dst;
	
	/**
	 * Initialization constructor
	 * @param src source coordinate
	 * @param dst destination coordinate
	 */
	public Move(coordinate src, coordinate dst) {
		super();
		this.src = src;
		this.dst = dst;
	}
	/**
	 * Gets the source coordinate
	 * @return the source coordinate
	 */
	public coordinate getSrc() {
		return src;
	}
	/**
	 * Sets the source coordinate
	 * @param src the coordinate to be set
	 */
	public void setSrc(coordinate src) {
		this.src = src;
	}
	/**
	 * Gets the destination coordinate
	 * @return the coordinate of the destination
	 */
	public coordinate getDst() {
		return dst;
	}
	/**
	 * Sets the destination coordinate
	 * @param dst the coordinate to be set
	 */
	public void setDst(coordinate dst) {
		this.dst = dst;
	}
	/**
	 * Returns the string representing this class
	 */
	public String toString()
	{
		return src.toString() + " to " + dst.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dst == null) ? 0 : dst.hashCode());
		result = prime * result + ((src == null) ? 0 : src.hashCode());
		return result;
	}
	/**
	 * Determins if two Moves are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Move other = (Move) obj;
		if (dst == null) {
			if (other.dst != null)
				return false;
		} else if (!dst.equals(other.dst))
			return false;
		if (src == null) {
			if (other.src != null)
				return false;
		} else if (!src.equals(other.src))
			return false;
		return true;
	}
	
}
