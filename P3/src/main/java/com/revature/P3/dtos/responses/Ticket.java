package com.revature.P3.dtos.responses;

import java.util.Arrays;

public class Ticket {
    private String claimId;
    private String submitterId;
    private String submitted;
    private double claimed;
    private String typeId;
    private String description;
    private byte[] receipt;
    private String resolverId;
    private String resolved;
    private double settled;
    private String status;

    public Ticket() {
        super();
    }

    public Ticket(String claimId, String submitterId, String submitted, double claimed, String typeId, String description, byte[] receipt, String resolverId, String resolved, double settled, String status) {
        this.claimId = claimId;
        this.submitterId = submitterId;
        this.submitted = submitted;
        this.claimed = claimed;
        this.typeId = typeId;
        this.description = description;
        this.receipt = receipt;
        this.resolverId = resolverId;
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

    public String getSubmitterId() {
        return submitterId;
    }

    public void setSubmitterId(String submitterId) {
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

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
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

    public String getResolverId() {
        return resolverId;
    }

    public void setResolverId(String resolverId) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "claimId='" + claimId + '\'' +
                ", submitterId='" + submitterId + '\'' +
                ", submitted='" + submitted + '\'' +
                ", claimed=" + claimed +
                ", typeId='" + typeId + '\'' +
                ", description='" + description + '\'' +
                ", receipt=" + Arrays.toString(receipt) +
                ", resolverId='" + resolverId + '\'' +
                ", resolved='" + resolved + '\'' +
                ", settled=" + settled +
                ", status='" + status + '\'' +
                '}';
    }
}
