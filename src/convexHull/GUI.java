package convexHull;

import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
}
