// replace second plugin in Class-Path variable with this text 
// to use this plugin during  command line execution:
// FT_lib/CountFileLines.jar

package com.api.spi.cfl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import com.api.spi.FileActions;

public class CountFileLines implements FileActions {

	String[] acceptedTypes;
	private String fileName;
	private String fileType;
	private InputStream is;
	private int count;
	File outFile;
	FileWriter fWriter;
	PrintWriter pWriter;
	
	public CountFileLines(){
		acceptedTypes = new String[]{ "java", "cpp", "py", "asm", "js" };
		fileName = null;
		fileType = null;
		count = 0;
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
		// TODO Auto-generated method stub
		
		try {
			count = countFileLines(file, attrs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// output line count to file 
		
	}
	
	private int countFileLines(Path file, BasicFileAttributes attrs)
			throws IOException{
		
		if(isSourceFile(file)){
			
			count = 0;
			fileName = file.toString();			
			// if file is a source type file 
			is = new BufferedInputStream(new FileInputStream(fileName));
		    
		    try 
		    {
		        byte[] charBuffer = new byte[1024];
		        int count = 0;
		        int readChars = 0;
		        boolean endsWithoutNewLine = false;
		    
		        while ((readChars = is.read(charBuffer)) != -1)
		        {
		            for (int i = 0; i < readChars; i++)
		            {
		                if (charBuffer[i] == '\n')
		                    count++;
		            }
		            
		            endsWithoutNewLine = (charBuffer[readChars - 1] != '\n');
		        }
		        
		        if(endsWithoutNewLine)
		        {
		            count++;
		        } 
		    
		        try {
					fWriter = new FileWriter(outFile, true);
					pWriter = new PrintWriter(fWriter);
					pWriter.println(file.getFileName() + " has " + count + " lines.");
					pWriter.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
		        return count;
		    } 
		    finally 
		    {
		        is.close();
		    }
		}
		
		return count;
			
	}
	
	private boolean isSourceFile(Path file) throws IOException{
		
		fileType = file.toString().toLowerCase();
		fileType = fileType.substring(fileType.lastIndexOf(".") + 1);
		
		int arraySize = acceptedTypes.length;
		
		for(int i = 0; i < arraySize; i++)
		{
			if(fileType.equals(acceptedTypes[i]))
			{
				return true;
			}
		}
		
		return false;
	}
	
	
	
}
