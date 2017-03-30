package com.api.spi;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.Vector;

public class FileTraverserService extends SimpleFileVisitor<Path> {

	private Vector<FileActions> fileActions = new Vector<FileActions>();
	
	public FileTraverserService() {
		
		ServiceLoader<FileActions> loader = ServiceLoader.load(FileActions.class);
		Iterator<FileActions> iter = loader.iterator();
		
		while(iter.hasNext()){
			fileActions.add(iter.next());
			
		}
		
	};
    
	   
    public FileVisitResult visitFile(Path file,
                BasicFileAttributes attrs) {
            if(attrs.isRegularFile())
            {
            	int numOfFileActions = fileActions.size();
            	
                for(int i = 0; i < numOfFileActions; i++)
                {
                	fileActions.elementAt(i).performAction(file, attrs);
                }
            }
            
            return FileVisitResult.CONTINUE;
        }

    
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("About to visit " + dir);
        return FileVisitResult.CONTINUE;
    }

    
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       System.err.println(exc.getMessage());
       return FileVisitResult.CONTINUE;
    }

    
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       System.out.println("Just visited " + dir);
       return FileVisitResult.CONTINUE;
    }
	
}
