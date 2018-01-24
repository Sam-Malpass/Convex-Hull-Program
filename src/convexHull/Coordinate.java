package convexHull;
/**
 * @author Sam Malpass
 * @version 1.0
 * Source: https://en.wikibooks.org/wiki/Algorithm_Implementation/Geometry/Convex_hull/Monotone_chain
 */
public class Coordinate implements Comparable<Coordinate>
{
	/**
	 * xPosition holds the x-value of the coordinate
	 */
	private int xPosition;
	/**
	 * yPosition holds the y-value of the coordinate
	 */
	private int yPosition;
	/**
	 * Function definition for setXPosition()
	 * <p>
	 * Sets the xPosition to passed value
	 * <p>
	 * @param x is the value to set the xPosition to
	 */
	public void setXPosition(int x)
	{
		this.xPosition = x;
	}
	/**
	 * Function definition for getXPosition()
	 * <p>
	 * Gets the xPosition of object
	 * <p>
	 * @return this.xPosition
	 */
	public int getXPosition()
	{
		return this.xPosition;
	}
	/**
	 * Function definition for setYPosition()
	 * <p>
	 * Sets the yPosition to the passed value
	 * <p>
	 * @param y is the value to set the yPosition to
	 */
	public void setYPosition(int y)
	{
		this.yPosition = y;
	}
	/**
	 * Function definition for getYPosition()
	 * <p>
	 * Gets the yPosition of object
	 * <p>
	 * @return this.yPosition
	 */
	public int getYPosition()
	{
		return this.yPosition;
	}
	/**
	 * Function definition for toString()
	 * <p>
	 * Returns a string of formatted coordinate data
	 */
	public String toString() 
	{
		return "("+this.xPosition + "," + this.yPosition+")";
	}
	/**
	 * Function definition for compareTo()
	 * <p>
	 * Compares a coordinate with another coordinate passed to the function
	 */
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
