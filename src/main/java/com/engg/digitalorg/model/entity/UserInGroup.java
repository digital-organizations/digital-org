package com.engg.digitalorg.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * The type User in group.
 */
@Getter
@Setter
@Entity
@Table(name = "user_in_group", schema = "digital")
public class UserInGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String email;
    private int group_id;
    private Date added_date;
    private String added_by;

    /**
     * Instantiates a new User in group.
     */
    public UserInGroup() {

    }

    /**
     * Instantiates a new User in group.
     *
     * @param user     the user
     * @param group_id the group id
     * @param admin    the admin
     */
    public UserInGroup(String user, int group_id, String admin) {
        super();
        this.email = user;
        this.group_id = group_id;
        this.added_by = admin;
    }


}
