// replace second plugin in Class-Path variable with this text 
// to use this plugin during  command line execution:
// add FT_lib/AverageFileSize.jar after  

package com.api.spi.avg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import com.api.spi.FileActions;

public class AverageFileSize implements FileActions {

	private long numOfFiles;
	private long totalFileBytes;
	private long fileSizeAvg;
	File outFile;
	FileWriter fWriter;
	PrintWriter pWriter;
	
	public AverageFileSize()
	{
		numOfFiles = 0;
		totalFileBytes = 0;
		fileSizeAvg = 0;
		outFile = new File ("secondary_action.txt"); 
		
		try {
			fWriter = new FileWriter (outFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void performAction(Path file, BasicFileAttributes attrs) {
		// write calculate average file size
		numOfFiles += 1;
		totalFileBytes += attrs.size();
		calculateFileSizeAvg();
		System.out.println(fileSizeAvg);
		
		try {
			fWriter = new FileWriter(outFile);
			pWriter = new PrintWriter(fWriter);
			pWriter.println("Average file size: " + fileSizeAvg + " bytes");
			pWriter.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void calculateFileSizeAvg() {
		fileSizeAvg = totalFileBytes / numOfFiles;		
	}
	
	

}
