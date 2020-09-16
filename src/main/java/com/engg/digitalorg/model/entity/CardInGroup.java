package com.engg.digitalorg.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * The type Card in group.
 */
@Getter
@Setter
@Entity
@Table(name = "card_in_group", schema = "digital")
public class CardInGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private int card_id;
    private int group_id;
    private Date added_date;
    private String added_by;

    /**
     * Instantiates a new Card in group.
     */
    public CardInGroup() {

    }


}
