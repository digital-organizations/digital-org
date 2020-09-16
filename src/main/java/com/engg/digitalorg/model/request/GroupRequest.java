package com.engg.digitalorg.model.request;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Group request.
 */
@Getter
@Setter
public class GroupRequest {

    private String name;
    private String description;
    private String tribe;
    private String team;
    private String component;
    private String created_by;
    private String updated_by;
}
