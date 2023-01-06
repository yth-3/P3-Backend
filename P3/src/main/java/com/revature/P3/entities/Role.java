package com.revature.P3.entities;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class Role {
    @Id
    private String roleId;

    @Column(name = "role", nullable = false)
    private String role;
}
