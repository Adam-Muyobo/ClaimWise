package application.screens;

import api.product.ProductService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteProductScreen extends JFrame {
    private final JTextField ProductNumField;
    private final ProductService service;

    public DeleteProductScreen() {
        service = new ProductService();

        setTitle("Delete Product");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Input field
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Enter Product Number:"), gbc);
        ProductNumField = new JTextField();
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(ProductNumField, gbc);

        // Delete button
        JButton deleteButton = new JButton("Delete Product");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int ProductNumber = Integer.parseInt(ProductNumField.getText());
                    if (service.deleteProduct(ProductNumber)) {
                        JOptionPane.showMessageDialog(null, "Product Deleted Successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error Deleting Product.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Product number.");
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(deleteButton, gbc);

        // Cancel button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        gbc.gridx = 1;
        add(cancelButton, gbc);

        setVisible(true);
    }

    public static void main(String[] args) {
        new DeleteProductScreen();
    }
}