package com.integrate.app.dreamwares.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import org.springframework.beans.factory.annotation.Value;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.integrate.app.dreamwares.model.Contact;
import com.integrate.app.dreamwares.model.Data;


@Service
public class SendFoxService {

	 private static Logger logger = LoggerFactory.getLogger(SendFoxService.class);
	    @Autowired
	    private RestTemplate restTemplate;
	    private static final String BASE_URL = "https://api.sendfox.com/";

	    @Value("${external.sendfox.api.token}")
	    private String token;

	    public List<Contact> getAllContacts() throws HttpClientErrorException {

	        ResponseEntity<String> response = this.restTemplate.exchange(BASE_URL + "/contacts",
	                HttpMethod.GET,
	                new HttpEntity<>(getCustomHeader()),
	                String.class);
	        try {
	            Data data = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
	                    .readValue(response.getBody(), Data.class);
	            List<Contact> contacts = data.getData();
	            return contacts;
	        } catch (JsonProcessingException ex) {
	            logger.error("Unable to process mapping", ex);
	        }
	        return Collections.emptyList();
	    }

	    public Contact createContact(Contact contact) {
	        ResponseEntity<Contact> response = this.restTemplate.exchange(BASE_URL + "/contacts",
	                HttpMethod.POST,
	                new HttpEntity<>(contact, getCustomHeader()),
	                Contact.class);
	        return response.getBody();
	    }

	    public HttpHeaders getCustomHeader() {
	        HttpHeaders headers = new HttpHeaders();
	        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
	        headers.add(HttpHeaders.AUTHORIZATION, token);
	        return headers;
	    }
}
