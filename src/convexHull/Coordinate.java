package convexHull;

public class Coordinate implements Comparable<Coordinate>
{
	private int xPosition;
	private int yPosition;
	public void setXPosition(int x)
	{
		this.xPosition = x;
	}
	public int getXPosition()
	{
		return this.xPosition;
	}
	public void setYPosition(int y)
	{
		this.yPosition = y;
	}
	public int getYPosition()
	{
		return this.yPosition;
	}
	public String toString() 
	{
		return "("+this.xPosition + "," + this.yPosition+")";
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
