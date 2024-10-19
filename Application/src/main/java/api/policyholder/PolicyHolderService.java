package api.policyholder;

import java.sql.*;
import api.DatabaseConnection;
import java.util.ArrayList;
import java.util.List;


public class PolicyHolderService {

    public PolicyHolder getPolicyHolderByID(String phID){
        String query = "SELECT PolicyHolderID, FirstName, LastName, DOB, CompanyName, phoneNumber, Address FROM T_PolicyHolder WHERE PolicyHolderID = ?  ";

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query); ){
            pstmt.setString(1, phID);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){

                return new PolicyHolder(
                    rs.getString("PolicyHolderID"),
                    rs.getString("Firstname"),
                    rs.getString("LastName"),
                    rs.getDate("DOB"),
                    rs.getString("CompanyName"),
                    rs.getString("phoneNumber"),
                    rs.getString("Address")
                );
            }  
        }catch (SQLException e) {
            System.err.println("Error retrieving policyholder: " + e.getMessage());
        }
        return null;
    }

    //Retrieving all Policyholders
    public List<PolicyHolder> getAllPolicyHolders(){
        String query = "SELECT PolicyHolderID, FirstName, LastName, DOB, CompanyName, phoneNumber, Address FROM T_PolicyHolder";
        List<PolicyHolder> phList = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query); ){
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){

                phList.add(
                    new PolicyHolder(
                    rs.getString("PolicyHolderID"),
                    rs.getString("Firstname"),
                    rs.getString("LastName"),
                    rs.getDate("DOB"),
                    rs.getString("CompanyName"),
                    rs.getString("phoneNumber"),
                    rs.getString("Address"))
                );
            }  
        }catch (SQLException e) {
            System.err.println("Error retrieving policy: " + e.getMessage());
        }
        return phList;
    }

    private void policyHolderHandling(PolicyHolder policyHolder, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, policyHolder.getPolicyHolderID());
        pstmt.setString(2, policyHolder.getFirstName());
        pstmt.setString(3, policyHolder.getLastName());
        pstmt.setDate(4, new java.sql.Date(policyHolder.getDateOfBirth().getTime()));
        pstmt.setString(5, policyHolder.getCompanyName());
        pstmt.setString(6, policyHolder.getPhoneNumber());
        pstmt.setString(7, policyHolder.getAddress());
    }
    
    public boolean insertPolicyHolder(PolicyHolder ph){
        String query = "INSERT INTO T_PolicyHolder (PolicyHolderID, FirstName, LastName, DOB, CompanyName, phoneNumber, Address) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
            policyHolderHandling(ph, pstmt);
            return pstmt.executeUpdate() > 0;
        }
        catch (SQLException e) {
            System.err.println("Error inserting policy holder: " + e.getMessage());
        }
        return false;
        
    }

    public boolean updatePolicyHolder(String policyHolderID, PolicyHolder newPolicyHolder) {
        String query = "UPDATE T_PolicyHolder SET FirstName = ?, LastName = ?, DOB = ?, CompanyName = ?, phoneNumber = ?, Address = ? WHERE PolicyHolderID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            policyHolderHandling(newPolicyHolder, pstmt);
            pstmt.setString(7, policyHolderID); // Set the PolicyHolderID at the end for the WHERE clause
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating policy holder: " + e.getMessage());
        }
        return false;
    }

    public boolean deletePolicyHolder(String policyHolderID) {
        String query = "DELETE FROM T_PolicyHolder WHERE PolicyHolderID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, policyHolderID); // Set the PolicyHolderID as the parameter
            
            // Execute the update and return true if at least one row was affected (deleted)
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting policy holder: " + e.getMessage());
        }
        
        return false;
    }
    
    

    

}
