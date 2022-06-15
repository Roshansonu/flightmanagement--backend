package com.capg.model;


import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data @Table
public class Passenger {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	
	private Long pnrNo;
	
	private String passengerName;
	private int age;
	private Long uin;
	private double luggage;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_pnrNo",referencedColumnName = "pnrNo")
	private List<Booking>booking;

	public Long getPnrNo() {
		return pnrNo;
	}

	public void setPnrNo(Long pnrNo) {
		this.pnrNo = pnrNo;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Long getUin() {
		return uin;
	}

	public void setUin(Long uin) {
		this.uin = uin;
	}

	public double getLuggage() {
		return luggage;
	}

	public void setLuggage(double luggage) {
		this.luggage = luggage;
	}

	public Passenger() {
		super();
	}

	public Passenger(Long pnrNo, String passengerNmae, int age, Long uin, double luggage) {
		super();
		this.pnrNo = pnrNo;
		this.passengerName = passengerNmae;
		this.age = age;
		this.uin = uin;
		this.luggage = luggage;
	}

	@Override
	public String toString() {
		return "Passenger [pnrNo=" + pnrNo + ", passengerName=" + passengerName + ", age=" + age + ", uin=" + uin
				+ ", luggage=" + luggage + "]";
	}

}
