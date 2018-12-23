/**
 * 
 */
package com.ptit.controller.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

import com.ptit.service.FileService;
import com.ptit.util.*;


/**
 * @author NNDuy
 * @Date Mar 14, 2018
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = Constants.Url.API + Constants.Url.FILE)
public class FileController {

  // declare file service
  @Autowired
  private FileService fileService;

  /**
   * upload file image
   * 
   * @param image
   * @return ResponseEntity<?>
   * @author: NNDuy
   */
  @PostMapping(value = Constants.Url.FileUrl.UPLOAD_IMAGE)
  public ResponseEntity<?> upLoadImage(
      @RequestParam(name = Constants.Param.IMAGE) MultipartFile image) {


    if (MethodUtil.isNull(image)) {

      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.File.IMAGE_NOT_VALID),
          HttpStatus.UNPROCESSABLE_ENTITY);
    }


    if (!new ManagerFile().isTypeFileImage(image)) {

      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.File.FILE_NOT_IMAGE),
          HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // validate size image < 3MB
    if (image.getSize() > Constants.Common.THREE_MB) {
      // return message error Image > 3MB
      return new ResponseEntity<ApiMessage>(
          new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.File.IMAGE_LARGE_THREE_MB),
          HttpStatus.UNPROCESSABLE_ENTITY);
    }


    try {

      return new ResponseEntity<String>(fileService.uploadImage(image), HttpStatus.OK);
    } catch (IOException e) {
      return new ResponseEntity<ApiMessage>(new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY,
          MessageUtil.Question.UPLOAD_IMAGE_NOT_SUCCESS), HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

}
