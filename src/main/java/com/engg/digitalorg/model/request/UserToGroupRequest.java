package com.engg.digitalorg.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserToGroupRequest {

    private String userEmail;
    private String adminEmail;
    private int groupId;
}
