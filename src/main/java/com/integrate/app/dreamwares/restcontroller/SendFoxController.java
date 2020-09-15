package com.integrate.app.dreamwares.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integrate.app.dreamwares.model.Contact;
import com.integrate.app.dreamwares.service.SendFoxService;

@RestController
public class SendFoxController {

	@Autowired
    private SendFoxService sendFoxService;

    @GetMapping("/sendfox")
    public ResponseEntity<List<Contact>> getAllContacts() {
        return ResponseEntity.ok(sendFoxService.getAllContacts());
    }
}
