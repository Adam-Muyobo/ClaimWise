package api.policy;

import api.DatabaseConnection;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PolicyService {

    // Retrieve a single Policy by PolicyNumber
    public Policy getPolicyById(int policyNumber) {
        String sql = "SELECT PolicyNumber, PolicyHolderID, PolicyName, PolicyType, PolicyCost, expiryDate FROM t_policy WHERE PolicyNumber = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, policyNumber);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Policy(
                        rs.getInt("PolicyNumber"),
                        rs.getString("PolicyHolderID"),
                        rs.getString("PolicyName"),
                        rs.getString("PolicyType"),
                        rs.getBigDecimal("PolicyCost"),
                        rs.getDate("expiryDate")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving policy: " + e.getMessage());
        }
        return null;
    }

    // Retrieve all Policies
    public List<Policy> getAllPolicies() {
        String sql = "SELECT PolicyNumber, PolicyHolderID, PolicyName, PolicyType, PolicyCost, expiryDate FROM t_policy";
        List<Policy> policies = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                policies.add(new Policy(
                        rs.getInt("PolicyNumber"),
                        rs.getString("PolicyHolderID"),
                        rs.getString("PolicyName"),
                        rs.getString("PolicyType"),
                        rs.getBigDecimal("PolicyCost"),
                        rs.getDate("expiryDate")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving policies: " + e.getMessage());
        }
        return policies;
    }

    // Experimenting with this cheese just to see if it makes the code cleaner
    private void policyHandling(Policy policy, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, policy.getPolicyHolderID());
        pstmt.setString(2, policy.getPolicyName());
        pstmt.setString(3, policy.getPolicyType());
        pstmt.setBigDecimal(4, policy.getPolicyCost());
        pstmt.setDate(5, new Date(policy.getExpiryDate().getTime()));
    }

    // Insert new Policy
    public boolean insertPolicy(Policy newPolicy) {
        String sql = "INSERT INTO t_policy (PolicyHolderID, PolicyName, PolicyType, PolicyCost, expiryDate) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            policyHandling(newPolicy, pstmt);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting policy: " + e.getMessage());
        }
        return false;
    }

    // Update existing Policy
    public boolean updatePolicy(int policyNumber, Policy newPolicy) {
        String sql = "UPDATE t_policy SET PolicyHolderID = ?, PolicyName = ?, PolicyType = ?, PolicyCost = ?, expiryDate = ? WHERE PolicyNumber = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            policyHandling(newPolicy, pstmt);
            pstmt.setInt(6, policyNumber);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating policy: " + e.getMessage());
        }
        return false;
    }

    // Delete Policy
    public boolean deletePolicy(int policyNumber) {
        String sql = "DELETE FROM t_policy WHERE PolicyNumber = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, policyNumber);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting policy: " + e.getMessage());
        }
        return false;
    }
}
