package com.integrate.app.dreamwares.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.integrate.app.dreamwares.model.Subscriber;

@Service
public class MailerLiteService {

	private static final Logger logger = LoggerFactory.getLogger(MailerLiteService.class);
    @Autowired
    private RestTemplate restTemplate;

    private static final String BASE_URL = "https://api.mailerlite.com/api/v2/";
    @Value("${external.mailerlite.api.headerName}")
    private String apiKeyHeaderName;
    @Value("${external.mailerlite.api.key}")
    private String apiKeyHeaderValue;

    public List<Subscriber> getAllSubscribers() throws HttpClientErrorException {

        ResponseEntity<List<Subscriber>> response = this.restTemplate.exchange(BASE_URL + "/subscribers",
                HttpMethod.GET,
                new HttpEntity<>(getCustomHeader()),
                new ParameterizedTypeReference<List<Subscriber>>() {
                });
        List<Subscriber> subscribers = response.getBody();
        return subscribers;

    }

    public HttpHeaders getCustomHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        headers.add(apiKeyHeaderName, apiKeyHeaderValue);
        return headers;
    }}
