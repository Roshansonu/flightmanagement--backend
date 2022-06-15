package com.capg.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToOne;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
public class ScheduledFlight {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name =  "schedule_flight_id")
	private int scheduleFlightid;
	
	@Column(name="available_seats")
	private int availableseats;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Schedule schedule;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Flight flight;
	
	public ScheduledFlight() {
		
	}

	
	public int getScheduleFlightid() {
		return scheduleFlightid;
	}

	public void setScheduleFlightid(int scheduleFlightid) {
		this.scheduleFlightid = scheduleFlightid;
	}

	

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	
	
}
