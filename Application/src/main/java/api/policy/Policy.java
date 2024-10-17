package api.policy;

import java.math.BigDecimal;
import java.util.Date;

public class Policy {
    private int policyNumber;
    private String policyHolderID;
    private String policyName;
    private String policyType;
    private BigDecimal policyCost;
    private Date expiryDate;

    public Policy(int policyNumber, String policyHolderID, String policyName, String policyType, BigDecimal policyCost, Date expiryDate) {
        this.policyNumber = policyNumber;
        this.policyHolderID = policyHolderID;
        this.policyName = policyName;
        this.policyType = policyType;
        this.policyCost = policyCost;
        this.expiryDate = expiryDate;
    }

    public int getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(int policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getPolicyHolderID() {
        return policyHolderID;
    }

    public void setPolicyHolderID(String policyHolderID) {
        this.policyHolderID = policyHolderID;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public BigDecimal getPolicyCost() {
        return policyCost;
    }

    public void setPolicyCost(BigDecimal policyCost) {
        this.policyCost = policyCost;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "Policy{" +
                "policyNumber=" + policyNumber +
                ", policyHolderID='" + policyHolderID + '\'' +
                ", policyName='" + policyName + '\'' +
                ", policyType='" + policyType + '\'' +
                ", policyCost=" + policyCost +
                ", expiryDate=" + expiryDate +
                '}';
    }
}

