package com.api.spi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StartTraverser {

	public static void main(String[] args) {
		
		Path filePath;
		
		if (args.length > 0)
		{
			filePath = Paths.get(args[0]);
		}
		else
		{
			filePath = Paths.get("."); 
		}
		// TODO Auto-generated method stub
		
		
		FileTraverserService ftr = new FileTraverserService();
		try {
			Files.walkFileTree(filePath, ftr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
