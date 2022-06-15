package com.capg.model;

import java.math.BigInteger;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger bookingId;
	
	private String bookingDate;
	private int noOfPassengers;
	
	
	public Booking() {
		
	}


	public BigInteger getBookingId() {
		return bookingId;
	}


	public void setBookingId(BigInteger bookingId) {
		this.bookingId = bookingId;
	}


	public String getBookingDate() {
		return bookingDate;
	}


	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}


	public int getNoOfPassengers() {
		return noOfPassengers;
	}


	public void setNoOfPassengers(int noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}


	
}
