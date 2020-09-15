package com.integrate.app.dreamwares.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integrate.app.dreamwares.model.Subscriber;
import com.integrate.app.dreamwares.service.MailerLiteService;

@RestController
public class MailerLiteController {
	 @Autowired
	    private MailerLiteService mailerLiteService;

	    @GetMapping("/mailerlite")
	    public ResponseEntity<List<Subscriber>> getAllSubscribers() {
	        return ResponseEntity.ok(mailerLiteService.getAllSubscribers());
	    }
}
