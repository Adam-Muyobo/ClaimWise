package application.screens;

import api.Claim.Claim;
import api.Claim.ClaimService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddClaimScreen extends JFrame {

    private final JTextField claimNumField;
    private final JTextField policyNumField;
    private final JTextField claimTypeField;
    private final JTextField claimDateField;
    private final JTextField claimAmountField;

    public AddClaimScreen() {
        setTitle("Add New Claim");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(8, 2));

        // Input fields
        add(new JLabel("Claim Number:"));
        claimNumField = new JTextField();
        add(claimNumField);

        add(new JLabel("Policy Number"));
        policyNumField = new JTextField();
        add(policyNumField);

        add(new JLabel("Claim Type:"));
        claimTypeField = new JTextField();
        add(claimTypeField);

        add(new JLabel("Claim Date:"));
        claimDateField = new JTextField();
        add(claimDateField);

        add(new JLabel("Claim Amount:"));
        claimAmountField = new JTextField();
        add(claimAmountField);

        // Add button
        JButton addButton = new JButton("Add Claim");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Parse the date input to ensure it's valid
                    java.sql.Date claimDate = java.sql.Date.valueOf(claimDateField.getText());
                    int claimNum = Integer.parseInt(claimNumField.getText());
                    int policyNum = Integer.parseInt(policyNumField.getText());
                    float claimAmount = Float.parseFloat(claimAmountField.getText());
                    
                    // Create a new Claim object with validated data
                    Claim newClaim = new Claim(
                        claimNum,
                        policyNum,
                        claimTypeField.getText(),
                        claimDate,
                        claimAmount
                    );
        
                    // Insert Claim and show message
                    ClaimService service = new ClaimService();
                    if (service.insertClaim(newClaim)) {
                        JOptionPane.showMessageDialog(null, "Claim Added!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error Adding Claim.");
                    }
                    dispose(); // Close the window after adding
                    
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter the date in 'yyyy-MM-dd' format.");
                }
            }
        });
        
        add(addButton);

        setVisible(true);
    }
    
}
