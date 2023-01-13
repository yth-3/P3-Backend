package com.revature.P3.entities;

import com.revature.P3.enums.ClaimStatuses;
import com.revature.P3.utils.custom_exceptions.InvalidClaimException;

import javax.persistence.*;

@Entity
@Table(name = "claim_statuses")
public class ClaimStatus {
    @Id
    private String statusId;

    @Column(name = "status", nullable = false)
    private String status;

    public ClaimStatus() {
    }

    public ClaimStatus(ClaimStatuses claimStatus) {
        if (claimStatus.equals(ClaimStatuses.CREATED)) {
            this.statusId = "CREATED";
        }
        else if (claimStatus.equals(ClaimStatuses.SETTLED)) {
            this.statusId = "SETTLED";
        }
        else {
            throw new InvalidClaimException("Invalid claim status specified");
        }

        this.status = claimStatus.toString();
    }

    public ClaimStatus(String statusId, String status) {
        this.statusId = statusId;
        this.status = status;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ClaimStatus{" +
                "statusId='" + statusId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
