package com.ptit.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ptit.entity.Customer;
import com.ptit.entity.Room;
import com.ptit.entity.RoomBooked;
import com.ptit.repository.RoomBookedRepository;
import com.ptit.repository.RoomRepository;
import com.ptit.util.Constants;
import com.ptit.util.MethodUtil;
import com.ptit.util.Paging;
import com.ptit.util.Sorting;
import com.ptit.util.Constants.Operation;
import com.ptit.util.filter.FilterBooking;
import com.ptit.util.filter.FilterCustomer;
import com.ptit.util.specification.CriteriaCustom;
import com.ptit.util.specification.SpecificationBuilder;

@Service
public class RoomBookedServicePImpl implements RoomBookedService {

	@Autowired
	RoomBookedRepository bookingRepository;

	@Autowired
	RoomRepository roomRepository;

	@Override
	public RoomBooked createBooking(RoomBooked booking) {
		// TODO Auto-generated method stub
		booking = bookingRepository.save(booking);
//		List<Room> lst = roomRepository.checkEmpty(booking.getRoomId().getRoomId(), booking.getStartDate(),
//				booking.getEndDate());
		return getById(booking.getRoomBookedId());
	}

	@Override
	public RoomBooked getById(int id) {
		// TODO Auto-generated method stub
		return bookingRepository.findOne(id);
	}

	@Override
	public boolean isExists(int id) {
		// TODO Auto-generated method stub
		return bookingRepository.exists(id);
	}

	@Override
	public List<RoomBooked> getBookingByCustomerId(int id) {
		// TODO Auto-generated method stub
		return bookingRepository.getBookingByCustomerId(id);
	}

	@Override
	public Page<RoomBooked> getAllRoomBooked(Paging paging, Sorting sorting, FilterBooking filter) {
		// TODO Auto-generated method stub
		SpecificationBuilder<RoomBooked> specification = new SpecificationBuilder<RoomBooked>();

		// roleId
		if (!MethodUtil.isNull(filter.getstatusId())) {
			specification.addCriteriaCustom(new CriteriaCustom(Constants.NameColume.BOOKING_FILTER_STATUS_ID,
					Operation.EQUAL, filter.getstatusId()));
		}

		sorting.and(Direction.DESC, Constants.NameColume.ROOM_BOOKED_ID);

		// query with paging
		if (!MethodUtil.isNull(paging)) {
			return bookingRepository.findAll(specification.build(), MethodUtil.Pagination(paging, sorting));
		}
		// else query not paging
		return new PageImpl<RoomBooked>(
				bookingRepository.findAll(specification.build(), MethodUtil.convertSort(sorting)));

	}

}
