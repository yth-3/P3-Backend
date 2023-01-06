package com.revature.P3.entities;

import javax.persistence.*;

@Entity
@Table(name = "claim_statuses")
public class ClaimStatus {
    @Id
    private String statusId;

    @Column(name = "status", nullable = false)
    private String status;
}
