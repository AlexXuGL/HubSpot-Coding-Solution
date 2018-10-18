/**
 * 
 */
package com.hubspot.microservice.service;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hubspot.microservice.model.Countries;
import com.hubspot.microservice.model.Country;
import com.hubspot.microservice.model.Partner;
import com.hubspot.microservice.model.Partners;

/**
 * @author Vikas Siva Ravindran
 *
 */
@RestController
@RequestMapping("/hubspot")
public class InvitationService {

	private RestTemplate restTemplate = new RestTemplate();
	private final String getUrl = "https://candidate.hubteam.com/candidateTest/v3/problem/dataset?userKey=81f395bc9ac3c8ff5d53d518000f";
	private final String postUrl = "https://candidate.hubteam.com/candidateTest/v3/problem/result?userKey=81f395bc9ac3c8ff5d53d518000f";
	private static final Logger logger = LoggerFactory.getLogger(InvitationService.class);
	List<LocalDate> inviteDates = null;
	List<String> emails = null;
	List<LocalDate> partnerDates = null;
	List<Country> presentCountries = null;
	int attendeeCount = 0;

	@GetMapping(value = "/invite")
	public void getInviteList() {

		ResponseEntity<Partners> response = restTemplate.getForEntity(getUrl, Partners.class);
		Map<String, List<Partner>> results = new HashMap<>();

		List<LocalDate> dates = null;

		Countries countriesList = new Countries();
		List<Country> finalAttendeesList = new ArrayList<>();

		List<Partner> partnersList = response.getBody().getPartners();

		/* Java Lamba expression to sort Countries -> Partners List */
		for (Partner p : partnersList) {
			results.computeIfAbsent(p.getCountry(), k -> new ArrayList<>()).add(p);
		}
		Map<String, List<LocalDate>> userAvailableDates = null;
		Set<String> countries = results.keySet();
		Iterator<String> listCounties = countries.iterator();

		while (listCounties.hasNext()) {
			presentCountries = new ArrayList<>();
			dates = new ArrayList<>();
			String country = listCounties.next();
			List<Partner> partnerDetails = results.get(country);
			userAvailableDates = new HashMap<>();

			for (Partner p : partnerDetails) {
				userAvailableDates.put(p.getEmail(), p.getAvailableDates());
				dates.addAll(p.getAvailableDates());
			}

			partnerDates = new ArrayList<>();
			Collections.sort(dates);
			partnerDates.addAll(dates.stream().distinct().collect(Collectors.toList()));
			Collections.sort(dates);
			
			/* Function to  finalise the Countries based on the date Logic*/
			calculateCountries(partnerDates, partnerDetails, country);
			/*
			 * Java Lambda Expression to get the Countries with maximum
			 * AttendeeCount
			 */
			Country c = presentCountries.stream().max((s1, s2) -> s1.getAttendeeCount() - s2.getAttendeeCount()).get();
			logger.info("Adding the Country to the Final List::{}", c);
			finalAttendeesList.add(c);

		}
		countriesList.setCountries(finalAttendeesList);
		/* RestTemplate Post to send the List of Invites*/
		restTemplate.postForEntity(postUrl, countriesList, Countries.class);
	}

	
	
	public void calculateCountries(List<LocalDate> partnerDates, List<Partner> partnerDetails, String country) {

		
		/* Comparing the List of Dates present in a specific country*/
		for (int j = 0; j < partnerDates.size() - 1; j++) {
			LocalDate d1 = partnerDates.get(j);
			LocalDate d2 = partnerDates.get(j + 1);
			inviteDates = new ArrayList<>();

			/* Java program to sort Dates whether they appear in order */
			if (d1.plusDays(1).equals(d2)) {
				emails = new ArrayList<>();
				attendeeCount = 0;
				inviteDates = Arrays.asList(d1, d2);
				Iterator<Partner> prts = partnerDetails.listIterator();
				while (prts.hasNext()) {
					Partner p1 = prts.next();
					if (p1.getAvailableDates().containsAll(inviteDates)) {
						emails.add(p1.getEmail());
						attendeeCount++;
					}
				}
				/* Creating Country Object*/
				Country c = new Country();

				c.setAttendeeCount(attendeeCount);
				c.setName(country);
				/* Check for attendee Count or else return null*/
				LocalDate startDate = c.getAttendeeCount().intValue() > 0 ? d1 : null;
				if (startDate != null) {
					String s = startDate.format(DateTimeFormatter.ISO_DATE);
					c.setStartDate(s);
				} else {
					c.setStartDate(null);
				}
				c.setAttendees(emails);
				logger.info("The Country is:{}", c);
				presentCountries.add(c);
			}

		}

	}

}
