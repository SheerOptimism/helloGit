// Default behaviour is referenced as FT_lib/DefaultBehavior.jar
// in classpath variable of MANIFEST.MF file 

package com.api.spi.def;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

import com.api.spi.FileActions;

public class DefaultBehavior implements FileActions {
	
	File outFile;
	FileWriter fWriter;
	PrintWriter pWriter;
	String fileInformation;
	// WRITING TO FILES
	public DefaultBehavior() {
		
		outFile = new File ("file_list.txt"); // create output file 
		
		try {
			fWriter = new FileWriter (outFile);
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // create file writer 
	    
	}
  	
   
	@Override
	public void performAction(Path file,
            BasicFileAttributes attrs) {
	
		
		// for debugging purposes
		System.out.print(file + ",");
		System.out.println(attrs.lastModifiedTime());
		 
		fileInformation = file.toString() + "," + attrs.lastModifiedTime();
		
		try {
			fWriter = new FileWriter(outFile, true);
			pWriter = new PrintWriter(fWriter);
			pWriter.println(fileInformation);
			pWriter.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
