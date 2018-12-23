package com.ptit.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ptit.entity.RoomBooked;

public class RoomBookedDTO {
	
	private Integer customerId;
	private Integer roomId;
	private String startDate;
	private String endDate;
	private Integer status;
	private Double totalMoney;
	
	
	
	
	public RoomBookedDTO() {
		super();
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	
	public RoomBooked toEntity() throws ParseException {
		 Date date=new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
		 Date edate=new SimpleDateFormat("yyyy-MM-dd").parse(endDate);  
		return new RoomBooked(roomId, customerId, totalMoney, status,date, edate);
	}

}
