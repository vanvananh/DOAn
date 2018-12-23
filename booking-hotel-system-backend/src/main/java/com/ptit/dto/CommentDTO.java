package com.ptit.dto;

import com.ptit.entity.Comment;

public class CommentDTO {
	
	private String content;
	private int roomBookedId;
	private boolean isNegative;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getRoomBookedId() {
		return roomBookedId;
	}
	public void setRoomBookedId(int roomBookedId) {
		this.roomBookedId = roomBookedId;
	}
	public boolean getIsNegative() {
		return isNegative;
	}
	public void setIsNegative(boolean isNegative) {
		this.isNegative = isNegative;
	}
	
	public Comment toEntity() {
		return new Comment(roomBookedId, content, isNegative);
	}

}
