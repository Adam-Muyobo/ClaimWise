package application.screens;

import api.policy.Policy;
import api.policy.PolicyService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RetrievePolicyScreen extends JFrame {
    private final JTextField policyNumberField;
    private final JTextArea displayArea;
    private final PolicyService service;

    public RetrievePolicyScreen() {
        service = new PolicyService();

        setTitle("Retrieve Policy");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Input field
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Enter Policy Number:"), gbc);
        policyNumberField = new JTextField();
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(policyNumberField, gbc);

        // Retrieve button
        JButton retrieveButton = new JButton("Retrieve");
        retrieveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int policyNumber = Integer.parseInt(policyNumberField.getText());
                    Policy policy = service.getPolicyById(policyNumber);
                    if (policy != null) {
                        displayArea.setText(policy.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Policy not found.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid policy number.");
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(retrieveButton, gbc);

        // Retrieve All button
        JButton retrieveAllButton = new JButton("Retrieve All");
        retrieveAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayArea.setText(service.getAllPolicies().toString());
            }
        });
        gbc.gridx = 1;
        add(retrieveAllButton, gbc);

        // Display area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(new JScrollPane(displayArea), gbc);

        setVisible(true);
    }

    public static void main(String[] args) {
        new RetrievePolicyScreen();
    }
}
