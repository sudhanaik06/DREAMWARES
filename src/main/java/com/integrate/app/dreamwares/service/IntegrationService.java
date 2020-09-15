package com.integrate.app.dreamwares.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.integrate.app.dreamwares.model.Contact;
import com.integrate.app.dreamwares.model.Subscriber;
import com.integrate.app.dreamwares.restcontroller.AccessException;
@Service
public class IntegrationService {

	private final static Logger logger = LoggerFactory.getLogger(IntegrationService.class);
	@Autowired
	private MailerLiteService mailerLiteService;
	@Autowired
	private SendFoxService sendFoxService;

	public void integrateMailerLiteAndSendFox() {
		try {
			Map<String, Subscriber> subscriberMap = convertToMap(mailerLiteService.getAllSubscribers());
			List<Contact> allContacts = sendFoxService.getAllContacts();

			Map<String, Contact> contactMap = new HashMap<>();
			allContacts.forEach(contact -> contactMap.put(contact.getEmail(), contact));

			contactMap.keySet().forEach(email -> subscriberMap.remove(email));

			subscriberMap.values()
			.forEach(subscriber -> sendFoxService.createContact(Contact.builder()
					.email(subscriber.getEmail())
					.firstName(subscriber.getName())
					.build()));
		} catch (HttpClientErrorException ex) {

			logger.error("Unable to process integration due to client exception", ex);
			if(ex instanceof HttpClientErrorException.Unauthorized){
				throw new AccessException("Unauthorized User",ex);}
			else if(ex instanceof HttpClientErrorException.BadRequest){
				throw new Exception("InvalidData", ex);
			}
			throw ex;
		} catch (Exception ex) {
			logger.error("unable to process", ex);
		}
	}

	private Map<String, Subscriber> convertToMap(List<Subscriber> allSubscribers) {
		Map map = new HashMap();
		for (Subscriber subscriber : allSubscribers) {
			map.put(subscriber.getEmail(), subscriber);
		}
		return map;
	}
}
