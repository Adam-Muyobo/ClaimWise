package application.screens;

import api.broker.Broker;
import api.broker.BrokerService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBrokerScreen extends JFrame {
    
    private final JTextField brokerIDField;
    private final JTextField brokerNameField;
    private final JTextField policyNumField;

    public AddBrokerScreen() {
        setTitle("Add New Broker");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(8, 2));

        // input fields
        add(new JLabel("Broker ID:"));
        brokerIDField = new JTextField();
        add(brokerIDField);

        add(new JLabel("Broker Name:"));
        brokerNameField = new JTextField();
        add(brokerNameField);

        add(new JLabel("Policy Number:"));
        policyNumField = new JTextField();
        add(policyNumField);

        // add button
        JButton addButton = new JButton("Add Broker");

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int brokerID = Integer.parseInt(brokerIDField.getText());
                int policyNum = Integer.parseInt(policyNumField.getText());

                Broker newBroker = new Broker(
                    brokerID,
                    brokerNameField.getText(),
                    policyNum
                );

                BrokerService service = new BrokerService();
                if (service.insertBroker(newBroker)) {
                    JOptionPane.showMessageDialog(null, "Broker Added!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error Adding Broker.");
                }
                dispose(); // Close the window after adding
            }
        });
        add(addButton);

        setVisible(true);
    }
    
}
