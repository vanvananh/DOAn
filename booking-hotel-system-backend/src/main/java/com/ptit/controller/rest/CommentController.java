package com.ptit.controller.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ptit.dto.CommentDTO;
import com.ptit.dto.CustomerDTO;
import com.ptit.entity.Comment;
import com.ptit.entity.Role;
import com.ptit.entity.Staff;
import com.ptit.service.CommentService;
import com.ptit.service.ConfigService;
import com.ptit.service.HotelService;
import com.ptit.util.ApiMessage;
import com.ptit.util.Constants;
import com.ptit.util.DataRespone;
import com.ptit.util.MessageUtil;
import com.ptit.util.MethodUtil;
import com.ptit.util.Paging;
import com.ptit.util.RegularExpressions;
import com.ptit.util.Sorting;
import com.ptit.util.filter.FilterStaff;

@CrossOrigin("*")
@RestController
@RequestMapping(value = Constants.Url.API + Constants.Url.COMMENT)
public class CommentController {

	@Autowired
	CommentService commentService;

	@Autowired
	ConfigService configService;
	
	@Autowired
	HotelService hotelService;

	@PostMapping(value = Constants.Url.CRUD.CREATE)
	public ResponseEntity<?> createCustomer(@RequestBody CommentDTO cmt) throws IOException {
		return new ResponseEntity<Comment>(commentService.createComment(cmt.toEntity()), HttpStatus.OK);
	}

	@GetMapping(value = Constants.Url.CRUD.GET_ALL)
	public ResponseEntity<?> getAllStaff(
			@RequestParam(name = Constants.Param.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(name = Constants.Param.SORT_TYPE, defaultValue = Constants.Param.SORT_ASC) String sortType,
			@RequestParam(name = Constants.Param.SORT_FIELD, defaultValue = Constants.Param.SORT_FIELD_ACCOUNT_DEFAULT) String sortField,
			@RequestParam(name = Constants.Param.KEYWORD_SEARCH, required = false) String keywordSearch,
			FilterStaff filter) {

		// validate param pageNumber <= 0
		if (!MethodUtil.isNull(pageNumber) && pageNumber <= Constants.Common.ZERO_INT) {
			// return message error param pageNumber not valid
			return new ResponseEntity<ApiMessage>(
					new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.PARAMETER_PAGENUMBER_NOT_VALID),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}

		// validate param sortType
		if (!MethodUtil.isNull(sortType)
				&& !MethodUtil.checkRegularExpression(sortType.toLowerCase(), RegularExpressions.SORT_TYPE_PATTERN)) {
			// return message error param sortType not valid
			return new ResponseEntity<ApiMessage>(
					new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.PARAMETER_SORTTYPE_NOT_VALID),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		Page<Comment> page = commentService.getAllComment(
				!MethodUtil.isNull(pageNumber) ? new Paging(pageNumber, configService.getNumberRecordPerPageDefault())
						: null,
				new Sorting(sortType, sortField), keywordSearch, filter);
		// validate param success then return list category question
		return new ResponseEntity<DataRespone>(
				new DataRespone(pageNumber, page.getTotalPages(), page.getContent().size(), page.getContent()),
				HttpStatus.OK);
	}

	@DeleteMapping(value = Constants.Url.CRUD.DELETE)
	public ResponseEntity<?> trainingNewComment(@RequestParam(name = Constants.Param.ID) List<Integer> ids)
			throws IOException {

		commentService.training(ids);
		System.out.println(ids.get(0));
		return new ResponseEntity<ApiMessage>(new ApiMessage(HttpStatus.OK, MessageUtil.UPDATE_SUCCESSFULLY),
				HttpStatus.OK);
	}

	@GetMapping(value = Constants.Url.CRUD.GET_BY_ID)
	public ResponseEntity<?> trainingNewComment(@RequestParam(name = Constants.Param.ID) int id) throws IOException {

		if (!commentService.isExists(id)) {

			return new ResponseEntity<ApiMessage>(
					new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.PARAMETER_ID_NOT_VALID),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}

		return new ResponseEntity<Comment>(commentService.getById(id), HttpStatus.OK);
	}

	@GetMapping(value ="/getByHotel")
	public ResponseEntity<?> getListCommentOfHotel(@RequestParam(name = Constants.Param.ID) int id) throws IOException {
		
		if(!hotelService.isExists(id)) {
			return new ResponseEntity<ApiMessage>(
					new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, "Khách sạn không tồn tại"),
					HttpStatus.UNPROCESSABLE_ENTITY);
			
		}
		return new ResponseEntity<List<Comment>>(
		        commentService.getListCommentOfHotel(id), HttpStatus.OK);
	}
	
	
	@GetMapping(value ="/getByBooking")
	public ResponseEntity<?> getListCommentOfBooking(@RequestParam(name = Constants.Param.ID) int id) throws IOException {
		
//		if(!.isExists(id)) {
//			return new ResponseEntity<ApiMessage>(
//					new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, "Khách sạn không tồn tại"),
//					HttpStatus.UNPROCESSABLE_ENTITY);
//			
//		}
		return new ResponseEntity<Comment>(
		        commentService.getCommentByBooking(id), HttpStatus.OK);
	}
	
	@GetMapping(value = Constants.Url.CommentUrl.UPDATE_SENTIMENT)
	public ResponseEntity<?> deleteCategoryQuestion(@RequestParam(name = Constants.Param.ID) int id) throws IOException {
		commentService.updateIsNegative(id);
		return new ResponseEntity<ApiMessage>(new ApiMessage(HttpStatus.OK, MessageUtil.UPDATE_SUCCESSFULLY),
				HttpStatus.OK);
	}

}
