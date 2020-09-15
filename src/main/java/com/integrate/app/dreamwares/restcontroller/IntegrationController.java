package com.integrate.app.dreamwares.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integrate.app.dreamwares.service.IntegrationService;

@RestController
@RequestMapping("/integrate")
public class IntegrationController {

	@Autowired
    private IntegrationService integrationService;

    @GetMapping
    public void integrate() {
        integrationService.integrateMailerLiteAndSendFox();
    }
}
