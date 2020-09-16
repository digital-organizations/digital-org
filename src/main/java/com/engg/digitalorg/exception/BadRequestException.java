package com.engg.digitalorg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Bad request exception.
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class BadRequestException extends RuntimeException {

    /**
     * Instantiates a new Bad request exception.
     */
    public BadRequestException() {

    }

    /**
     * Instantiates a new Bad request exception.
     *
     * @param message the message
     */
    public BadRequestException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Bad request exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Bad request exception.
     *
     * @param cause the cause
     */
    public BadRequestException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Bad request exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSupression   the enable supression
     * @param writableStackTrace the writable stack trace
     */
    public BadRequestException(String message, Throwable cause, boolean enableSupression, boolean writableStackTrace) {
        super(message, cause, enableSupression, writableStackTrace);
    }
}
