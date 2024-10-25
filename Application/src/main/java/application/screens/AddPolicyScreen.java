package application.screens;

import api.policy.*;
import api.policy.PolicyService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class AddPolicyScreen extends JFrame {
    
    private final JTextField policyNameField;
    private final JTextField policyTypeField;
    private final JTextField policyHolderIDField;
    private final JTextField policyCostField;
    private final JTextField expiryDateField;

    public AddPolicyScreen() {
        setTitle("Add New Policy");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        // Input fields
        add(new JLabel("Policy Name:"));
        policyNameField = new JTextField();
        add(policyNameField);

        add(new JLabel("Policy Type:"));
        policyTypeField = new JTextField();
        add(policyTypeField);

        add(new JLabel("Policy Holder ID:"));
        policyHolderIDField = new JTextField();
        add(policyHolderIDField);

        add(new JLabel("Policy Cost:"));
        policyCostField = new JTextField();
        add(policyCostField);

        add(new JLabel("Expiry Date (YYYY-MM-DD):"));
        expiryDateField = new JTextField();
        add(expiryDateField);

        // Add button
        JButton addButton = new JButton("Add Policy");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Policy newPolicy = new Policy();

                    PolicyService service = new PolicyService();
                    if (service.insertPolicy(newPolicy)) {
                        JOptionPane.showMessageDialog(null, "Policy Added Successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error Adding Policy.");
                    }
                    dispose(); // Close the window after adding
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, 
                        "Please check your input format. Date should be YYYY-MM-DD and Cost should be a valid number.");
                }
            }
        });
        add(addButton);

        // Cancel button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        add(cancelButton);

        setVisible(true);
    }
    public static void main(String[] args) {
        new AddPolicyScreen();
    }
}
