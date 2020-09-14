package com.engg.digitalorg.model.response;

import com.engg.digitalorg.model.mapper.StringListConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Convert;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class GroupResponse {

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
    private Boolean hasAdmin;

    @Convert(converter = StringListConverter.class)
    private List<String> admin;
}
