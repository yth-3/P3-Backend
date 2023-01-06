package com.revature.P3.entities;

import javax.persistence.*;

@Entity
@Table(name = "claims")
public class Claim {
    @Id
    private String claimId;
}
