package api.area;

import api.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AreaService {

    // Retrieve a single Area by AreaName
    public Area getAreaByName(String areaName) {
        String sql = "SELECT AreaName FROM t_area WHERE AreaName = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, areaName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Area(rs.getString("AreaName"));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving area: " + e.getMessage());
        }
        return null; // Return null if no area is found
    }

    // Retrieve all Areas
    public List<Area> getAllAreas() {
        String sql = "SELECT AreaName FROM t_area";
        List<Area> areas = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                areas.add(new Area(rs.getString("AreaName")));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving areas: " + e.getMessage());
        }
        return areas;
    }

    // Insert new Area
    public boolean insertArea(Area area) {
        String sql = "INSERT INTO t_area (AreaName) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, area.getAreaName());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting area: " + e.getMessage());
        }
        return false;
    }

    // Update existing Area
    public boolean updateArea(String oldAreaName, Area newArea) {
        String sql = "UPDATE t_area SET AreaName = ? WHERE AreaName = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newArea.getAreaName());
            stmt.setString(2, oldAreaName);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating area: " + e.getMessage());
        }
        return false;
    }

    // Delete Area
    public boolean deleteArea(String areaName) {
        String sql = "DELETE FROM t_area WHERE AreaName = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, areaName);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting area: " + e.getMessage());
        }
        return false;
    }
}

