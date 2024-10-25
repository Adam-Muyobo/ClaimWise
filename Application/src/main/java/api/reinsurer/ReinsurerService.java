package api.reinsurer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import api.DatabaseConnection;

public class ReinsurerService {


    // Retrieve a reinsurer by ID
    public Reinsurer getReinsurerByID(int reinsurerID) {
        String query = "SELECT ReinsurerID, ReinsurerName, ReinsurerLocation FROM t_reinsurer WHERE ReinsurerID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, reinsurerID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Reinsurer(
                    rs.getInt("ReinsurerID"),
                    rs.getString("ReinsurerName"),
                    rs.getString("ReinsurerLocation")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving reinsurer: " + e.getMessage());
        }
        return null;
    }

    // Retrieve all reinsurers
    public List<Reinsurer> getAllReinsurers() {
        String query = "SELECT ReinsurerID, ReinsurerName, ReinsurerLocation FROM t_reinsurer";
        List<Reinsurer> reinsurerList = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                reinsurerList.add(new Reinsurer(
                    rs.getInt("ReinsurerID"),
                    rs.getString("ReinsurerName"),
                    rs.getString("ReinsurerLocation")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving reinsurers: " + e.getMessage());
        }
        return reinsurerList;
    }

    // Handler method to set common fields in PreparedStatement
    private void reinsurerHandler(Reinsurer reinsurer, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, reinsurer.getReinsurerName());
        pstmt.setString(2, reinsurer.getReinsurerLocation());
    }

    // Insert a new reinsurer
    public boolean insertReinsurer(Reinsurer reinsurer) {
        if (reinsurer == null || reinsurer.getReinsurerName() == null || reinsurer.getReinsurerLocation() == null) {
            System.err.println("Invalid reinsurer data provided.");
            return false;
        }

        String query = "INSERT INTO t_reinsurer (ReinsurerName, ReinsurerLocation) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            reinsurerHandler(reinsurer, pstmt);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting reinsurer: " + e.getMessage());
        }
        return false;
    }


    // Update an existing reinsurer
    public boolean updateReinsurer(int reinsurerID, Reinsurer reinsurer) {
        String query = "UPDATE t_reinsurer SET ReinsurerName = ?, ReinsurerLocation = ? WHERE ReinsurerID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            reinsurerHandler(reinsurer, pstmt);
            pstmt.setInt(3, reinsurerID); // Set ReinsurerID for WHERE clause
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating reinsurer: " + e.getMessage());
        }
        return false;
    }

    // Delete a reinsurer
    public boolean deleteReinsurer(int reinsurerID) {
        String query = "DELETE FROM t_reinsurer WHERE ReinsurerID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, reinsurerID); // Set ReinsurerID as the parameter
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting reinsurer: " + e.getMessage());
        }
        return false;
    }
}
