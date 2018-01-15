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

public class FileIO 
{
	public boolean save(String s)
	{
		JFrame frame = new JFrame();
		JFileChooser saveAs = new JFileChooser();
		saveAs.setDialogTitle("Save As");
		int Option = saveAs.showSaveDialog(frame);
		if (Option == JFileChooser.APPROVE_OPTION) 
		{
			File file = saveAs.getSelectedFile();
			String filePath = file.getPath();
			if(!filePath.toLowerCase().endsWith(".in"))
			{
			    file = new File(filePath + ".in");
			}
			try 
			{	
				PrintWriter output = new PrintWriter(file);
				output.println(s);
				output.close();
				return true;
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	public Coordinate[] load()
	{
		Coordinate c[] = null;
		JFrame frame = new JFrame();
		JFileChooser loadFrom = new JFileChooser();
		loadFrom.setDialogTitle("Load From");
		int option = loadFrom.showOpenDialog(frame);
		if(option == JFileChooser.APPROVE_OPTION)
		{
			File file = loadFrom.getSelectedFile();
			try (FileReader fileReader = new FileReader(file))
			{
				BufferedReader bufferedFileReader = new BufferedReader(fileReader);
				StringTokenizer stringTokenizer = new StringTokenizer(bufferedFileReader.readLine());
				c = new Coordinate[Integer.parseInt(stringTokenizer.nextToken())];
				for(int I = 0; I < c.length; I++)
				{
					c[I] = new Coordinate();
					c[I].setXPosition(Integer.parseInt(stringTokenizer.nextToken()));
					c[I].setYPosition(Integer.parseInt(stringTokenizer.nextToken()));
				}
	            bufferedFileReader.close();
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		return c;
	}
}
