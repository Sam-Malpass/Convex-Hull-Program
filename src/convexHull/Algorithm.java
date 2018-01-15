package convexHull;

public class Algorithm 
{
	public static long cross(Coordinate o, Coordinate a, Coordinate b) 
	{
		return (a.getXPosition() - o.getXPosition()) * (b.getYPosition() - o.getYPosition()) - (a.getYPosition() - o.getYPosition()) * (b.getXPosition() - o.getXPosition());
	}
}
