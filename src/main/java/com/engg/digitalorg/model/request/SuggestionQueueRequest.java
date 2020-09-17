package com.engg.digitalorg.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * The type Suggestion queue request.
 */
@Getter
@Setter
public class SuggestionQueueRequest {
    private int card_id;
    private String email;
    private Date suggested_date;

}
