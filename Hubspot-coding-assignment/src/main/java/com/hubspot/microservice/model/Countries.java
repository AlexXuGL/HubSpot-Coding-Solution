/**
 * 
 */
package com.hubspot.microservice.model;

import java.util.List;

/**
 * @author Vikas Siva Ravindran
 *
 */
public class Countries {
	
	private List<Country> countries;

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	public Countries() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Countries(List<Country> countries) {
		super();
		this.countries = null;
	}
	
	

}
