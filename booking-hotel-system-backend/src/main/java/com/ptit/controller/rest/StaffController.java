package com.ptit.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ptit.dto.CustomerDTO;
import com.ptit.dto.StaffDTO;
import com.ptit.entity.Customer;
import com.ptit.entity.Staff;
import com.ptit.service.ConfigService;
import com.ptit.service.StaffService;
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
@RequestMapping(value = Constants.Url.API + Constants.Url.STAFF)
public class StaffController {

	@Autowired
	StaffService staffService;
	
	@Autowired
	ConfigService configService;
	
	
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
		Page<Staff> page = staffService.getAllStaff(
				!MethodUtil.isNull(pageNumber) ? new Paging(pageNumber, configService.getNumberRecordPerPageDefault())
						: null,
				new Sorting(sortType, sortField), keywordSearch, filter);
		// validate param success then return list category question
		return new ResponseEntity<DataRespone>(
				new DataRespone(pageNumber, page.getTotalPages(), page.getContent().size(), page.getContent()),
				HttpStatus.OK);
	}
	
	@PostMapping(value = Constants.Url.CRUD.CREATE)
	public ResponseEntity<?> createCustomer(@RequestBody StaffDTO staff) {
		return new ResponseEntity<Staff>(staffService.createStaff(staff.toEntity()), HttpStatus.OK);
	}
	
	@GetMapping(value = Constants.Url.StaffUrl.LOGIN)
	public ResponseEntity<?> login(@RequestParam String username) {
		// validate param id
//		if (!staffService.isExists(id)) {
//			// return message error param id not valid
//			return new ResponseEntity<ApiMessage>(
//					new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.PARAMETER_ID_NOT_VALID),
//					HttpStatus.UNPROCESSABLE_ENTITY);
//		}

		// validate param success then return account by id param
		Staff s = staffService.getStaffByUsername(username);
		if (s == null) {
			return new ResponseEntity<ApiMessage>(
					new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.PARAMETER_ID_NOT_VALID),
					HttpStatus.UNPROCESSABLE_ENTITY);
			
		}
		return new ResponseEntity<Staff>(s, HttpStatus.OK);
	}
	
	@DeleteMapping(value = Constants.Url.CRUD.DELETE)
	  public ResponseEntity<?> deleteCategoryQuestion(
	      @RequestParam(name = Constants.Param.ID) List<Integer> ids) {

	    // check list ids is empty
	    if (MethodUtil.checkListIsNull(ids)) {
	      return new ResponseEntity<ApiMessage>(
	          new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.PARAMETER_LIST_ID_NOT_VALID),
	          HttpStatus.UNPROCESSABLE_ENTITY);
	    }

	    // validate param ids
	    if (!staffService.isListRoleExist(ids)) {
	      // return message error id not valid
	      return new ResponseEntity<ApiMessage>(
	          new ApiMessage(HttpStatus.UNPROCESSABLE_ENTITY, MessageUtil.PARAMETER_LIST_ID_NOT_EXIST),
	          HttpStatus.UNPROCESSABLE_ENTITY);
	    }

	    // if validate param id success then delete group by id
	    staffService.deleteListStaff(ids);
	    // return success

	    return new ResponseEntity<ApiMessage>(
	        new ApiMessage(HttpStatus.OK, MessageUtil.DELETE_SUCCESSFULLY), HttpStatus.OK);
	  }
	
	
//	@GetMapping(value = Constants.Url.CustomerUrl.LOGIN)
//	public ResponseEntity<?> getCustomerByUsername(@RequestParam("username") String username) {
//
//
//		// validate param success then return account by id param
//		return new ResponseEntity<Staff>(staffService.getStaffByUsername(username), HttpStatus.OK);
//	}

}
