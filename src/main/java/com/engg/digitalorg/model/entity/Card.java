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
@Table(name="card", schema = "org")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;
    private String description;
    private String original_url;
    private String short_url;
    private Date expire_date;
    private Date created_date;
    private String created_by;
    private String tribe;
    private String team;
    private String component;
    private Date updated_date;
    private String updated_by;
    private String group_name;
    private Boolean active;

//    @Embedded
//    private Group group;

}
