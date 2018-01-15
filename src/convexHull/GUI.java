package convexHull;

import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class GUI extends Application
{
	private int cSize = 512;
	public int hullNo;
	private GraphicsContext gContext; 
	private VBox rPane;
	private HBox lPane;
	private Coordinate[] c1, c2, hull1, hull2;
	private void alertWindow(String titleStr, String contentStr) 
	{
	    Alert alert = new Alert(AlertType.INFORMATION);
	    alert.setTitle(titleStr);
	    alert.setHeaderText(null);
	    alert.setContentText(contentStr);
	    alert.showAndWait();
	}
	public void drawCoordinates(Coordinate[] c)
	{
		gContext.setFill(Color.WHITE);
		gContext.fillRect(0, 0, cSize, cSize);
		for(int ct = 0; ct < c.length; ct++)
		{
			gContext.setFill(Color.BLACK);
			gContext.fillArc(c[ct].getXPosition()-2, c[ct].getYPosition()-2, 2, 2, 0, 360, ArcType.ROUND);
		}
	}
	public void drawHull(Coordinate[] h)
	{
		gContext.setStroke(Color.GREEN);
		gContext.setLineWidth(2);
		for(int ct = 1; ct < h.length; ct++)
		{
			gContext.strokeLine(h[ct].getXPosition(), h[ct].getYPosition(), h[ct-1].getXPosition(), h[ct-1].getYPosition());
		}
		gContext.strokeLine(h[h.length-1].getXPosition(), h[h.length-1].getYPosition(), h[0].getXPosition(), h[0].getYPosition());
	}
	public void drawPolygon(Coordinate[] p)
	{
		gContext.setStroke(Color.BLACK);
		gContext.setLineWidth(2);
		for(int ct = 0; ct < p.length-1; ct++)
		{
			gContext.strokeLine(p[ct].getXPosition(), p[ct].getYPosition(), p[ct+1].getXPosition(), p[ct+1].getYPosition());
		}
		gContext.strokeLine(p[p.length-1].getXPosition(), p[p.length-1].getYPosition(), p[0].getXPosition(), p[0].getYPosition());
	}
	public String listAll(Coordinate[] c)
	{
		String s = "";
		s = s + "Hull Points: \n";
		for(int ct = 0; ct < c.length; ct++)
		{
			s = s + c[ct].toString() + "\n";
		}
		return s;
	}
}
