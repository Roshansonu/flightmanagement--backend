package com.capg.controller;



import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.capg.exception.RecordNotFoundException;
import com.capg.model.Schedule;
import com.capg.model.ScheduledFlight;
import com.capg.service.AirportService;
import com.capg.service.FlightService;
import com.capg.service.ScheduledFlightService;

import exceptions.ScheduledFlightNotFoundException;

@RestController
@RequestMapping("/scheduledFlight")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ScheduledFlightController {

	@Autowired
	ScheduledFlightService scheduleFlightService;
	
	@Autowired
	AirportService airportService;
	
	@Autowired
	FlightService flightService;
	
	@PostMapping("/add")
	public ResponseEntity<ScheduledFlight> add(@ModelAttribute ScheduledFlight scheduledFlight,@RequestParam(name = "srcAirport") String source, @RequestParam(name = "dstnAirport") String destination, @RequestParam(name = "deptDateTime") String dipartureTime, @RequestParam(name = "arrDateTime") String arrivalTime){
		Schedule schedule = new Schedule();
		schedule.setScheduleid(scheduledFlight.getScheduleFlightid());
		
		try
		{
			schedule.setSrcAirport(airportService.viewAirport(source));
		}
		catch (RecordNotFoundException e) {
			return new ResponseEntity("Airport Not Found", HttpStatus.BAD_REQUEST);
		}
		try
		{
			schedule.setDstnAirport(airportService.viewAirport(destination));
		}
		catch (RecordNotFoundException e) {
			// TODO: handle exception
			return new ResponseEntity("Airport Not Found", HttpStatus.BAD_REQUEST);
		}
		
		schedule.setArrDateTime(arrivalTime);
		schedule.setDeptDateTime(dipartureTime);
		try {
			scheduledFlight.setFlight(flightService.viewFlight(scheduledFlight.getScheduleFlightid()));
		} catch (RecordNotFoundException e1) {
			// TODO: handle exception
			return new ResponseEntity("Flight Not Found", HttpStatus.BAD_REQUEST);
		}
		
		scheduledFlight.setAvailableseats(scheduledFlight.getFlight().getSeatCapacity());
		scheduledFlight.setSchedule(schedule);
		try {
			return new ResponseEntity<ScheduledFlight>(scheduleFlightService.addScheduledFlight(scheduledFlight),HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity("Error adding Flight." + e, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PutMapping("/modify")
	public ResponseEntity<ScheduledFlight> modifyScheduleFlight(@ModelAttribute ScheduledFlight scheduleFlight) {
		ScheduledFlight modifySFlight = scheduleFlightService.modifyScheduledFlight(scheduleFlight);
		if (modifySFlight == null) {
			return new ResponseEntity("Flight not modified", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<ScheduledFlight>(modifySFlight, HttpStatus.OK);
		}

	}

	
	@DeleteMapping("/delete")
	public String deleteSF(@RequestParam BigInteger flightId) throws RecordNotFoundException {
		return scheduleFlightService.removeScheduledFlight(flightId);
	}

	
	@GetMapping("/search")
	@ExceptionHandler(ScheduledFlightNotFoundException.class)
	public ResponseEntity<ScheduledFlight> viewSF(@RequestParam BigInteger flightId) throws ScheduledFlightNotFoundException {
		ScheduledFlight searchSFlight = scheduleFlightService.viewScheduledFlight(flightId);
		if (searchSFlight == null) {
			return new ResponseEntity("Flight not present", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<ScheduledFlight>(searchSFlight, HttpStatus.OK);
		}
	}

	
	@GetMapping("/viewAll")
	public Iterable<ScheduledFlight> viewAllSF() {
		return scheduleFlightService.viewAllScheduledFlights();
	}
	
}