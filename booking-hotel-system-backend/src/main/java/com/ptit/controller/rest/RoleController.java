package com.ptit.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptit.entity.Role;
import com.ptit.service.RoleService;
import com.ptit.util.Constants;

@CrossOrigin("*")
@RestController
@RequestMapping(value = Constants.Url.API + Constants.Url.ROLE)
public class RoleController {

	@Autowired
	RoleService roleService;
	
	  @GetMapping(value = Constants.Url.RoleUrl.GET_ALL_ROLE)
	  public ResponseEntity<?> getAllCategoryQuestion() {
	    return new ResponseEntity<List<Role>>(
	        roleService.getAllRole(), HttpStatus.OK);
	  }
	
}
