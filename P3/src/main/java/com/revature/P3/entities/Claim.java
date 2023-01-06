package com.revature.P3.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "claims")
public class Claim {
    @Id
    private String claimId;

    @Column(name = "submitter_id")
    private String submitterId;

    @Column(name = "submitted")
    private Timestamp submitted;

    @Column(name = "claimed")
    private Double claimed;

    @Column(name = "type_id")
    private String typeId;

    @Column(name = "description")
    private String description;

    @Column(name = "receipt")
    private byte[] receipt;

    @Column(name = "resolver_id")
    private String resolverId;

    @Column(name = "resolved")
    private Timestamp resolved;

    @Column(name = "settled")
    private Double settled;

    @Column(name = "status_id")
    private String statusId;
}
