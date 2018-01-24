package convexHull;

import java.util.Arrays;
/**
 * @author Sam Malpass
 * @version 1.0
 * Source: https://en.wikibooks.org/wiki/Algorithm_Implementation/Geometry/Convex_hull/Monotone_chain
 */
public class Algorithm 
{
	/**
	 * Function definition for cross()
	 * <p>
	 * Determines whether the lines between a set of three coordinates make a turn
	 * <p>
	 * @param o is a coordinate
	 * @param a is a coordinate
	 * @param b is a coordinate
	 * @return a long number that determines if the lines are turning
	 */
	public static long cross(Coordinate o, Coordinate a, Coordinate b) 
	{
		return (a.getXPosition() - o.getXPosition()) * (b.getYPosition() - o.getYPosition()) - (a.getYPosition() - o.getYPosition()) * (b.getXPosition() - o.getXPosition());
	}
	/**
	 * Function definition for convexHull()
	 * <p>
	 * Starts by sorting the coordinate array.
	 * Determines the lower and upper hulls, removing the duplicate points.
	 * Then the coordinate array is returned.
	 * <p>
	 * @param c is the array of coordinates to have a hull determined
	 * @return a coordinate array of hull points
	 */
	public static Coordinate[] convexHull(Coordinate[] c) 
	{
		/*If there is more than one point*/
		if (c.length > 1) 
		{
			/*Gets the number of coordinates*/
			int numberCoordinates = c.length;
			/*Sets k to 0*/
			int k = 0;
			/*Creates the hullCoordinates array*/
			Coordinate[] hullCoordinates = new Coordinate[2 * numberCoordinates];
			/*Sorts array c*/
			Arrays.sort(c);
			/*Determines the lower hull*/
			for (int ct = 0; ct < numberCoordinates; ++ct) 
			{
				while (k >= 2 && cross(hullCoordinates[k - 2], hullCoordinates[k - 1], c[ct]) <= 0)
				{
					k--;
				}
				hullCoordinates[k++] = c[ct];
			}
			/*Determines the upper hull*/
			for (int ct = numberCoordinates - 2, T = k + 1; ct >= 0; ct--)
			{
				while (k >= T && cross(hullCoordinates[k - 2], hullCoordinates[k - 1], c[ct]) <= 0)
				{
					k--;
				}
				hullCoordinates[k++] = c[ct];
			}
			if (k > 1)
			{
				/*Remove non-hull vertices, and k-1 which is a duplicate*/
				hullCoordinates = Arrays.copyOfRange(hullCoordinates, 0, k - 1); 
			}
			/*Return the hullCoordinates*/
			return hullCoordinates;
		} 
		/*If the is one coordinate or less*/
		else if (c.length <= 1)
		{
			/*Return the coordinate*/
			return c;
		} 
		/*Otherwise*/
		else
		{
			/*Return null*/
			return null;
		}
	}
}
