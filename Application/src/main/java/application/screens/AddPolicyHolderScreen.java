package application.screens;

import api.policyholder.PolicyHolder;
import api.policyholder.PolicyHolderService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPolicyHolderScreen extends JFrame {
    private final JTextField policyHolderIDField;
    private final JTextField firstNameField;
    private final JTextField lastNameField;
    private final JTextField dobField;
    private final JTextField companyField;
    private final JTextField phoneField;
    private final JTextField addressField;

    public AddPolicyHolderScreen() {
        setTitle("Add New Policy Holder");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(8, 2));

        // Input fields
        add(new JLabel("Policy Holder ID:"));
        policyHolderIDField = new JTextField();
        add(policyHolderIDField);

        add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        add(firstNameField);

        add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        add(lastNameField);

        add(new JLabel("Company Name:"));
        companyField = new JTextField();
        add(companyField);

        add(new JLabel("Phone Number:"));
        phoneField = new JTextField();
        add(phoneField);

        add(new JLabel("Date of Birth:"));
        dobField = new JTextField();
        add(dobField);
        

        add(new JLabel("Address:"));
        addressField = new JTextField();
        add(addressField);

        // Add button
        JButton addButton = new JButton("Add Policy Holder");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PolicyHolder newPolicyHolder = new PolicyHolder(
                        policyHolderIDField.getText(),
                        firstNameField.getText(),
                        lastNameField.getText(),
                        companyField.getText(),
                        phoneField.getText(),
                        java.sql.Date.valueOf(dobField.getText()),
                        addressField.getText()
                );

                PolicyHolderService service = new PolicyHolderService();
                if (service.insertPolicyHolder(newPolicyHolder)) {
                    JOptionPane.showMessageDialog(null, "Policy Holder Added!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error Adding Policy Holder.");
                }
                dispose(); // Close the window after adding
            }
        });
        add(addButton);

        setVisible(true);
    }
}

