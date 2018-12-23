package com.ptit.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptit.entity.Ward;
import com.ptit.service.RoleService;
import com.ptit.service.WardService;
import com.ptit.util.Constants;

@CrossOrigin("*")
@RestController
@RequestMapping(value = Constants.Url.API + Constants.Url.WARD)
public class WardController {

	@Autowired
	private WardService wardService;
	
	@Autowired
	RoleService roleService;
	
	  @GetMapping(value = Constants.Url.CRUD.GET_ALL)
	  public ResponseEntity<?> getAllWard() {
	    return new ResponseEntity<List<Ward>>(
	    		wardService.getAllWard(), HttpStatus.OK);
	  }
}
