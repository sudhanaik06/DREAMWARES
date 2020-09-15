package com.integrate.app.dreamwares.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientException;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class AccessException extends RestClientException {

    private String msg;

    public AccessException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public AccessException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
