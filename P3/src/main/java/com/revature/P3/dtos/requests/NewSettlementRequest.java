package com.revature.P3.dtos.requests;

public class NewSettlementRequest {
    private String claimId;
    private double settledAmount;

    public NewSettlementRequest() {
        super();
    }

    public NewSettlementRequest(String claimId, double settledAmount) {
        this.claimId = claimId;
        this.settledAmount = settledAmount;
    }

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }

    public double getSettledAmount() {
        return settledAmount;
    }

    public void setSettledAmount(double settledAmount) {
        this.settledAmount = settledAmount;
    }

    @Override
    public String toString() {
        return "NewSettlementRequest{" +
                "claimId='" + claimId + '\'' +
                ", settledAmount=" + settledAmount +
                '}';
    }
}
