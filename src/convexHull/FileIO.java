package convexHull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FileIO 
{
	public boolean save(String S)
	{
		JFrame Frame = new JFrame();
		JFileChooser SaveAs = new JFileChooser();
		SaveAs.setDialogTitle("Save As");
		int Option = SaveAs.showSaveDialog(Frame);
		if (Option == JFileChooser.APPROVE_OPTION) 
		{
			File File = SaveAs.getSelectedFile();
			String FilePath = File.getPath();
			if(!FilePath.toLowerCase().endsWith(".in"))
			{
			    File = new File(FilePath + ".in");
			}
			try 
			{	
				PrintWriter Output = new PrintWriter(File);
				Output.println(S);
				Output.close();
				return true;
			} 
			catch (FileNotFoundException Exception) 
			{
				Exception.printStackTrace();
				return false;
			}
		}
		else
		{
			return false;
		}
	}
}
