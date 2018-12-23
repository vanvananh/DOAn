package com.ptit.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	 String uploadImage(MultipartFile image) throws IOException;
	 
	  boolean isFileExist(String path);

}
