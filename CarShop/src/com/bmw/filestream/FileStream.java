package com.bmw.filestream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class FileStream {
	
	OutputStream outStream;
    InputStream inStream;
    String realPath;
	
	public FileStream() {
		this.outStream = null;
		this.inStream = null;
	}

	public String getFileFromRequest(HttpServletRequest request) {
		
		this.realPath = request.getSession().getServletContext().getRealPath(""); // Get path of project
	    
	    String fileName = null;
	    
	    try {
		    final Part filePart = request.getPart("file"); //Get file
		    fileName = getFileNameFromHeader(filePart);
		    
		    inStream = filePart.getInputStream();
	    } catch(ServletException e) {
	    	System.out.println(e.toString());
	    } catch(IOException e) {
	    	System.out.println(e.toString());
	    }
	    
	    return fileName;
	}
	
	public void saveFile(String savePathFile, String fileName) {
	 	try {
	 		final String finalPath = realPath + savePathFile + "/" + fileName;
	        outStream = new FileOutputStream(new File(finalPath));
	        
	        int read = 0;
	        final byte[] bytes = new byte[1024];

	        while ((read = inStream.read(bytes)) != -1)
	            outStream.write(bytes, 0, read);

	    } catch (FileNotFoundException e) {
	    	System.out.println("File doesn't exist or access denied to save.");
	    } catch (IOException e) {
	    	System.out.println(e.toString());
	    }
	}
	
	private boolean isGoodExtension(String fileName, String extension) {
		String[] content = fileName.split("\\.");
		
		if(content.length == 2) {
			if(content[1].toUpperCase().equals((extension).toUpperCase()))
				return true;
		}
		return false;
	}
	
	public boolean isJPG(String fileName) {
		return this.isGoodExtension(fileName, "jpg");
	}
	
	public boolean isPNG(String fileName) {
		return this.isGoodExtension(fileName, "png");
	}
	
	private String getFileNameFromHeader(final Part part) {
	    for (String content : part.getHeader("content-disposition").split(";")) { // Split string to obtain file name
	        if (content.trim().startsWith("filename")) //Get file name
	            return content.substring(content.indexOf('=') + 1).trim().replace("\"", ""); // Remove not needed chars
	    }
	    return null;
	}
	
	public void close() {
		try {
			if (outStream != null)
	            outStream.close();
	        
	        if (inStream != null)
	            inStream.close();
		} catch(IOException e) {
			System.out.println(e.toString());
		}
	}
}
