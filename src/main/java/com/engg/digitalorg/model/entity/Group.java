package com.engg.digitalorg.model.entity;

import com.engg.digitalorg.model.mapper.StringListConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="group", schema = "org")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String description;
    private Date created_date;
    private String created_by;
    private String tribe;
    private String team;
    private String component;
    private Date updated_date;
    private String updated_by;
    private Boolean active;

    private String admin;
}
