package com.engg.digitalorg.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CardUpdateRequest {
    private int id;
    private String title;
    private String description;
    private String original_url;
    private Date expire_date;
    private String tribe;
    private String team;
    private String component;
    private String updated_by;
    private int group_id;
}
