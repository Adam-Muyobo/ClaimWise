package application.screens;

import api.policy.PolicyService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeletePolicyScreen extends JFrame {
    private final JTextField policyNumberField;
    private final PolicyService service;

    public DeletePolicyScreen() {
        service = new PolicyService();

        setTitle("Delete Policy");
        setSize(400, 150);
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

        // Delete button
        JButton deleteButton = new JButton("Delete Policy");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int policyNumber = Integer.parseInt(policyNumberField.getText());
                    if (service.deletePolicy(policyNumber)) {
                        JOptionPane.showMessageDialog(null, "Policy Deleted Successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error Deleting Policy.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid policy number.");
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
        new DeletePolicyScreen();
    }
}
