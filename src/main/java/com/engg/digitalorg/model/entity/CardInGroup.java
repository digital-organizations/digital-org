package com.engg.digitalorg.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="card-in-group", schema = "org")
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
