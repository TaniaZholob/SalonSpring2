package com.tania.zholob.demo.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Users {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private Boolean enabled;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Column(name = "role_id")
    @Enumerated(EnumType.ORDINAL)
    private Roles role;

}
