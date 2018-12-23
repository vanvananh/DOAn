package com.ptit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ptit.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Integer>, JpaSpecificationExecutor<Location>{
	@Query(value = "from Location l where l.popular = 1")
	public List<Location> getPopu();

}
