package com.ptit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ptit.dto.HotelSearchDTO;
import com.ptit.entity.Hotel;



public interface HotelRepository extends JpaRepository<Hotel, Integer>, JpaSpecificationExecutor<Hotel>{
	
	@Query(value = "select distinct h from Hotel h, Room r where h.locationId.locationId = :#{#hotelSearchDto.locationId} and  r.roomTypeId.roomTypeId = :#{#hotelSearchDto.typeOfRoomId}")
	public List<Hotel> getHotelBySearch(@Param("hotelSearchDto")HotelSearchDTO hotelSearchDto); 
	

}
