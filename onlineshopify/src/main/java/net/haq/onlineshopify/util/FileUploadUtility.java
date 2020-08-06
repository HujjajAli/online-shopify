package net.haq.onlineshopify.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility {
	
	private static final String ABS_PATH  = "I:/Programming/J2EE-Git-Repo/spring/online-shopify/onlineshopify/src/main/webapp/assets/images/";
	private static String REAL_PATH = "";
	
	public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {
		//Get Real Path in Runtime
		REAL_PATH = request.getSession().getServletContext().getRealPath("/assets/images/");
		System.out.println("REAL PATH Runtime: "+REAL_PATH);
		
		//to make sure directory is exists
		//if not exists create a one
		if(! new File(ABS_PATH).exists()){
			//create the directories
			new File(ABS_PATH).mkdirs();
		}
		
		if(! new File(REAL_PATH).exists()){
			//create the directories
			new File(REAL_PATH).mkdirs();
		}
		
		try {
			//server upload 
			file.transferTo(new File(REAL_PATH + code + ".jpg"));
			//development location upload
			file.transferTo(new File(ABS_PATH  + code + ".jpg"));
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
