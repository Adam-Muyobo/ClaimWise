package api.Company;

public class Company {
    private String companyName;
    private String companyLocation;
    private int policyNumber;

    public Company(String companyName, String companyLocation, int policyNumber) {
        this.companyName = companyName;
        this.companyLocation = companyLocation;
        this.policyNumber = policyNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLocation() {
        return companyLocation;
    }

    public void setCompanyLocation(String companyLocation) {
        this.companyLocation = companyLocation;
    }

    public int getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(int policyNumber) {
        this.policyNumber = policyNumber;
    }

    @Override
    public String toString() {
        return "Company [companyName=" + companyName + ", companyLocation=" + companyLocation + ", policyNumber="
                + policyNumber + "]";
    }

    
}
