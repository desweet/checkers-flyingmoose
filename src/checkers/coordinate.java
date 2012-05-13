package checkers;

public class coordinate {
	private int x;
	private int y;
	
	coordinate ()
	{
		x = 0;
		y = 0;
	}
	coordinate(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	int getX()
	{
		return x;
	}
	int getY()
	{
		return y;
	}
	void set(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	void setX(int x){
		this.x = x;
	}
	void setY(int y){
		this.y =y;
	}
	public String toString()
	{
		return "(" + x + ", " + y + ")";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		coordinate other = (coordinate) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	
}
