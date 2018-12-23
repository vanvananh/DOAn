package com.ptit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.ptit.entity.RoomBooked;

public interface RoomBookedRepository extends JpaRepository<RoomBooked, Integer>, JpaSpecificationExecutor<RoomBooked>{

	@Query(value = " from RoomBooked a where a.customerId.customerId = :id")
	public List<RoomBooked> getBookingByCustomerId(int id);
}
