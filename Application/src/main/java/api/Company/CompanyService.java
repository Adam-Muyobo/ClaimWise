package api.Company;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import api.DatabaseConnection;

public class CompanyService {

    // Retrieve a company by company name
    public Company getCompanyByName(String companyName) {
        String query = "SELECT CompanyName, CompanyLocation, PolicyNumber FROM T_InsuranceCompany WHERE CompanyName = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, companyName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Company(
                    rs.getString("CompanyName"),
                    rs.getString("CompanyLocation"),
                    rs.getInt("PolicyNumber")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving company: " + e.getMessage());
        }
        return null;
    }

     // Retrieve all companies
    public List<Company> getAllCompanies() {
        String query = "SELECT CompanyName, CompanyLocation, PolicyNumber FROM T_InsuranceCompany";
        List<Company> companyList = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                companyList.add(
                    new Company(
                        rs.getString("CompanyName"),
                        rs.getString("CompanyLocation"),
                        rs.getInt("PolicyNumber")
                    )
                );
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving companies: " + e.getMessage());
        }
        return companyList;
    }

    private void companyHandling(Company company, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, company.getCompanyName());
        pstmt.setString(2, company.getCompanyLocation());
        pstmt.setInt(3, company.getPolicyNumber());
    }

    // Insert a new company
    public boolean insertCompany(Company company) {
        String query = "INSERT INTO T_InsuranceCompany (CompanyName, CompanyLocation, PolicyNumber) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            companyHandling(company, pstmt);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error inserting company: " + e.getMessage());
        }
        return false;
    }

    // Update an existing company by company name
    public boolean updateCompany(String companyName, Company newCompany) {
        String query = "UPDATE T_InsuranceCompany SET CompanyLocation = ?, PolicyNumber = ? WHERE CompanyName = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, newCompany.getCompanyLocation());
            pstmt.setInt(2, newCompany.getPolicyNumber());
            pstmt.setString(3, companyName); // Set the company name to 3 for the WHERE clause
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error updating company: " + e.getMessage());
        }
        return false;
    }

    // Delete a company by company name
    public boolean deleteCompany(String companyName) {
        String query = "DELETE FROM T_InsuranceCompany WHERE CompanyName = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, companyName); // Set the company name as the parameter
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting company: " + e.getMessage());
        }
        return false;
    }
    
}
