package com.engg.digitalorg.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GroupResponse {

    private int id;
    private String name;
    private String description;
    private String tribe;
    private String team;
    private String component;
    private Date created_date;
    private Date updated_date;
    private String created_by;
    private String updated_by;
    private Boolean active;
    private Boolean hasAdmin;
}
