package com.revature.P3.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
public class User {
    @Id
    private String userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "registered")
    private Timestamp registered;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "role_id")
    private String roleId;
}
