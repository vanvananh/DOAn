package com.ptit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ptit.entity.HotelType;

public interface HotelTypeRepository extends JpaRepository<HotelType, Integer>, JpaSpecificationExecutor<HotelType>{

}
