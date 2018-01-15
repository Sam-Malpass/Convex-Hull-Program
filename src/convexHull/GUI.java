package convexHull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
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
	public void drawCoordinatesData(Coordinate[] c)
	{
		if(hullNo < 2)
		{
			rPane.getChildren().clear();
		}
		Label l = new Label(listAll(c));
		rPane.getChildren().add(l);
	}
	public void task1() throws IOException
	{
		hullNo = 1;
		Coordinate[] polygon = new Coordinate[9]; 
		BufferedReader bufferedFileReader = new BufferedReader(new FileReader("Task 1.in")); 	
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedFileReader.readLine());
		c1 = new Coordinate[Integer.parseInt(stringTokenizer.nextToken())];
		for (int ct = 0; ct < c1.length; ct++) 
		{
			c1[ct] = new Coordinate();
			c1[ct].setXPosition(Integer.parseInt(stringTokenizer.nextToken()));
			c1[ct].setYPosition(Integer.parseInt(stringTokenizer.nextToken())); 
		}
		polygon = Arrays.copyOfRange(c1, 2, c1.length);
		bufferedFileReader.close();
		drawCoordinates(c1);
		drawPolygon(polygon);
	}
	public void task2() throws IOException
	{
		Coordinate[] polygon1 = new Coordinate[9]; 
		BufferedReader bufferedFileReader = new BufferedReader(new FileReader("Task 201.in")); 	
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedFileReader.readLine());
		c1 = new Coordinate[Integer.parseInt(stringTokenizer.nextToken())];
		for (int ct = 0; ct < c1.length; ct++) 
		{
			c1[ct] = new Coordinate();
			c1[ct].setXPosition(Integer.parseInt(stringTokenizer.nextToken())); 
			c1[ct].setYPosition(Integer.parseInt(stringTokenizer.nextToken()));
		}
		polygon1 = Arrays.copyOfRange(c1, 2, c1.length);
		bufferedFileReader.close();
		bufferedFileReader = new BufferedReader(new FileReader("Task 202.in"));
		stringTokenizer = new StringTokenizer(bufferedFileReader.readLine());
		Coordinate[] polygon2 = new Coordinate[9]; 
		Coordinate[] polygon3 = new Coordinate[9]; 
		c2 = new Coordinate[Integer.parseInt(stringTokenizer.nextToken())];
		for (int ct = 0; ct < c2.length; ct++) 
		{
			c2[ct] = new Coordinate();
			c2[ct].setXPosition(Integer.parseInt(stringTokenizer.nextToken()));
			c2[ct].setYPosition(Integer.parseInt(stringTokenizer.nextToken())); 
		}
		polygon2 = Arrays.copyOfRange(c2, 2, 12);
		polygon3 = Arrays.copyOfRange(c2, 12, c2.length);
		bufferedFileReader.close();
		drawCoordinates(c1);
		drawCoordinates(c2);
		drawPolygon(polygon1);
		drawPolygon(polygon2);
		drawPolygon(polygon3);
		hullNo = 2;
	}
}
