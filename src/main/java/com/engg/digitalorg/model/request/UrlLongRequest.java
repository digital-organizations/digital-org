package com.engg.digitalorg.model.request;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * The type Url long request.
 */
@Getter
@Setter
public class UrlLongRequest {

    private String longUrl;
    private Date expiresDate;

}
