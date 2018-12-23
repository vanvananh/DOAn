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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "comment")
public class Comment {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "comment_id", unique = false, nullable = false)
	private int commentId;
	
	@JoinColumn(name = "room_booked_id", referencedColumnName = "room_booked_id", nullable = false)
	@OneToOne(optional = false)
	private RoomBooked roomBookedId;
	
	@Basic(optional = false)
	@Column(name = "content", nullable = false, length = 5000)
	private String content;
	
	@Basic(optional = false)
	@Column(name = "is_negative", unique = false,nullable = false)
	private boolean isNegative;
	
	@Basic(optional = false)
	@Column(name = "training", unique = false,nullable = false)
	private boolean training;
	
	@Basic(optional = false)
	@Column(name = "create_date", nullable = false, length = 10, insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;



	public Comment(int roomBookedId, String content, boolean isNegative, boolean training , Date createDate) {
		super();
		this.roomBookedId = new RoomBooked(roomBookedId);
		this.content = content;
		this.training = training;
		this.isNegative = isNegative;
		this.createDate = createDate;
	}
	

	public Comment(int roomBookedId, String content, boolean isNegative) {
		super();
		this.roomBookedId = new RoomBooked(roomBookedId);
		this.content = content;
		this.isNegative = isNegative;
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public RoomBooked getRoomBookedId() {
		return roomBookedId;
	}

	public void setRoomBookedId(RoomBooked roomBookedId) {
		this.roomBookedId = roomBookedId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public boolean isTraining() {
		return training;
	}


	public void setTraining(boolean training) {
		this.training = training;
	}


	public boolean getIsNegative() {
		return isNegative;
	}

	public void setIsNegative(boolean isNegative) {
		this.isNegative = isNegative;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
