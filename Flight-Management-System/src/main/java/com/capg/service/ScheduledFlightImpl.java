package com.capg.service;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.dao.ScheduleDao;
import com.capg.dao.ScheduledFlightDao;
import com.capg.exception.RecordNotFoundException;
import com.capg.model.Schedule;
import com.capg.model.ScheduledFlight;

import exceptions.ScheduledFlightNotFoundException;

@Service
public class ScheduledFlightImpl implements ScheduledFlightService{

	@Autowired
	ScheduledFlightDao dao;
	
	@Autowired
	ScheduleDao scheduleDao;

	private Object flightId;
	
	@Override
	public ScheduledFlight addScheduledFlight(ScheduledFlight scheduledFlight) {
		
		 ScheduledFlight saveInfo = dao.save(scheduledFlight);
		 
		 return saveInfo;
		
	
}
	/*
	 * public ScheduledFlight addScheduledFlight(ScheduledFlight scheduledFlight) {
	 * // TODO Auto-generated method stub return dao.save(scheduledFlight); }
	 */

	@Override
	public ScheduledFlight modifyScheduledFlight(ScheduledFlight scheduledFlight) {
		ScheduledFlight updateScheduleFlight = dao.findById(scheduledFlight.getScheduleFlightid()).get();
		Schedule updateSchedule = scheduleDao.findById(scheduledFlight.getSchedule().getScheduleid()).get();
		updateScheduleFlight.setAvailableseats(scheduledFlight.getAvailableseats());
		updateSchedule.setSrcAirport(scheduledFlight.getSchedule().getSrcAirport());
		updateSchedule.setDstnAirport(scheduledFlight.getSchedule().getDstnAirport());
		updateSchedule.setArrDateTime(scheduledFlight.getSchedule().getArrDateTime());
		updateSchedule.setDeptDateTime(scheduledFlight.getSchedule().getDeptDateTime());
		dao.save(updateScheduleFlight);
		return scheduledFlight;
	}

	public String removeScheduledFlight1(Integer Flightid) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		if(flightId == null)
			throw new RecordNotFoundException("Enter flight Id");
		Optional<ScheduledFlight>scheduleFlight = dao.findById(Flightid);
		if(scheduleFlight.isPresent())
			throw new RecordNotFoundException("Enter a valid Flight Id");
		else {
			dao.deleteById(Flightid);
		}
		return  "Scheduled flight with ID " + flightId + " is not found";
	}

	@Override
	public Iterable<ScheduledFlight> viewAllScheduledFlights() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public ScheduledFlight viewScheduledFlight(BigInteger id) throws ScheduledFlightNotFoundException {
		if (flightId == null)
			throw new ScheduledFlightNotFoundException("Enter flight Id");
		Optional<ScheduledFlight> scheduleFlight = dao.findById((Integer) flightId);
		if (!scheduleFlight.isPresent())
			throw new ScheduledFlightNotFoundException("Enter a valid Flight Id");
		else
			return scheduleFlight.get();
	}

	@Override
	public String removeScheduledFlight(BigInteger flightId) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
