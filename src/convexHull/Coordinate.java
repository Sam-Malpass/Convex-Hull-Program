package convexHull;

public class Coordinate implements Comparable<Coordinate>
{
	private int xPosition;
	private int yPosition;
	
	public String toString() 
	{
		return "("+xPosition + "," + yPosition+")";
	}
	
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
