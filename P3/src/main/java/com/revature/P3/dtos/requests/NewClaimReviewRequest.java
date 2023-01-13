package com.revature.P3.dtos.requests;

public class NewClaimReviewRequest {
    private Double settled;

    public NewClaimReviewRequest(Double settled) {
        this.settled = settled;
    }

    public Double getSettled() {
        return settled;
    }

    public void setSettled(Double settled) {
        this.settled = settled;
    }

    @Override
    public String toString() {
        return "NewClaimReviewRequest{" +
                "settled=" + settled +
                '}';
    }
}
