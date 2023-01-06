package com.revature.P3.entities;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    private String userId;
}
