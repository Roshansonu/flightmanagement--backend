package com.capg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.dao.PassengerDao;
import com.capg.exception.PassengerNotFoundException;
import com.capg.model.Passenger;

@Service
public class PassengerServiceImpl implements PassengerService {

	@Autowired
	private PassengerDao passengerrepo;

	@Override
	public List<Passenger> getAllPasenger() {
		List<Passenger> h = (List<Passenger>) passengerrepo.findAll();
		return h;
	}

	@Override
	public Passenger AddPassenger(Passenger p) {
		return passengerrepo.save(p);
	}

	@Override
	public Passenger updatePassengerBypnr(Long prn, Passenger passenger) {
		Passenger searchprn = passengerrepo.findById(prn)
				.orElseThrow(() -> new PassengerNotFoundException("passenger not found" + prn));
		searchprn.setAge(passenger.getAge());
		searchprn.setLuggage(passenger.getLuggage());
		searchprn.setPassengerName(passenger.getPassengerName());
		searchprn.setUin(passenger.getUin());
		return passengerrepo.save(searchprn);

	}

	@Override
	public String Deletepasenger(Long prn) {
		passengerrepo.deleteById(prn);
			return "User is deleted";
		}
	}

