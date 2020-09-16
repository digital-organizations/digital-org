package com.engg.digitalorg.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="user_in_group", schema = "digital")
public class UserInGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String email;
    private int group_id;
    private Date added_date;
    private String added_by;

    public UserInGroup() {

    }

    public UserInGroup(String user, int group_id, String admin) {
        super();
        this.email = user;
        this.group_id = group_id;
        this.added_by = admin;
    }


}
