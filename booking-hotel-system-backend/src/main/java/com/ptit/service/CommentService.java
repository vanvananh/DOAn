package com.ptit.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;

import com.ptit.entity.Comment;
import com.ptit.util.Paging;
import com.ptit.util.Sorting;
import com.ptit.util.filter.FilterStaff;

public interface CommentService {
	public Comment createComment(Comment cmt) throws IOException;
	public Page<Comment> getAllComment(Paging paging, Sorting sorting, String keywordSearch, FilterStaff filter);
	
	public void training(List<Integer> ids) throws IOException;
	
	public Comment getById(int id);
	
	public void updateIsNegative(int id);
	
	public boolean isExists(int id);
	
	public List<Comment> getListCommentOfHotel(int hotelId);
	
	public Comment getCommentByBooking(int id);

}
