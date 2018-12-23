package com.ptit.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptit.entity.RoomType;
import com.ptit.service.RoomTypeService;
import com.ptit.util.Constants;

@CrossOrigin("*")
@RestController
@RequestMapping(value = Constants.Url.API + Constants.Url.ROOM_TYPE)
public class RoomTypeController {
	
	@Autowired
	private RoomTypeService roomTypeService;
	
	  @GetMapping(value = Constants.Url.CRUD.GET_ALL)
	  public ResponseEntity<?> getAllRoomType() {
	    return new ResponseEntity<List<RoomType>>(
	    		roomTypeService.getAllRoomType(), HttpStatus.OK);
	  }

}
