package com.ptit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import com.ptit.dto.CustomerDTO;
import com.ptit.entity.Customer;
import com.ptit.repository.CustomerRepository;
import com.ptit.util.Constants;
import com.ptit.util.MethodUtil;
import com.ptit.util.Paging;
import com.ptit.util.Sorting;
import com.ptit.util.filter.FilterCustomer;
import com.ptit.util.specification.CriteriaCustom;
import com.ptit.util.specification.SpecificationBuilder;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerRepository customerRepository;
	
	
	@Override
	public boolean isExists(int id) {
		// TODO Auto-generated method stub
		return customerRepository.exists(id);
	}

	@Override
	public CustomerDTO getById(int id) {
		// TODO Auto-generated method stub
		return new CustomerDTO(customerRepository.findOne(id));
	}

	@Override
	public List<Customer> getListCustomerByName(String name) {
		// TODO Auto-generated method stub
		return customerRepository.getListCustomerByName(name);
	}

	@Override
	public Customer createCustomer(Customer customer) {
		
		// TODO Auto-generated method stub
		customer = customerRepository.save(customer);
		return customerRepository.findOne(customer.getCustomerId());
	}

	@Override
	public Page<Customer> getAllCustomers(Paging paging, Sorting sorting, String keywordSearch, FilterCustomer filter) {
		// TODO Auto-generated method stub
		SpecificationBuilder<Customer> specification = new SpecificationBuilder<Customer>();
	    // search
	    if (!MethodUtil.isNull(keywordSearch)) {
	      specification.addCriteriaCustom(new CriteriaCustom(Constants.NameColume.FULL_NAME_SEARCH,
	          Constants.Operation.LIKE, keywordSearch));
	    }
	    
	    
	    
	    sorting.convertSort(Constants.NameColume.FULL_NAME, Constants.NameColume.FULL_NAME_SEARCH);
	    
	    sorting.and(Direction.ASC, Constants.NameColume.CUSTOMER_ID);
	    
	    // query with paging
	    if (!MethodUtil.isNull(paging)) {
	      return customerRepository.findAll(specification.build(), MethodUtil.Pagination(paging, sorting));
	    }
	    // else query not paging
	    return new PageImpl<Customer>(
	    		customerRepository.findAll(specification.build(), MethodUtil.convertSort(sorting)));
			
	}

	@Override
	public Customer getCustomerByUsername(String username) {
		// TODO Auto-generated method stub
		return customerRepository.getCustomerByUsername(username);
	}



}
