package com.capg.service;

import java.math.BigInteger;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capg.dao.BookingDao;
import com.capg.exception.RecordAlreadyPresentException;
import com.capg.exception.RecordNotFoundException;
import com.capg.model.Booking;

@Service
public class IBookingService implements BookingService {
	@Autowired
	BookingDao bookingDao;

	
	
	@Override
	public Booking updateBooking(Booking newBooking) {
		Optional<Booking> findBookingById= bookingDao.findById(newBooking.getBookingId());
		if(findBookingById.isPresent()) {
			
			bookingDao.save(newBooking);
		}else
			
			throw new RecordNotFoundException("Booking With Booking Id : " + newBooking.getBookingId() + " not exist!");
		
		return newBooking;
	}

	@Override
	public String deleteBooking(BigInteger bookingId) {
		
		Optional<Booking> findBookingById = bookingDao.findById(bookingId);
		
	if(findBookingById.isPresent()) {
		
		bookingDao.deleteById(bookingId);
		return " Booking Deleted !!";
	}else
		throw new RecordNotFoundException("Booking not found for the entered BookingId");
	}

	@Override
	public ResponseEntity<Booking> findBookingById(BigInteger bookingId) {
		Optional<?> findByid = bookingDao.findById(bookingId);
		try {
			if(findByid.isPresent()) {
				
				Booking findBooking= (Booking) findByid.get();
				return new ResponseEntity<Booking>(findBooking, HttpStatus.OK);
				
			}else
				
				throw new RecordNotFoundException("No record found with Id" + bookingId);
			
		}catch(RecordNotFoundException e) {
			
		return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Iterable<Booking> displayAllBooking() {
		
		return bookingDao.findAll();
	}

	@Override
	public Booking createBooking(Booking newBooking) {
		// TODO Auto-generated method stub
		Booking saveInfo = bookingDao.save(newBooking);
		return saveInfo;
	}



}
