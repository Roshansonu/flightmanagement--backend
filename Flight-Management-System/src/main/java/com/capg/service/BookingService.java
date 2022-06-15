package com.capg.service;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.capg.model.Booking;

public interface BookingService {
	
	public Booking createBooking(Booking newBooking);
	
	public Booking  updateBooking(Booking newBooking);

	public String deleteBooking(BigInteger bookingId);
	
	public ResponseEntity<Booking> findBookingById(BigInteger bookingId);

	public Iterable<Booking> displayAllBooking();
	
		
	}


