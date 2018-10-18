/**
 * 
 */
package com.hubspot.microservice.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Vikas Siva Ravindran
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Partner {
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String country;
	
	public Partner() {
		super();
		// TODO Auto-generated constructor stub
	}

	private List<LocalDate> availableDates;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getlastName() {
		return lastName;
	}

	public void setlastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<LocalDate> getAvailableDates() {
		return availableDates;
	}

	public void setAvailableDates(List<LocalDate> availableDates) {
		this.availableDates = availableDates;
	}

	public Partner(String firstName, String lastName, String email, String country, List<LocalDate> availableDates) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.country = country;
		this.availableDates = availableDates;
	}

	@Override
	public String toString() {
		return "Partner [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", country="
				+ country + ", availableDates=" + availableDates + "]";
	}
	
	

}
