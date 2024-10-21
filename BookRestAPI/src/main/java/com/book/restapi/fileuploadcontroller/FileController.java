package com.book.restapi.fileuploadcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.book.restapi.helper.FileUploadHelperJar;

@RestController
public class FileController {
	@Autowired
	private FileUploadHelperJar fileUploadHelper;

	@PostMapping("/upload-file")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		// Check if the file is empty
		if (file.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
		}

		// Check if the upload is valid
		boolean isUploaded = fileUploadHelper.checkUpload(file);
		if (isUploaded) {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString();

			return ResponseEntity.ok("File uploaded successfully: " + fileDownloadUri);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
		}
	}
}
