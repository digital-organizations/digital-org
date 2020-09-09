package com.engg.digitalorg.model;

import lombok.Getter;
import lombok.Setter;
import lombok.With;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="card", schema = "digital")
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
    private String suggested_by;
    private Date updated_date;
    private String updated_by;
    private String group_name;
    private Boolean authorize;

}
