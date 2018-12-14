package com.demographic.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "PERSON")
public class Person {

	@Id
	@Column(name = "pps_number", nullable = false)
	private String ppsNumber;

	@NotNull
	@Size(max = 25)
	private String name;

	@Temporal(TemporalType.DATE)
	@Column(name = "Date_Of_Birth", nullable = false)
	private Date dob;

	private String phoneNumber;
	
	public Person()
	{}
	

	public Person(String ppsNumber, String name, Date dob, String phoneNumber) {
		this.ppsNumber = ppsNumber;
		this.name = name;
		this.dob = dob;
		this.phoneNumber = phoneNumber;
	}

	public String getPpsNumber() {
		return ppsNumber;
	}

	public void setPpsNumber(String ppsNumber) {
		this.ppsNumber = ppsNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
