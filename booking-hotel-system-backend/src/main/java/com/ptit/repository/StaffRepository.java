package com.ptit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ptit.entity.Staff;

public interface StaffRepository extends JpaRepository<Staff, Integer>,JpaSpecificationExecutor<Staff> {
	
	@Query( value = "from Staff h where h.username = :username")
	public Staff getStaffByUsername(@Param("username") String username);

}
