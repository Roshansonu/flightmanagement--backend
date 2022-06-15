package com.capg.service;
//import exceptions.*;
import java.math.BigInteger;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestBody;

import com.capg.dao.FlightDao;
import exceptions.RecordAlreadyPresentException;
import com.capg.model.Flight;

import exceptions.RecordNotFoundException;

@Service
public class FlightServiceImpl implements FlightService {
	@Autowired
	FlightDao flightDao;

	
	@Override
	public Flight addFlight(Flight flight) {
		
			Flight saveInfo=flightDao.save(flight);
			 return saveInfo;
			
	}


	@Override
	public Iterable<Flight> viewAllFlight() {
		return flightDao.findAll();
	}

	
	public Flight viewFlight(BigInteger flightNumber) {
		Optional<Flight> findById = flightDao.findById(flightNumber);
		if (findById.isPresent()) {
			return findById.get();
		}
		else
			throw new RecordNotFoundException("Flight with number: " + flightNumber + " not exists");
	    }
	

	
	@Override
	public Flight modifyFlight(Flight flight) {
		Optional<Flight> findById = flightDao.findById(flight.getFlightNo());
		if (findById.isPresent()) {
			flightDao.save(flight);
		} else
			throw new RecordNotFoundException("Flight with number: " + flight.getFlightNo() + " not exists");
		return flight;
	}

	
	public String removeFlight(BigInteger flightNumber) {
		Optional<Flight> findById = flightDao.findById(flightNumber);
		if (findById.isPresent()) {
			flightDao.deleteById(flightNumber);
			return "Flight removed!!";
		} else
			throw new RecordNotFoundException("Flight with number: " + flightNumber + " not exists");

	}

	@Override
	public Flight viewFlight(int i) {
		// TODO Auto-generated method stub
		return null;
	}
}
