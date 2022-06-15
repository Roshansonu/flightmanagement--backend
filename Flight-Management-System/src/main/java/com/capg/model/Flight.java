package com.capg.model;

import java.math.BigInteger;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data @Table
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger flightNo;
	private String carrierName;
	private String flightModel;
	private int seatCapacity;

	

	public Flight() {
	}

	
	public Flight(BigInteger flightNo, String carrierName, String flightModel, int seatCapacity) {
		super();
		this.flightNo = flightNo;
		this.carrierName = carrierName;
		this.flightModel = flightModel;
		this.seatCapacity = seatCapacity;
	}

	
	public BigInteger getFlightNo() {
		return flightNo;
	}

	
	public void setFlightNo(BigInteger flightNo) {
		this.flightNo = flightNo;
	}

	
	public String getCarrierName() {
		return carrierName;
	}

	
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	
	public String getFlightModel() {
		return flightModel;
	}

	
	public void setFlightModel(String flightModel) {
		this.flightModel = flightModel;
	}

	
	public int getSeatCapacity() {
		return seatCapacity;
	}

	
	public void setSeatCapacity(int seatCapacity) {
		this.seatCapacity = seatCapacity;
	}

	@Override
	public String toString() {
		return "Flight [flightNo=" + flightNo + ",carrierName=" + carrierName + ",flightModel=" + flightModel
				+ ",seatCapacity=" + seatCapacity + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carrierName == null) ? 0 : carrierName.hashCode());
		result = prime * result + ((flightModel == null) ? 0 : flightModel.hashCode());
		result = prime * result + ((flightNo == null) ? 0 : flightNo.hashCode());
		result = prime * result + seatCapacity;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		if (carrierName == null) {
			if (other.carrierName != null)
				return false;
		} else if (!carrierName.equals(other.carrierName))
			return false;
		if (flightModel == null) {
			if (other.flightModel != null)
				return false;
		} else if (!flightModel.equals(other.flightModel))
			return false;
		if (flightNo == null) {
			if (other.flightNo != null)
				return false;
		} else if (!flightNo.equals(other.flightNo))
			return false;
		if (seatCapacity != other.seatCapacity)
			return false;
		return true;
	}
	
	@OneToOne(cascade = CascadeType.ALL)
	private ScheduledFlight scheduledFlight;

}
