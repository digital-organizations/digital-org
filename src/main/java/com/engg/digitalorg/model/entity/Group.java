package com.engg.digitalorg.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * The type Group.
 */
@Getter
@Setter
@Entity
@Table(name = "group", schema = "digital")

public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String name;
    private String description;
    private String tribe;
    private String team;
    private String component;
    private String created_by;
    private String updated_by;
    private Date created_date;
    private Date updated_date;
    private Boolean active;
}
