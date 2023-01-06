package com.revature.P3.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "claims")
public class Claim {
    @Id
    private String claimId;

    @Column(name = "submitter_id", nullable = false)
    private String submitterId;

    @Column(name = "submitted", nullable = false)
    private Timestamp submitted;

    @Column(name = "claimed", nullable = false)
    private Double claimed;

    @Column(name = "type_id", nullable = false)
    private String typeId;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "receipt")
    private byte[] receipt;

    @Column(name = "resolver_id")
    private String resolverId;

    @Column(name = "resolved")
    private Timestamp resolved;

    @Column(name = "settled", nullable = false)
    private Double settled;

    @Column(name = "status_id", nullable = false)
    private String statusId;
}
