package com.engg.digitalorg.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * The type Url.
 */
@Getter
@Setter
@Entity
@Table(name = "url", schema = "digital")
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String long_url;
    private Date created_date;
    private int card_id;
    private String short_url;
    private Date expires_date;
}
