package convexHull;

public class Coordinate implements Comparable<Coordinate>
{
	public int xPosition;
	public int yPosition;
	
	public int compareTo(Coordinate c) 
	{
		if (this.xPosition == c.xPosition) 
		{
			return this.yPosition - c.yPosition;
		} 
		else 
		{
			return this.xPosition - c.xPosition;
		}
	}
	
	
}
