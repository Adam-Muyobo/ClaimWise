package api.ClubAssoc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import api.DatabaseConnection;

public class ClubAssocService {

    // Retrieve a Club Association by name
    public ClubAssoc getClubAssocByName(String caName) {
        String query = "SELECT CAName FROM T_ClubAssoc WHERE CAName = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, caName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new ClubAssoc(rs.getString("CAName"));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving club association: " + e.getMessage());
        }
        return null;
    }

    //Retrieve all Club Associations

    public List<ClubAssoc> getAllClubAssocs() {
        String query = "SELECT CAName FROM T_ClubAssoc";
        List<ClubAssoc> caList = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                caList.add(new ClubAssoc(rs.getString("CAName")));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving club associations: " + e.getMessage());
        }
        return caList;
    }

    // Insert a new Club Association
    public boolean insertClubAssoc(ClubAssoc clubAssoc) {
        String query = "INSERT INTO T_ClubAssoc (CAName) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, clubAssoc.getCaName());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting club association: " + e.getMessage());
        }
        return false;
    }

    // Update an existing Club Association name
    public boolean updateClubAssoc(String oldCaName, String newCaName) {
        String query = "UPDATE T_ClubAssoc SET CAName = ? WHERE CAName = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, newCaName);
            pstmt.setString(2, oldCaName);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating club association: " + e.getMessage());
        }
        return false;
    }
    
     // Delete a Club Association by name
     public boolean deleteClubAssoc(String caName) {
        String query = "DELETE FROM T_ClubAssoc WHERE CAName = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, caName);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting club association: " + e.getMessage());
        }
        return false;
    }
}
