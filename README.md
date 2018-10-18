# HubSpot-Coding-Solution
Sample Java solution to one of the programming questions.

You’re provided with an API that gives you a list of partners, their countries, and which dates they’re available in ISO 8601 format.
Another team will send out the invitations, but you need to tell them when we should host the event and who should attend by POSTing to an API.

The date you send in for the country should be the starting date of the two day period where the most partners can make it for both days in a row. In case of multiple dates with the same number of partners, pick the earlier date. If there are no two days in a row when any partners can make it, return null.
API Docs and Example
To get the list of partners, send an HTTP GET to:

https://candidate.hubteam.com/candidateTest/v3/problem/dataset?userKey=81f395bc9ac3c8ff5d53d518000f

Here’s a sample response with 10 partners:

{
            "partners": [
          {
            "firstName": "Darin",
            "lastName": "Daignault",
            "email": "ddaignault@hubspotpartners.com",
            "country": "United States",
            "availableDates": [
            "2017-05-03",
            "2017-05-06"
            ]
          },
          {
            "firstName": "Crystal",
            "lastName": "Brenna",
            "email": "cbrenna@hubspotpartners.com",
            "country": "Ireland",
            "availableDates": [
            "2017-04-27",
            "2017-04-29",
            "2017-04-30"
            ]
          },
          {
            "firstName": "Janyce",
            "lastName": "Gustison",
            "email": "jgustison@hubspotpartners.com",
            "country": "Spain",
            "availableDates": [
            "2017-04-29",
            "2017-04-30",
            "2017-05-01"
            ]
          },
          {
            "firstName": "Tifany",
            "lastName": "Mozie",
            "email": "tmozie@hubspotpartners.com",
            "country": "Spain",
            "availableDates": [
            "2017-04-28",
            "2017-04-29",
            "2017-05-01",
            "2017-05-04"
            ]
          },
          {
            "firstName": "Temple",
            "lastName": "Affelt",
            "email": "taffelt@hubspotpartners.com",
            "country": "Spain",
            "availableDates": [
            "2017-04-28",
            "2017-04-29",
            "2017-05-02",
            "2017-05-04"
            ]
          },
          {
            "firstName": "Robyn",
            "lastName": "Yarwood",
            "email": "ryarwood@hubspotpartners.com",
            "country": "Spain",
            "availableDates": [
            "2017-04-29",
            "2017-04-30",
            "2017-05-02",
            "2017-05-03"
            ]
          },
          {
            "firstName": "Shirlene",
            "lastName": "Filipponi",
            "email": "sfilipponi@hubspotpartners.com",
            "country": "Spain",
            "availableDates": [
            "2017-04-30",
            "2017-05-01"
            ]
          },
          {
            "firstName": "Oliver",
            "lastName": "Majica",
            "email": "omajica@hubspotpartners.com",
            "country": "Spain",
            "availableDates": [
            "2017-04-28",
            "2017-04-29",
            "2017-05-01",
            "2017-05-03"
            ]
          },
          {
            "firstName": "Wilber",
            "lastName": "Zartman",
            "email": "wzartman@hubspotpartners.com",
            "country": "Spain",
            "availableDates": [
            "2017-04-29",
            "2017-04-30",
            "2017-05-02",
            "2017-05-03"
            ]
          },
          {
            "firstName": "Eugena",
            "lastName": "Auther",
            "email": "eauther@hubspotpartners.com",
            "country": "United States",
            "availableDates": [
            "2017-05-04",
            "2017-05-09"
            ]
          }
            ]
          }
POST a JSON body to:

https://candidate.hubteam.com/candidateTest/v3/problem/result?userKey=81f395bc9ac3c8ff5d53d518000f

For the list of partners above, the proper API response to send would look like this:

{
            "countries": [
          {
            "attendeeCount": 1,
            "attendees": [
            "cbrenna@hubspotpartners.com"
            ],
            "name": "Ireland",
            "startDate": "2017-04-29"
          },
          {
            "attendeeCount": 0,
            "attendees": [],
            "name": "United States",
            "startDate": null
          },
          {
            "attendeeCount": 3,
            "attendees": [
            "omajica@hubspotpartners.com",
            "taffelt@hubspotpartners.com",
            "tmozie@hubspotpartners.com"
            ],
            "name": "Spain",
            "startDate": "2017-04-28"
          }
            ]
          }
