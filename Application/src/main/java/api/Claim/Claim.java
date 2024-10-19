package api.Claim;

import java.util.Date;

public class Claim {
    
    private int claimNumber;
    private int policyNumber;
    private String claimType;
    private Date claimDate;
    private double claimAmount;

    public Claim(int claimNumber, int policyNumber, String claimType, Date claimDate, double claimAmount) {
        this.claimNumber = claimNumber;
        this.policyNumber = policyNumber;
        this.claimType = claimType;
        this.claimDate = claimDate;
        this.claimAmount = claimAmount;
    }

    public int getClaimNumber() {
        return claimNumber;
    }

    public void setClaimNumber(int claimNumber) {
        this.claimNumber = claimNumber;
    }

    public int getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(int policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getClaimType() {
        return claimType;
    }

    public void setClaimType(String claimType) {
        this.claimType = claimType;
    }

    public Date getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public double getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(double claimAmount) {
        this.claimAmount = claimAmount;
    }

    @Override
    public String toString() {
        return "Claim [claimNumber=" + claimNumber + ", policyNumber=" + policyNumber + ", claimType=" + claimType
                + ", claimDate=" + claimDate + ", claimAmount=" + claimAmount + "]";
    }
 
}
