package com.engg.digitalorg.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * The type Card in group.
 */
@Getter
@Setter
public class CardInGroupRequest {

    private int card_id;
    private int group_id;
    private String added_by;

    /**
     * Instantiates a new Card in group.
     */
    public CardInGroupRequest() {

    }


}
