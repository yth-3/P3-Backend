package com.revature.P3.dtos.requests;

public class NewClaimRequest {
    private double claimedAmount;
    private String claimType;
    private String description;

    public NewClaimRequest() {
        super();
    }

    public NewClaimRequest(double claimedAmount, String claimType, String description) {
        this.claimedAmount = claimedAmount;
        this.claimType = claimType;
        this.description = description;
    }

    public double getClaimedAmount() {
        return claimedAmount;
    }

    public void setClaimedAmount(double claimedAmount) {
        this.claimedAmount = claimedAmount;
    }

    public String getClaimType() {
        return claimType;
    }

    public void setClaimType(String claimType) {
        this.claimType = claimType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "NewClaimRequest{" +
                "claimedAmount=" + claimedAmount +
                ", claimType='" + claimType + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
