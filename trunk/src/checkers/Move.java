package checkers;

public class Move {
	private coordinate src;
	private coordinate dst;
	
	
	public Move(coordinate src, coordinate dst) {
		super();
		this.src = src;
		this.dst = dst;
	}
	
	public coordinate getSrc() {
		return src;
	}
	public void setSrc(coordinate src) {
		this.src = src;
	}
	public coordinate getDst() {
		return dst;
	}
	public void setDst(coordinate dst) {
		this.dst = dst;
	}
	
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
