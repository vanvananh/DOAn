package com.ptit.service;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.ptit.util.Constants;
import com.ptit.util.ManagerFile;
import com.ptit.util.MethodUtil;


@Service
public class FileServiceImpl implements FileService{
	
	  @Autowired
	  private ConfigService configService;

	@Override
	 public String uploadImage(MultipartFile image) throws IOException {


	    String linkFolderSave = configService.getFolderSaveImage();
	    String nameImage = new Date().getTime() + Constants.Common.DOT
	        + ManagerFile.getFormatFile(image.getOriginalFilename());


	    return createFile(linkFolderSave, nameImage, image);
	  }

	@Override
	public boolean isFileExist(String path) {
	    String linkFile = MethodUtil.getLinkProject() + path;

	    boolean result;
	    try {
	      result = new ManagerFile().checkFileOrFolderExist(linkFile);
	    } catch (IOException e) {
	      result = false;
	    }
	    return result;
	}

	  private String createFile(String linkFolderSave, String nameFile, MultipartFile file)
		      throws IOException {

		    String linkProject = MethodUtil.getLinkProject();


		    ManagerFile managerFile = new ManagerFile();

		    // check folder is exist
		    if (!managerFile.checkFileOrFolderExist(linkProject + linkFolderSave)) {
		      managerFile.createFolder(linkProject + linkFolderSave);
		    }
		    managerFile.createMultiPartFile(linkProject + linkFolderSave + "\\" + nameFile, file);

		    return linkFolderSave + "\\" + nameFile;
		  }
	
}
