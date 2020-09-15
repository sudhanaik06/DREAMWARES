	package com.integrate.app.dreamwares;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import java.time.Duration;

@SpringBootApplication
public class DreamwaresApplication {

	public static void main(String[] args) {
		SpringApplication.run(DreamwaresApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate (RestTemplateBuilder builder) {

		return builder.setConnectTimeout(Duration.ofMillis(3000)).setReadTimeout(Duration.ofMillis(3000)).build();
	}

	@Bean
	public RestTemplate restTemplate () {

		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setConnectTimeout(3000);
		factory.setReadTimeout(3000);
		return new RestTemplate(factory);
	}
}
