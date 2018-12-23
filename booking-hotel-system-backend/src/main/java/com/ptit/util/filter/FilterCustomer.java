package com.ptit.util.filter;

import java.util.Date;

public class FilterCustomer {

	private Date joinDateFrom;
	private Date joinDateTo;

	public FilterCustomer() {
		super();
	}

	public FilterCustomer(Date joinDateFrom, Date joinDateTo) {
		super();
		this.joinDateFrom = joinDateFrom;
		this.joinDateTo = joinDateTo;
	}

	public Date getJoinDateFrom() {
		return joinDateFrom;
	}

	public void setJoinDateFrom(Date joinDateFrom) {
		this.joinDateFrom = joinDateFrom;
	}

	public Date getJoinDateTo() {
		return joinDateTo;
	}

	public void setJoinDateTo(Date joinDateTo) {
		this.joinDateTo = joinDateTo;
	}

}
