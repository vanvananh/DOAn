package com.ptit.util;

import java.io.File;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;



public class ManagerFile {
	public ManagerFile() {

	  }


	  public boolean checkFileOrFolderExist(String path) throws IOException {
	    if (MethodUtil.isNull(path)) {
	      throw new IOException("Param filePath is null");
	    }

	    return new File(path).exists();
	  }


	  public boolean isTypeFileImage(MultipartFile file) {


	    if (MethodUtil.isNull(file)) {
	      return false;
	    }


	    return file.getContentType().toLowerCase().contains(Constants.Common.IMAGE);
	  }


	  public boolean isFolder(String path) {
	    return new File(path).isDirectory();
	  }


	  public boolean createFile(String filePath) throws IOException {

	    if (checkFileOrFolderExist(filePath)) {

	      System.out.println(MessageUtil.File.FILE_OR_FOLDER_EXIST);
	      return false;
	    }

	    new File(filePath).createNewFile();

	    System.out.println(MessageUtil.File.CREATE_FILE_SUCCESS);
	    return true;
	  }


	  public void createMultiPartFile(String filePath, MultipartFile multipartFile) throws IOException {


	    if (MethodUtil.isNull(multipartFile)) {
	      throw new IOException();
	    }

	    if (checkFileOrFolderExist(filePath)) {

	      throw new IOException(MessageUtil.File.FILE_OR_FOLDER_EXIST);
	    }

	
	    File file = new File(filePath);
	    multipartFile.transferTo(file);
	  }


	  public boolean createFolder(String folderPath) throws IOException {
	    // if file Exist
	    if (checkFileOrFolderExist(folderPath)) {
	      // Show message error
	      System.out.println(MessageUtil.File.FILE_OR_FOLDER_EXIST);
	      return false;
	    }


	    new File(folderPath).mkdirs();
	    // Show message
	    System.out.println(MessageUtil.File.CREATE_FOLDER_SUCCESS);
	    return true;
	  }


	  public static String getFormatFile(String input) {
	    String[] results = input.split(Constants.Common.DOT_ENCODE);
	    return results[results.length - 1];
	  }

	  public static String getNameFolderOfPath(String path) {
	    String[] results = path.split("\\");
	    return results[results.length - 2];
	  }
}
