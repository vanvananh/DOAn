package com.ptit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ptit.entity.Ward;

public interface WardRepository extends JpaRepository<Ward, Integer>, JpaSpecificationExecutor<Ward>{

}
