package convexHull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
/**
 * @author      Sam Malpass <gf009788@live.reading.ac.uk>
 * @version     1.0
 * @since       1.0        
 */
public class GUI extends Application
{
	/**
	 * cSize is the size of the canvas
	 */
	private int cSize = 512;
	/**
	 * hullNo is the number hulls being drawn
	 */
	public int hullNo;
	/**
	 * gContext is the GraphicsContext of the application
	 */
	private GraphicsContext gContext; 
	/**
	 * rPane is a VBox used to output the hull coordinates
	 */
	private VBox rPane;
	/**
	 * lPane is a HBox used to hold the buttons
	 */
	private HBox lPane;
	/**
	 * These are arrays of coordinates with c1 and c2 being all coordinates
	 * and hull1 and hull2 being hull coordinates for the respective c1 and c2
	 * coordinate arrays
	 */
	private Coordinate[] c1, c2, hull1, hull2;
	/**
	 * Function definition for AlertWindow()
	 * <p>
	 * Creates a new Alert of type INFORMATION before setting the title
	 * of said alert to titleStr and the content to contentStr. The Alert is
	 * then shown.
	 * <p>
	 * @param titleStr is the string used for the title of the window
	 * @param contentStr is the string used for the content of the window
	 */
	private void alertWindow(String titleStr, String contentStr) 
	{
		/*Creates object of type Alert*/
	    Alert alert = new Alert(AlertType.INFORMATION);
	    /*Sets alert title to titleStr*/
	    alert.setTitle(titleStr);
	    /*Sets alert header to null*/
	    alert.setHeaderText(null);
	    /*Sets alert content to contentStr*/
	    alert.setContentText(contentStr);
	    /*Show the alert and wait*/
	    alert.showAndWait();
	}
	/**
	 * Function definition for drawCoordinates()
	 * <p>
	 * Fills the canvas with a white rectangle to act as the background then draws 
	 * a point on the canvas for all the coordinates passed to this function
	 * <p>
	 * @param c is an array of type Coordinate
	 */
	public void drawCoordinates(Coordinate[] c)
	{
		/*Sets the fill colour of the GraphicsContext*/
		gContext.setFill(Color.WHITE);
		/*Draws a filled rectangle on the canvas*/
		gContext.fillRect(0, 0, cSize, cSize);
		/*For all points in c*/
		for(int ct = 0; ct < c.length; ct++)
		{
			/*Sets the fill colour of the GraphicsContext*/
			gContext.setFill(Color.BLACK);
			/*Draw a filled circle on the canvas*/
			gContext.fillArc(c[ct].getXPosition()-2, c[ct].getYPosition()-2, 2, 2, 0, 360, ArcType.ROUND);
		}
	}
	/**
	 * Function definition for drawHull()
	 * <p>
	 * Draws green lines between all the points in the passed array
	 * <p>
	 * @param h is an array of type Coordinate - should be hull specific
	 */
	public void drawHull(Coordinate[] h)
	{
		/*Sets the stroke colour of the GraphicsContext*/
		gContext.setStroke(Color.GREEN);
		/*Sets the line width of the GraphicsContext*/
		gContext.setLineWidth(2);
		/*For all coordinates in h*/
		for(int ct = 1; ct < h.length; ct++)
		{
			/*Draws a line between two points in the array h*/
			gContext.strokeLine(h[ct].getXPosition(), h[ct].getYPosition(), h[ct-1].getXPosition(), h[ct-1].getYPosition());
		}
		/*Draws a line between the end of the array and the start*/
		gContext.strokeLine(h[h.length-1].getXPosition(), h[h.length-1].getYPosition(), h[0].getXPosition(), h[0].getYPosition());
	}
	/**
	 * Function definition for drawPolygon()
	 * <p>
	 * Draws black lines between all the points in the passed array
	 * <p>
	 * @param p is an array of type Coordinate - should be polygon specific
	 */
	public void drawPolygon(Coordinate[] p)
	{
		/*Sets the stroke colour of the GraphicsContext*/
		gContext.setStroke(Color.BLACK);
		/*Sets the line width of the GraphicsContext*/
		gContext.setLineWidth(2);
		/*For all points in the array p*/
		for(int ct = 0; ct < p.length-1; ct++)
		{
			/*Draws a line between two points in the array p*/
			gContext.strokeLine(p[ct].getXPosition(), p[ct].getYPosition(), p[ct+1].getXPosition(), p[ct+1].getYPosition());
		}
		/*Draws a line between the first and last points in the array p*/
		gContext.strokeLine(p[p.length-1].getXPosition(), p[p.length-1].getYPosition(), p[0].getXPosition(), p[0].getYPosition());
	}
	/**
	 * Function definition for listAll()
	 * <p>
	 * Creates a string and adds the passed coordinates to that string
	 * before returning said string
	 * <p>
	 * @param c is an array of type Coordinate
	 * @return s
	 */
	public String listAll(Coordinate[] c)
	{
		/*Create an empty string*/
		String s = "";
		/*Add the title to the string*/
		s = s + "Hull Points: \n";
		/*For all points in array c*/
		for(int ct = 0; ct < c.length; ct++)
		{
			/*Add the coordinates to string*/
			s = s + c[ct].toString() + "\n";
		}
		/*Return string s*/
		return s;
	}
	/**
	 * Function definition for drawCoordinatesData
	 * <p>
	 * Determines how many hulls are being drawn and then outputs
	 * the coordinate data for the hull(s) to the rPane
	 * <p>
	 * @param c is an array of type Coordinate
	 */
	public void drawCoordinatesData(Coordinate[] c)
	{
		/*If two hulls are open*/
		if(hullNo < 2)
		{
			/*Clear the pane*/
			rPane.getChildren().clear();
		}
		/*Create a Label object using the coordinates of c*/
		Label l = new Label(listAll(c));
		/*Add the label to rPane*/
		rPane.getChildren().add(l);
	}
	/**
	 * Function definition for task1()
	 * <p>
	 * Sets up the Task 1 data for the coursework assignment in CS2AO17
	 * <p>
	 * @throws IOException
	 */
	public void task1() throws IOException
	{
		/*Sets hullNo to 1*/
		hullNo = 1;
		/*Creates a Coordinate array for the polygon*/
		Coordinate[] polygon = new Coordinate[9]; 
		/*Opens the coursework data file and reads it in*/
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
		/*Draw the Coordinates*/
		drawCoordinates(c1);
		/*Draw the polygon*/
		drawPolygon(polygon);
	}
	/**
	 * Function definition for task2()
	 * <p>
	 * Sets up the Task 2 data for the coursework assignment in CS2AO17
	 * <p>
	 * @throws IOException
	 */
	public void task2() throws IOException
	{
		/*Creates a Coordinate array for a polygon*/
		Coordinate[] polygon1 = new Coordinate[9]; 
		/*Opens and reads in the data for the first set of Coordinates*/
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
		/*Opens and reads in the data for the second set of Coordinates*/
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
		/*Draw the two sets of Coordinates*/
		drawCoordinates(c1);
		drawCoordinates(c2);
		/*Draw all the polygons*/
		drawPolygon(polygon1);
		drawPolygon(polygon2);
		drawPolygon(polygon3);
		/*Set hullNo to 2*/
		hullNo = 2;
	}
	/**
	 * Function definition for setMenu()
	 * <p>
	 * Creates all the Menu functions for the MenuBar and then returns the
	 * created MenuBar
	 * <p>
	 * @return menuBar
	 */
	MenuBar setMenu() 
	{
		/*Create a MenuBar object*/
		MenuBar menuBar = new MenuBar();
		/*Create a Menu object*/
		Menu mFile = new Menu("File");
		/*Create and define actions for three MenuItems*/
		MenuItem mSave = new MenuItem("Save");
		MenuItem mLoad = new MenuItem("Load");
		MenuItem mExit = new MenuItem("Exit");
		mSave.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent actionEvent) 
		    {
		    	/*Creates FileIO object*/
		        FileIO File = new FileIO();
		        /*Create a string*/
		        String Write = new String();
		        /*If there are no coordinates*/
		        if(c1 == null)
		        {
		        	/*Return an error message*/
		        	alertWindow("Error", "No co-ordinates are open!");
		        }
		        /*Otherwise*/
		        else
		        {
		        	/*Add the length of the coordinate array to the string*/
		        	Write = Write + c1.length;
		        	/*For all the coordinates in the array*/
		        	for(int ct = 0; ct < c1.length; ct++)
		        	{
		        		/*Add their x and y positions to the file*/
		        		Write = Write + " " + c1[ct].getXPosition() + " " + c1[ct].getYPosition();
		        	}
		        	/*If the file saves successfully*/
		        	if(File.save(Write) == true)
		        	{
		        		/*Return success message*/
		        		alertWindow("Success", "Co-ordinates saved successfully!");
		        	}
		        	/*Otherwise*/
		        	else
		        	{
		        		/*Return an error message*/
		        		alertWindow("Error", "Co-ordinates failed to save!");
		        	}
		        }
		    }
		});
		mLoad.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent actionEvent) 
		    {
		    	/*Create FileIO object*/
		    	FileIO File = new FileIO();
		    	/*Load the file into a coordinate array*/
		        c1 = File.load();
		        /*If the coordinate array is not null*/
		        if(c1 != null)
		        {
		        	/*Return a success message*/
		        	alertWindow("Success", "Co-ordinates loaded successfully!");
		        	/*Set hullNo to 1*/
		        	hullNo = 1;
		        	/*Draw the coordinates*/
		        	drawCoordinates(c1);
		        }
		        /*Otherwise*/
		        else
		        {
		        	/*Return an error message*/
		        	alertWindow("Error", "Co-ordinates failed to load!");
		        }
		    }
		});
		mExit.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent actionEvent) 
		    {
		    	/*Exit the system*/
		        System.exit(0);						
		    }
		});
		/*Add MenuItems to mFile*/
		mFile.getItems().addAll(mSave, mLoad, mExit);
		/*Create new Menu*/
		Menu mCoursework = new Menu("Coursework");
		/*Create new MenuItems and define their actions*/
		MenuItem mTask1 = new MenuItem("Task 1");
		MenuItem mTask2 = new MenuItem("Task 2");
		mTask1.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent actionEvent) 
		    {
		    	try {
		    		/*Calls task1 function*/
					task1();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		});
		mTask2.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent actionEvent) 
		    {
		    	try {
		    		/*Calls task2 function*/
					task2();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		});
		/*Add MenuItems to mCoursework*/
		mCoursework.getItems().addAll(mTask1, mTask2);
		/*Create new Menu*/
		Menu mHelp = new Menu("Help");
		/*Create new MenuItems and define their actions*/
		MenuItem mInfo = new MenuItem("Information");
		MenuItem mAbout = new MenuItem("About");
		mInfo.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent actionEvent) 
		    {
		    	/*Send out alert with information*/
		        alertWindow("Information", "This is the Convex Hull program, please select a coursework task, or input your own file.");					
		    }
		});
		mAbout.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent actionEvent) 
		    {
		    	/*Send out alert with about*/
		        alertWindow("About", "Created by Samuel John Malpass, Student Number 24009788 for CS2AO17");					
		    }
		});
		/*Add MenuItems to mHelp*/
		mHelp.getItems().addAll(mInfo, mAbout);
		/*Add Menus to menuBar*/
		menuBar.getMenus().addAll(mFile, mCoursework, mHelp);
		/*Return menuBar*/
		return menuBar;					
	}
	/**
	 * Function definition for createButtons()
	 * <p>
	 * Creates buttons, defines their actions and puts them into the passed HBox
	 * <p>
	 * @param lPane is a HBox that will hold the buttons
	 */
	public void createButtons(HBox lPane)
	{
		/*Create Button object and define its actions*/
		Button solveButton = new Button("Show Hull Points");
		solveButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			public void handle(ActionEvent event) 
			{
				/*Create a coordinate array for a hull*/
				hull1 = Algorithm.convexHull(c1);
				/*Draw the coordinates of the hull*/
				drawCoordinatesData(hull1);
				/*If the hullNo is greater than 1*/
				if(hullNo > 1)
				{
					/*Create a coordinate array for a hull*/
					hull2 = Algorithm.convexHull(c2);
					/*Draw the coordinates of the hull*/
					drawCoordinatesData(hull2);
				}
			}
		});
		/*Create Button object and define its actions*/
		Button drawButton = new Button("Draw Hull");
		drawButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			public void handle(ActionEvent event) 
			{
				/*Create a coordinate array for a hull*/
				hull1 = Algorithm.convexHull(c1);
				/*Draw the hull*/
				drawHull(hull1);
				/*If the hullNo is greater than 1*/
				if(hullNo > 1)
				{
					/*Create a coordinate array for a hull*/
					hull2 = Algorithm.convexHull(c2);
					/*Draw the hull*/
					drawHull(hull2);
				}
			}
		});
		/*Add the buttons to the HBox*/
		lPane.getChildren().setAll(solveButton, drawButton);
	}
	/**
	 * Function definition for start()
	 * <p>
	 * Creates the base window for the application
	 */
	public void start(Stage mainStage) throws Exception 
	{
		/*Sets the mainStage's title*/
	 	mainStage.setTitle("Convex Hull");
	 	/*Create a BorderPane*/
	    BorderPane bPane = new BorderPane();	
	    /*Sets the menuBar at the top of the BorderPane*/
		bPane.setTop(setMenu());
		/*Create a new Group*/
	    Group base = new Group();
	    /*Create a new Canvas*/
	    Canvas backdrop = new Canvas( cSize, cSize );	
	    /*Add the Canvas to the Group*/
	    base.getChildren().add( backdrop );
	    /*Set the GraphicsContext*/
	    gContext = backdrop.getGraphicsContext2D();
	    /*Centre the Canvas in the window*/
	    bPane.setCenter(base);
	    /*Create a new VBox*/
	    rPane = new VBox();
	    /*Set the VBox to the right of the window*/
	    bPane.setRight(rPane);
	    /*Create a new HBox*/
	    lPane = new HBox();
	    /*Call createButtons and pass lPane*/
	    createButtons(lPane);
	    /*Set the lPane to the bottom of the window*/
	    bPane.setBottom(lPane);
	    /*Create a new Scene using the BorderPane*/
	    Scene mainScene = new Scene(bPane, cSize+100, cSize+49); 			
	    /*Set the mainStage's Scene to mainScene*/
	    mainStage.setScene(mainScene);
	    /*Show the window*/
	    mainStage.show();
	}
	/**
	 * Function definition for main()
	 * <p>
	 * Launches the application
	 * <p>
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Application.launch(args);
	}
}
