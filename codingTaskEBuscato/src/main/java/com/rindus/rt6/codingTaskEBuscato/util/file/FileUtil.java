package com.rindus.rt6.codingTaskEBuscato.util.file;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class FileUtil {	
	
	/**
	 * Saves the file in the default downloads folder, 
	 * with the name and content passed as parameters
	 * 
	 * @param response
	 * @param filename
	 * @param content
	 */
	public static void saveFile(HttpServletResponse response, String filename, String content)   {
		
		 response.setHeader("Content-Disposition","attachment; filename="+filename);
		 try {
			 
			response.getWriter().write(content);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				response.getWriter().close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 
		       
	}
	
	

}
