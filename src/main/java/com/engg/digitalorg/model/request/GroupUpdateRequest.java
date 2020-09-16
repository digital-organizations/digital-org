package com.engg.digitalorg.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupUpdateRequest {

    private int id;
    private String name;
    private String description;
    private String tribe;
    private String team;
    private String component;
    private String updated_by;
}
