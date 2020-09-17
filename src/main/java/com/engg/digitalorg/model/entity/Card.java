package com.engg.digitalorg.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * The type Card.
 */
@Getter
@Setter
@Entity
@Table(name = "card", schema = "digital")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    @NotNull
    private String title;
    private String description;
    private String tribe;
    private String team;
    private String component;
    @NotNull
    private String created_by;
    @NotNull
    private String updated_by;
    @NotNull
    private Date created_date;
    @NotNull
    private Date updated_date;
    @NotNull
    private Boolean active;
    private int url_id;
    private int icon_id;
}
