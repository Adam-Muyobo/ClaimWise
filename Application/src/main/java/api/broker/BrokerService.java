package api.broker;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import api.DatabaseConnection;

public class BrokerService {


    // Retrieve a broker by their ID
    public Broker getBrokerByID(int brokerID){

        String query = "SELECT BrokerID, BrokerName, PolicyNumber FROM T_Broker WHERE BrokerID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, brokerID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Broker(
                    rs.getInt("BrokerID"),
                    rs.getString("BrokerName"),
                    rs.getInt("PolicyNumber")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving broker: " + e.getMessage());
        }
        return null;
    }

    // Retrieving all brokers
    public List<Broker> getAllBrokers() {
        String query = "SELECT BrokerID, BrokerName, PolicyNumber FROM T_Broker";
        List<Broker> brokerList = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                brokerList.add(
                    new Broker(
                        rs.getInt("BrokerID"),
                        rs.getString("BrokerName"),
                        rs.getInt("PolicyNumber")
                    )
                );
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving brokers: " + e.getMessage());
        }
        return brokerList;
    }
    //handler
    private void brokerHandling(Broker broker, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, broker.getBrokerName());
        pstmt.setInt(2, broker.getPolicyName());
    }

    // Insert a new broker
    public boolean insertBroker(Broker broker) {
        String query = "INSERT INTO T_Broker (BrokerName, PolicyNumber) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            brokerHandling(broker, pstmt);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error inserting broker: " + e.getMessage());
        }
        return false;
    }

    // Update an existing broker
    public boolean updateBroker(int brokerID, Broker newBroker) {
        String query = "UPDATE T_Broker SET BrokerName = ?, PolicyNumber = ? WHERE BrokerID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            brokerHandling(newBroker, pstmt);
            pstmt.setInt(3, brokerID); // Set the BrokerID at the end for the WHERE clause
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error updating broker: " + e.getMessage());
        }
        return false;
    }

    // Delete a broker by their ID
    public boolean deleteBroker(int brokerID) {
        String query = "DELETE FROM T_Broker WHERE BrokerID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, brokerID); // Set the BrokerID as the parameter
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting broker: " + e.getMessage());
        }
        return false;
    }
    
}
