package api.policyholder;
import java.util.Date;

public class PolicyHolder {
    private String policyHolderID;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String companyName;
    private String address;
    private String phoneNumber;
    

    public PolicyHolder(String policyHolderID, String firstName, String lastName, Date dateOfBirth, String companyName, String address, String phoneNumber) {
        this.policyHolderID = policyHolderID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    public String getPolicyHolderID() {
        return policyHolderID;
    }

    public void setPolicyHolderID(String policyHolderID) {
        this.policyHolderID = policyHolderID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "PolicyHolder [policyHolderID=" + policyHolderID + ", firstName=" + firstName + ", lastName=" + lastName
                + ", companyName=" + companyName + ", address=" + address + ", phoneNumber=" + phoneNumber
                + ", dateOfBirth=" + dateOfBirth + "]";
    }

}
