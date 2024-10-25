package application.screens;

import api.product.Product;
import api.product.ProductService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProductScreen extends JFrame {

    private final JTextField productNumField = new JTextField();
    private final JTextField productTypeField = new JTextField();;
    private final JTextField areaNameField = new JTextField();;
    private final JTextField policyNumField = new JTextField();;

    public AddProductScreen() {

        setTitle("Add new Product");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(8, 2));

        // input fields
        add(new JLabel("Product No:"));
        add(productNumField);

        add(new JLabel("Product Type:"));
        add(productTypeField);

        add(new JLabel("Area Name:"));
        add(areaNameField);

        add(new JLabel("Policy No:"));
        add(policyNumField);

        // add button
        JButton addButton = new JButton("Add Product");
       

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int productNum = Integer.parseInt(productNumField.getText());
                int policyNum = Integer.parseInt(policyNumField.getText());
                Product newProduct = new Product(
                    productNum,
                    productTypeField.getText(),
                    areaNameField.getText(),
                    policyNum
                );

                ProductService service = new ProductService();
                if (service.insertProduct(newProduct)) {
                    JOptionPane.showMessageDialog(null, "Product Added!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error Adding Product.");
                }
                dispose(); // Close the window after adding
            }
        });
        add(addButton);

        setVisible(true);
    }
    
}
