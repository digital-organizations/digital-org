package com.engg.digitalorg.model.request;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UrlLongRequest {

    private String longUrl;
    private Date expiresDate;

}
