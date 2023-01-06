package com.revature.P3.entities;

import javax.persistence.*;

@Entity
@Table(name = "claim_types")
public class ClaimType {
    @Id
    private String typeId;

    @Column(name = "type")
    private String type;
}
