package com.demographic.formbean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PersonForm {
	
	
    private String ppsNumber;
    
    
    private String name;
    private String phoneNumber;
    private String dob;
    
    
    public PersonForm(String name, String ppsNumber, 
    		String dob, String phoneNumber) {
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}
 
    public PersonForm() {
 
    }
 
    

}
