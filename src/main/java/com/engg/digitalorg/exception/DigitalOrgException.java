package com.engg.digitalorg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Digital org exception.
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class DigitalOrgException extends RuntimeException {

    /**
     * Instantiates a new Digital org exception.
     */
    public DigitalOrgException() {

    }

    /**
     * Instantiates a new Digital org exception.
     *
     * @param message the message
     */
    public DigitalOrgException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Digital org exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public DigitalOrgException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Digital org exception.
     *
     * @param cause the cause
     */
    public DigitalOrgException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Digital org exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSupression   the enable supression
     * @param writableStackTrace the writable stack trace
     */
    public DigitalOrgException(String message, Throwable cause, boolean enableSupression, boolean writableStackTrace) {
        super(message, cause, enableSupression, writableStackTrace);
    }
}
