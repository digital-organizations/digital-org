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
@Table(name = "suggestion_queue", schema = "digital")
public class SuggestionQueue {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private int card_id;
    private String email;
    private Date suggested_date;
    private String suggestion_text;

}
