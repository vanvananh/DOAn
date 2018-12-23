package com.ptit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ptit.entity.District;

public interface DistrictRepository extends JpaRepository<District, Integer>, JpaSpecificationExecutor<District>{

}
