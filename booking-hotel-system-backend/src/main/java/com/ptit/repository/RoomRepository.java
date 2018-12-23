package com.ptit.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.ptit.entity.Room;
import com.ptit.util.Constants;

public interface RoomRepository extends JpaRepository<Room, Integer>, JpaSpecificationExecutor<Room> {
	
	@Query(value = "from Room r where r.hotelId.hotelId = :id and r.roomTypeId.roomTypeId = :roomTypeId ")
	public List<Room> getListRoomOfHotel(@Param(Constants.Param.ID) int hotelId,@Param("roomTypeId") int roomTypeId);
	
	
	@Query(value = "from Room r , RoomBooked b\r\n" + 
			"where r.roomId = b.roomId\r\n" + 
			"and r.roomId = :id and ((b.startDate >= :startDate and b.startDate <= :endDate) "
			+ "or (b.startDate >= :startDate and b.endDate <= :endDate) "
			+ "or (b.endDate>= :startDate and b.endDate<= :endDate))" )
	public List<Room> checkEmpty(@Param(Constants.Param.ID)int id,@Param("startDate") Date startDate,@Param("endDate") Date endDate);

	@Query(value = "from Room r where r.hotelId.hotelId = :id")
	public List<Room> getListRoom(@Param(Constants.Param.ID) int hotelId);
}
