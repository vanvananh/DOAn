package com.ptit.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptit.entity.Status;
import com.ptit.service.StatusService;
import com.ptit.util.Constants;

@CrossOrigin("*")
@RestController
@RequestMapping(value = Constants.Url.API + Constants.Url.STATUS)
public class StatusController {
	
	@Autowired
	private StatusService statusS;
	
	@GetMapping(value = Constants.Url.CRUD.GET_ALL)
	 public ResponseEntity<?> getAllStatus() {
	    return new ResponseEntity<List<Status>>(
	    		statusS.getAllStatus(), HttpStatus.OK);
	  }

}
