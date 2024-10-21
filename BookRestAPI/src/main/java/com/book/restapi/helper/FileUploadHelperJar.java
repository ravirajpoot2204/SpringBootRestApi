package com.book.restapi.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelperJar  {

	/*
	 * public final String uploadDirect
	 * ="D:\\SpringBoot\\BookRestAPI\\src\\main\\resources\\static\\image\\";
	 */ 
	public final String uploadDirect = Paths.get("src/main/resources/static/image").toAbsolutePath().toString();

	public boolean checkUpload(MultipartFile file) {
		boolean f = false;

		try {
			/*
			 * InputStream is = file.getInputStream(); byte[] data = is.readAllBytes();
			 * is.read(data); FileOutputStream fos = new FileOutputStream(uploadDirect +
			 * File.separator + file.getOriginalFilename()); fos.write(data); fos.close();
			 * is.close();
			 */
			Files.copy(file.getInputStream(),Paths.get(uploadDirect+File.separator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			f = true;
		} catch (Exception e) {
			// TODO: handle exception
			f=false;
			e.printStackTrace();
		}
		return f;
	}

}
