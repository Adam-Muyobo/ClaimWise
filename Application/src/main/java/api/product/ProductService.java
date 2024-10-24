package api.product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import api.DatabaseConnection;

public class ProductService {

    // Retrieve a product by ProductNo
    public Product getProductByNo(int productNo) {
        String query = "SELECT ProductNo, ProductType, AreaName, PolicyNumber FROM T_Product WHERE ProductNo = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, productNo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Product(
                    rs.getInt("ProductNo"),
                    rs.getString("ProductType"),
                    rs.getString("AreaName"),
                    rs.getInt("PolicyNumber")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving product: " + e.getMessage());
        }
        return null;
    }

    // Retrieve all products
    public List<Product> getAllProducts() {
        String query = "SELECT ProductNo, ProductType, AreaName, PolicyNumber FROM T_Product";
        List<Product> productList = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                productList.add(new Product(
                    rs.getInt("ProductNo"),
                    rs.getString("ProductType"),
                    rs.getString("AreaName"),
                    rs.getInt("PolicyNumber")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving products: " + e.getMessage());
        }
        return productList;
    }

    // Handler method to set common fields in PreparedStatement
    private void productHandler(Product product, PreparedStatement pstmt) throws SQLException {
    pstmt.setString(1, product.getProductType());
    pstmt.setString(2, product.getAreaName());
        pstmt.setInt(3, product.getPolicyNumber());
    }

    // Insert a new product
    public boolean insertProduct(Product product) {
        if (product == null || product.getProductType() == null || product.getAreaName() == null) {
            System.err.println("Invalid product data provided.");
            return false;
        }

        String query = "INSERT INTO T_Product (ProductType, AreaName, PolicyNumber) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            productHandler(product, pstmt); // Use handler method to set values
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting product: " + e.getMessage());
        }
        return false;
    }


    // Update a product
    public boolean updateProduct(int productNo, Product product) {
        String query = "UPDATE T_Product SET ProductType = ?, AreaName = ?, PolicyNumber = ? WHERE ProductNo = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            productHandler(product, pstmt); // Use handler method to set values
            pstmt.setInt(4, productNo); // Set ProductNo for WHERE clause
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating product: " + e.getMessage());
        }
        return false;
    }

    // Delete a product
    public boolean deleteProduct(int productNo) {
        String query = "DELETE FROM T_Product WHERE ProductNo = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, productNo); // Set ProductNo as the parameter
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting product: " + e.getMessage());
        }
        return false;
    }

}
