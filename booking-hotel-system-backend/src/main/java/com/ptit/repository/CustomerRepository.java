package com.ptit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ptit.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>,JpaSpecificationExecutor<Customer>{
	
	@Query(value = "from Customer a where (a.fullname like (%:name%) escape '&') or (a.username like (%:name%) escape '&')")
	public List<Customer> getListCustomerByName(@Param("name") String name);	
	@Query(value = "from Customer a where a.username = :name")
	public Customer getCustomerByUsername(@Param("name") String name);

}
