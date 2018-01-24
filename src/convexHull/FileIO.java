package convexHull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * @author Sam Malpass <gf009788@live.reading.ac.uk>
 * @version 1.0
 * @since 1.0
 */
public class FileIO {
	/**
	 * Function definition for save()
	 * <p>
	 * Writes a string to a the chosen file
	 * <p>
	 * 
	 * @param s
	 *            is the string to be written
	 * @return true or false depending on whether successful
	 */
	public boolean save(String s) {
		/* Create a JFrame */
		JFrame frame = new JFrame();
		/* Create a JFileChooser */
		JFileChooser saveAs = new JFileChooser();
		/* Set the title */
		saveAs.setDialogTitle("Save As");
		/* Get the users confirmation */
		int Option = saveAs.showSaveDialog(frame);
		/* If the user pressed confirm */
		if (Option == JFileChooser.APPROVE_OPTION) {
			/* Create a file using the input file path */
			File file = saveAs.getSelectedFile();
			/* Write the file path to a string */
			String filePath = file.getPath();
			/* Add the file extension if not already present */
			if (!filePath.toLowerCase().endsWith(".in")) {
				file = new File(filePath + ".in");
			}
			try {
				/* Write the string to the file */
				PrintWriter output = new PrintWriter(file);
				output.println(s);
				/* Close the file */
				output.close();
				/* Return true */
				return true;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				/* Return false */
				return false;
			}
		} else {
			/* Return false */
			return false;
		}
	}

	/**
	 * Function definition for load()
	 * <p>
	 * Creates a coordinate array and loads the points from a user selected file
	 * <p>
	 * 
	 * @return the coordinate array
	 */
	public Coordinate[] load() {
		/* Create Coordinate array */
		Coordinate c[] = null;
		/* Create a JFrame */
		JFrame frame = new JFrame();
		/* Create a JFileChooser */
		JFileChooser loadFrom = new JFileChooser();
		/* Set the title */
		loadFrom.setDialogTitle("Load From");
		/* Get the user choice */
		int option = loadFrom.showOpenDialog(frame);
		/* If user pressed confirm */
		if (option == JFileChooser.APPROVE_OPTION) {
			/* Open the selected file */
			File file = loadFrom.getSelectedFile();
			try (FileReader fileReader = new FileReader(file)) {
				/* Open the and read the file into the coordinate array */
				BufferedReader bufferedFileReader = new BufferedReader(fileReader);
				StringTokenizer stringTokenizer = new StringTokenizer(bufferedFileReader.readLine());
				c = new Coordinate[Integer.parseInt(stringTokenizer.nextToken())];
				for (int I = 0; I < c.length; I++) {
					c[I] = new Coordinate();
					c[I].setXPosition(Integer.parseInt(stringTokenizer.nextToken()));
					c[I].setYPosition(Integer.parseInt(stringTokenizer.nextToken()));
				}
				/* Close the file */
				bufferedFileReader.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		/* Return the array */
		return c;
	}
}
