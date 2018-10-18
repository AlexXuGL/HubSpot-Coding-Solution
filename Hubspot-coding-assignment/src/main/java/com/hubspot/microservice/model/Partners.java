/**
 * 
 */
package com.hubspot.microservice.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Vikas Siva Ravindran
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Partners {

	public List<Partner> partners;

	public List<Partner> getPartners() {
		return partners;
	}

	public Partners(List<Partner> partners) {
		super();
		this.partners = partners;
	}

	public void setPartners(List<Partner> partners) {
		this.partners = partners;
	}
	
	public Partners(){
		partners = null;
	}

	@Override
	public String toString() {
		return "Partners [partners=" + partners + "]";
	}
	
	
}
