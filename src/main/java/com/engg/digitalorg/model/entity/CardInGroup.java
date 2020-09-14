package com.engg.digitalorg.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="card-in-group", schema = "digital")
public class CardInGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int card_id;
    private int group_id;
    private Date added_date;
    private String added_by;

    public CardInGroup() {

    }


}
