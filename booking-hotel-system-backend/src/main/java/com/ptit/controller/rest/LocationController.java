package com.ptit.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptit.entity.Location;
import com.ptit.service.LocationService;
import com.ptit.util.Constants;

@CrossOrigin("*")
@RestController
@RequestMapping(value = Constants.Url.API + Constants.Url.LOCATION)
public class LocationController {
	
	@Autowired
	private LocationService wardService;
	

	
	  @GetMapping(value = Constants.Url.CRUD.GET_ALL)
	  public ResponseEntity<?> getAllWard() {
	    return new ResponseEntity<List<Location>>(
	    		wardService.getAllWard(), HttpStatus.OK);
	  }
	  
	  @GetMapping("/popular")
	  public ResponseEntity<?> getPopu() {
	    return new ResponseEntity<List<Location>>(
	    		wardService.getPopu(), HttpStatus.OK);
	  }

}
