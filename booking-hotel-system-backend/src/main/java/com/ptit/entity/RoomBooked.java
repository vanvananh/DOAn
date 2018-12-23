package com.ptit.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "roombooked")
public class RoomBooked {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "room_booked_id", unique = true, nullable = false)
	private int roomBookedId;
	
	@JoinColumn(name = "room_id", referencedColumnName = "room_id", nullable = false)
	@ManyToOne(optional = false)
	private Room roomId;
	
	@JoinColumn(name = "customer_id", referencedColumnName = "customer_id", nullable = false)
	@ManyToOne(optional = false)
	private Customer customerId;
	
	@Basic(optional = false)
	@Column(name = "total_money", unique = false, nullable = false)
	private Double totalMoney;
	
	
	@JoinColumn(name = "status_id", referencedColumnName = "status_id", nullable = false)
	@ManyToOne(optional = false)
	private Status statusId;
	
	@Basic(optional = false)
	@Column(name = "start_date", nullable = false, length = 10, updatable = false)
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Basic(optional = false)
	@Column(name = "end_date", nullable = false, length = 10, updatable = false)
	@Temporal(TemporalType.DATE)
	private Date endDate;

	public RoomBooked(Integer roomId, Integer customerId, Double totalMoney, Integer status, Date startDate,
			Date endDate) {
		super();
		this.roomId = new Room(roomId);
		this.customerId = new Customer(customerId);
		this.totalMoney = totalMoney;
		this.statusId = new Status(status);
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public RoomBooked(Integer roomId, Integer customerId, Double totalMoney, Integer status, 
			Date endDate) {
		super();
		this.roomId = new Room(roomId);
		this.customerId = new Customer(customerId);
		this.totalMoney = totalMoney;
		this.statusId = new Status(status);
		this.endDate = endDate;
	}


	public RoomBooked() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public RoomBooked(int roomBookedId) {
		this.roomBookedId = roomBookedId;
		// TODO Auto-generated constructor stub
	}

	public int getRoomBookedId() {
		return roomBookedId;
	}

	public void setRoomBookedId(int roomBookedId) {
		this.roomBookedId = roomBookedId;
	}

	public Room getRoomId() {
		return roomId;
	}

	public void setRoomId(Room roomId) {
		this.roomId = roomId;
	}

	public Customer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}

	public Double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Status getStatusId() {
		return statusId;
	}

	public void setStatusId(Status status) {
		this.statusId = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
	

}
