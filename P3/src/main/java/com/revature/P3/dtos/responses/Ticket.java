package com.revature.P3.dtos.responses;

import com.revature.P3.entities.Claim;
import com.revature.P3.entities.ClaimStatus;
import com.revature.P3.entities.ClaimType;

import java.util.Arrays;

public class Ticket {
    private String claimId;
    private Principal submitterId;
    private String submitted;
    private double claimed;
    private ClaimType type;
    private String description;
    private byte[] receipt;
    private Principal resolverId;
    private String resolved;
    private double settled;
    private ClaimStatus status;

    public Ticket() {
        super();
    }

    public Ticket(Claim claim) {
        this.claimId = claim.getClaimId();
        this.submitterId = new Principal(claim.getSubmitter());
        this.submitted = claim.getSubmitted().toString();
        this.claimed = claim.getClaimed();
        this.type = claim.getType();
        this.description = claim.getDescription();
        this.receipt = claim.getReceipt();
        this.resolverId = claim.getResolver() == null ? null : new Principal(claim.getResolver());
        this.resolved = claim.getResolved() == null? null : claim.getResolved().toString();
        this.settled = claim.getSettled();
        this.status = claim.getStatus();
    }

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }

    public Principal getSubmitterId() {
        return submitterId;
    }

    public void setSubmitterId(Principal submitterId) {
        this.submitterId = submitterId;
    }

    public String getSubmitted() {
        return submitted;
    }

    public void setSubmitted(String submitted) {
        this.submitted = submitted;
    }

    public double getClaimed() {
        return claimed;
    }

    public void setClaimed(double claimed) {
        this.claimed = claimed;
    }

    public ClaimType getType() {
        return type;
    }

    public void setType(ClaimType typeId) {
        this.type = typeId;
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

    public Principal getResolverId() {
        return resolverId;
    }

    public void setResolverId(Principal resolverId) {
        this.resolverId = resolverId;
    }

    public String getResolved() {
        return resolved;
    }

    public void setResolved(String resolved) {
        this.resolved = resolved;
    }

    public double getSettled() {
        return settled;
    }

    public void setSettled(double settled) {
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
        return "Ticket{" +
                "claimId='" + claimId + '\'' +
                ", submitterId='" + submitterId + '\'' +
                ", submitted='" + submitted + '\'' +
                ", claimed=" + claimed +
                ", typeId='" + type+ '\'' +
                ", description='" + description + '\'' +
                ", receipt=" + Arrays.toString(receipt) +
                ", resolverId='" + resolverId + '\'' +
                ", resolved='" + resolved + '\'' +
                ", settled=" + settled +
                ", status='" + status + '\'' +
                '}';
    }
}
