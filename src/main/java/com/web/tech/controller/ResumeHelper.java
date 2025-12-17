package com.web.tech.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component("resumeHelper")
public class ResumeHelper {

   @Value("${file.resume-upload-dir}")
   private String uploadDir;
   
   public boolean isSaveResume(MultipartFile resume) {
	   try {
		  Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
          Files.createDirectories(uploadPath); // Create directory if it doesn't exist

		  InputStream input=resume.getInputStream();
		  byte b[]=new byte[input.available()];
		  input.read(b);
		  FileOutputStream fout=new FileOutputStream(uploadPath.resolve(resume.getOriginalFilename()).toString());
		  fout.write(b);
		  fout.close();
		  return true;  
	   }
	   catch(Exception ex) {
		   ex.printStackTrace(); // Log the exception for debugging
		   return false;
	   }
   }
}
