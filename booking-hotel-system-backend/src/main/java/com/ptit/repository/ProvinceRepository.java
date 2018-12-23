package com.ptit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ptit.entity.Province;

public interface ProvinceRepository extends JpaRepository<Province, Integer>, JpaSpecificationExecutor<Province>{

}