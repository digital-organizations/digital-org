package com.engg.digitalorg.model.request;

import com.engg.digitalorg.model.mapper.StringListConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Convert;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class GroupRequest {

    private String name;
    private String description;
    private String created_by;
    private String tribe;
    private String team;
    private String component;
    private String updated_by;

    @Convert(converter = StringListConverter.class)
    private List<String> admin;
}
