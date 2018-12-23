package com.ptit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ptit.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>, JpaSpecificationExecutor<Comment>{

	@Query(value = " from Comment c where c.roomBookedId.roomId.hotelId.hotelId = :hotelId")
	public List<Comment> getListCommentOfHotel(@Param("hotelId") int hotelId);
	
	@Query(value = " from Comment c where c.roomBookedId.roomBookedId = :id")
	public Comment getCommentOfBooking(@Param("id") int id);
}
