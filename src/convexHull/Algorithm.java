package convexHull;

import java.util.Arrays;

public class Algorithm 
{
	public static long cross(Coordinate o, Coordinate a, Coordinate b) 
	{
		return (a.getXPosition() - o.getXPosition()) * (b.getYPosition() - o.getYPosition()) - (a.getYPosition() - o.getYPosition()) * (b.getXPosition() - o.getXPosition());
	}
	public static Coordinate[] convexHull(Coordinate[] c) 
	{
		if (c.length > 1) 
		{
			int numberCoordinates = c.length;
			int k = 0;
			Coordinate[] hullCoordinates = new Coordinate[2 * numberCoordinates];
			Arrays.sort(c);
			for (int ct = 0; ct < numberCoordinates; ++ct) 
			{
				while (k >= 2 && cross(hullCoordinates[k - 2], hullCoordinates[k - 1], c[ct]) <= 0)
				{
					k--;
				}
				hullCoordinates[k++] = c[ct];
			}
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
				hullCoordinates = Arrays.copyOfRange(hullCoordinates, 0, k - 1); 
			}
			return hullCoordinates;
		} 
		else if (c.length <= 1)
		{
			return c;
		} 
		else
		{
			return null;
		}
	}
}
