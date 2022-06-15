package com.capg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.model.Passenger;
import com.capg.service.PassengerServiceImpl;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"},allowedHeaders = "*")
public class PassengerController {

	@Autowired
	private PassengerServiceImpl passengerservice;

	@GetMapping("/getAll")
	public List<Passenger> getAll() {
		return passengerservice.getAllPasenger();
	}

	
	@PostMapping("/addpassenger")
	public Passenger addPassenger(@RequestBody Passenger p) {
		return passengerservice.AddPassenger(p);
	}

	@PutMapping("/updatepasssenger/{pnr}")
	public Passenger updatepassenger(@PathVariable("pnr") Long pnr,@RequestBody Passenger passenger) {
		return passengerservice.updatePassengerBypnr(pnr, passenger);
		
	}
	
	@DeleteMapping("/deletepassenger/{pnru}")
	public String deletePassenger( @PathVariable("pnru") Long pnr) {
		return passengerservice.Deletepasenger(pnr);
	}
	
	
}
