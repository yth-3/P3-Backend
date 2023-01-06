package com.revature.P3.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "claims")
public class Claim {
    @Id
    private String claimId;

    @ManyToOne
    @JoinColumn(
            name = "submitter_id",
            nullable = false
    )
    @JsonBackReference
    private User submitterId;

    @Column(name = "submitted", nullable = false)
    private Timestamp submitted;

    @Column(name = "claimed", nullable = false)
    private Double claimed;

    @ManyToOne
    @JoinColumn(
            name = "type_id",
            nullable = false
    )
    @JsonBackReference
    private ClaimType typeId;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "receipt")
    private byte[] receipt;

    @ManyToOne
    @JoinColumn(
            name = "resolver_id"
    )
    @JsonBackReference
    private User resolverId;

    @Column(name = "resolved")
    private Timestamp resolved;

    @Column(name = "settled", nullable = false)
    private Double settled;

    @ManyToOne
    @JoinColumn(
            name = "status_id",
            nullable = false
    )
    @JsonBackReference
    private ClaimStatus statusId;
}
