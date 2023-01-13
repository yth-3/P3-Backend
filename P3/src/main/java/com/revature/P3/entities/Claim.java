package com.revature.P3.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;

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
    private User submitter;

    @Column(name = "submitted", nullable = false)
    private Timestamp submitted;

    @Column(name = "claimed", nullable = false)
    private Double claimed;

    @ManyToOne
    @JoinColumn(
            name = "type_id",
            nullable = false
    )
    private ClaimType type;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "receipt")
    private byte[] receipt;

    @ManyToOne
    @JoinColumn(
            name = "resolver_id"
    )
    private User resolver;

    @Column(name = "resolved")
    private Timestamp resolved;

    @Column(name = "settled", nullable = false)
    private Double settled;

    @ManyToOne
    @JoinColumn(
            name = "status_id",
            nullable = false
    )
    private ClaimStatus status;

    public Claim() {
    }

    public Claim(String claimId, User submitter, Timestamp submitted, Double claimed, ClaimType type, String description, byte[] receipt, User resolver, Timestamp resolved, Double settled, ClaimStatus status) {
        this.claimId = claimId;
        this.submitter = submitter;
        this.submitted = submitted;
        this.claimed = claimed;
        this.type = type;
        this.description = description;
        this.receipt = receipt;
        this.resolver = resolver;
        this.resolved = resolved;
        this.settled = settled;
        this.status = status;
    }

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }

    public User getSubmitter() {
        return submitter;
    }

    public void setSubmitter(User submitter) {
        this.submitter = submitter;
    }

    public Timestamp getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Timestamp submitted) {
        this.submitted = submitted;
    }

    public Double getClaimed() {
        return claimed;
    }

    public void setClaimed(Double claimed) {
        this.claimed = claimed;
    }

    public ClaimType getType() {
        return type;
    }

    public void setType(ClaimType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getReceipt() {
        return receipt;
    }

    public void setReceipt(byte[] receipt) {
        this.receipt = receipt;
    }

    public User getResolver() {
        return resolver;
    }

    public void setResolver(User resolver) {
        this.resolver = resolver;
    }

    public Timestamp getResolved() {
        return resolved;
    }

    public void setResolved(Timestamp resolved) {
        this.resolved = resolved;
    }

    public Double getSettled() {
        return settled;
    }

    public void setSettled(Double settled) {
        this.settled = settled;
    }

    public ClaimStatus getStatus() {
        return status;
    }

    public void setStatus(ClaimStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Claim{" +
                "claimId='" + claimId + '\'' +
                ", submitter=" + submitter +
                ", submitted=" + submitted +
                ", claimed=" + claimed +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", receipt=" + Arrays.toString(receipt) +
                ", resolver=" + resolver +
                ", resolved=" + resolved +
                ", settled=" + settled +
                ", status=" + status +
                '}';
    }
}
