package api.Claim;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import api.DatabaseConnection;

public class ClaimService {

    public Claim getClaimByNumber(int claimNumber) {
        String query = "SELECT ClaimNumber, PolicyNumber, claimType, ClaimDate, ClaimAmount FROM T_Claim WHERE ClaimNumber = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, claimNumber);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Claim(
                    rs.getInt("ClaimNumber"),
                    rs.getInt("PolicyNumber"),
                    rs.getString("claimType"),
                    rs.getDate("ClaimDate"),
                    rs.getDouble("ClaimAmount")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving claim: " + e.getMessage());
        }
        return null;
    }

    // Retrieve all claims
    public List<Claim> getAllClaims() {
        String query = "SELECT ClaimNumber, PolicyNumber, claimType, ClaimDate, ClaimAmount FROM T_Claim";
        List<Claim> claimList = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                claimList.add(
                    new Claim(
                        rs.getInt("ClaimNumber"),
                        rs.getInt("PolicyNumber"),
                        rs.getString("claimType"),
                        rs.getDate("ClaimDate"),
                        rs.getDouble("ClaimAmount")
                    )
                );
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving claims: " + e.getMessage());
        }
        return claimList;
    }
    
    // Handler to set parameters for insert and update operations
    private void claimHandling(Claim claim, PreparedStatement pstmt) throws SQLException {
        pstmt.setInt(1, claim.getPolicyNumber());
        pstmt.setString(2, claim.getClaimType());
        pstmt.setDate(3, new java.sql.Date(claim.getClaimDate().getTime())); // Convert to java.sql.Date
        pstmt.setDouble(4, claim.getClaimAmount());
    }

    // Insert a new claim
    public boolean insertClaim(Claim claim) {
        String query = "INSERT INTO T_Claim (PolicyNumber, claimType, ClaimDate, ClaimAmount) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            claimHandling(claim, pstmt);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error inserting claim: " + e.getMessage());
        }
        return false;
    }

    // Update an existing claim by claim number
    public boolean updateClaim(int claimNumber, Claim newClaim) {
        String query = "UPDATE T_Claim SET PolicyNumber = ?, claimType = ?, ClaimDate = ?, ClaimAmount = ? WHERE ClaimNumber = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            claimHandling(newClaim, pstmt);
            pstmt.setInt(5, claimNumber); // Set the claim number for the WHERE clause
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error updating claim: " + e.getMessage());
        }
        return false;
    }

    // Delete a claim by claim number
    public boolean deleteClaim(int claimNumber) {
        String query = "DELETE FROM T_Claim WHERE ClaimNumber = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, claimNumber); // Set the claim number as the parameter
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting claim: " + e.getMessage());
        }
        return false;
    }
}
