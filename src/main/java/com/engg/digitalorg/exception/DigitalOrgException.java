package com.engg.digitalorg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class DigitalOrgException extends RuntimeException  {

    public DigitalOrgException() {

    }

    public DigitalOrgException(String message) {
        super(message);
    }

    public DigitalOrgException(String message, Throwable cause) {
        super(message, cause);
    }

    public DigitalOrgException(Throwable cause) {
        super(cause);
    }

    public DigitalOrgException(String message, Throwable cause, boolean enableSupression, boolean writableStackTrace) {
        super(message, cause, enableSupression, writableStackTrace);
    }
}
