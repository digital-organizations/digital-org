package com.engg.digitalorg.model.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * The type Error message.
 */
@Getter
@Setter
@Slf4j
public class ErrorMessage implements Serializable {

    private String message;
    /**
     * The Object mapper.
     */
    static protected ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * Instantiates a new Error message.
     *
     * @param message the message
     */
    public ErrorMessage(String message) {
        this.message = message;
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException error) {
            log.error("Cannot convert to json", error);
        }
        return null;
    }
}
