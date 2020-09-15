package com.integrate.app.dreamwares.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientException;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidDataException extends RestClientException {
	private String msg;

    public InvalidDataException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public InvalidDataException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
