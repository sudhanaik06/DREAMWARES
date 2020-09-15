package com.integrate.app.dreamwares.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integrate.app.dreamwares.model.Contact;
import com.integrate.app.dreamwares.model.Subscriber;
import com.integrate.app.dreamwares.service.MailerLiteService;
import com.integrate.app.dreamwares.service.SendFoxService;

@RestController
@RequestMapping("/integrate")
public class IntegrateMailerAndSendFoxController {

	 @Autowired
	    private MailerLiteService mailerLiteService	;
	    @Autowired
	    private SendFoxService sendFoxService;

	    @GetMapping
	    public void integrate () {
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
	    }

	    private Map<String, Subscriber> convertToMap (List<Subscriber> allSubscribers) {
	        Map map = new HashMap();
	        for (Subscriber subscriber : allSubscribers) {
	            map.put(subscriber.getEmail(), subscriber);
	        }
	        return map;
	    }

	    @GetMapping("/mailerlite")
	    public List<Subscriber> getAllSubscribers () {
	        return mailerLiteService.getAllSubscribers();
	    }

	    @GetMapping("/sendfox")
	    public List<Contact> getAllContacts () {
	        return sendFoxService.getAllContacts();
	    }

}
