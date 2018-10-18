/**
 * 
 */
package com.hubspot.microservice.model;

import java.util.List;

/**
 * @author Vikas Siva Ravindran
 *
 */
public class Country {

	private Integer attendeeCount;
	
	private List<String> attendees = null;
	
	private String name;
	
	private String startDate;

	public Integer getAttendeeCount() {
		return attendeeCount;
	}

	public void setAttendeeCount(Integer attendeeCount) {
		this.attendeeCount = attendeeCount;
	}

	public List<String> getAttendees() {
		return attendees;
	}

	public void setAttendees(List<String> attendees) {
		this.attendees = attendees;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	@Override
	public String toString() {
		return "Country [attendeeCount=" + attendeeCount + ", attendees=" + attendees + ", name=" + name
				+ ", startDate=" + startDate + "]";
	}

	public Country(Integer attendeeCount, List<String> attendees, String name, String startDate) {
		super();
		this.attendeeCount = attendeeCount;
		this.attendees = attendees;
		this.name = name;
		this.startDate = startDate;
	}

	public Country() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
